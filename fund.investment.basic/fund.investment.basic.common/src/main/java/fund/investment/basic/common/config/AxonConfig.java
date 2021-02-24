package fund.investment.basic.common.config;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.commandhandling.gateway.IntervalRetryScheduler;
import org.axonframework.commandhandling.gateway.RetryScheduler;
import org.axonframework.common.caching.Cache;
import org.axonframework.common.caching.WeakReferenceCache;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.eventhandling.TrackingEventProcessorConfiguration;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.axonframework.eventhandling.tokenstore.TokenStore;
import org.axonframework.eventhandling.tokenstore.jpa.JpaTokenStore;
import org.axonframework.eventsourcing.EventCountSnapshotTriggerDefinition;
import org.axonframework.eventsourcing.SnapshotTriggerDefinition;
import org.axonframework.eventsourcing.Snapshotter;
import org.axonframework.modelling.saga.repository.jpa.JpaSagaStore;
import org.axonframework.serialization.Serializer;
import org.axonframework.spring.eventsourcing.SpringAggregateSnapshotterFactoryBean;
import org.axonframework.springboot.util.jpa.ContainerManagedEntityManagerProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Configuration
public class AxonConfig {

	@Value("${axon.snapshot.event.count.threshold}")
	private int snapshotThreshold;
	
	@Value("${axon.command.retry.max-count}")
	private int maxRetryCount;
	
	@Value("${axon.command.retry.interval}")
	private int retryInterval;
	
	@Value("${axon.command.retry.executor.count}")
	private int retryExecutorCount;
	
	@Value("${axon.event.parallel}")
	private int eventParallel;


	@Bean
    public Cache cache() {
        return new WeakReferenceCache();
    }
	
	@Bean
	public SnapshotTriggerDefinition snapshotTrigger(Snapshotter snapshotter) {
	    return new EventCountSnapshotTriggerDefinition(snapshotter, snapshotThreshold);
	}
	
	@Bean
	public SpringAggregateSnapshotterFactoryBean snapshotter() {
		SpringAggregateSnapshotterFactoryBean springAggregateSnapshotterFactoryBean = new SpringAggregateSnapshotterFactoryBean();
	    springAggregateSnapshotterFactoryBean.setExecutor(Executors.newSingleThreadExecutor());
	    return springAggregateSnapshotterFactoryBean;
	}

	@Autowired
	public void config(EventProcessingConfigurer configurer) {
		configurer.registerTrackingEventProcessorConfiguration(
				c -> TrackingEventProcessorConfiguration.forParallelProcessing(eventParallel)
		);
		configurer.registerHandlerInterceptor("Event-Handler-Interceptor", configuration -> new EventHandlerInterceptor());
	}

	@Bean
    public CommandGateway commandGatewayWithRetry(CommandBus commandBus, EventGateway eventGateway){
		registerInterceptors(commandBus, eventGateway);

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(retryExecutorCount);
        RetryScheduler rs = IntervalRetryScheduler
        		.builder()
        		.retryExecutor(scheduledExecutorService)
        		.maxRetryCount(maxRetryCount)
        		.retryInterval(retryInterval)
        		.build();
        CommandGateway commandGateway = DefaultCommandGateway
        		.builder()
        		.commandBus(commandBus)
        		.retryScheduler(rs)
        		.build();
        return commandGateway;
    }

    private void registerInterceptors(CommandBus commandBus, EventGateway eventGateway){
		commandBus.registerDispatchInterceptor(new CommandDispatcherInterceptor());
		commandBus.registerHandlerInterceptor(new CommandHandlerInterceptor(maxRetryCount, eventGateway));
	}

	@Bean
	public EntityManagerProvider entityManagerProvider(){
		return new ContainerManagedEntityManagerProvider();
	}

	@Bean
	public TokenStore tokenStore(Serializer serializer, EntityManagerProvider entityManagerProvider){
		return JpaTokenStore
				.builder()
				.entityManagerProvider(entityManagerProvider)
				.serializer(serializer)
				.build();
	}

	@Bean
	public JpaSagaStore sagaStore(Serializer serializer, EntityManagerProvider entityManagerProvider){
		JpaSagaStore sagaStore =
				JpaSagaStore
				.builder()
				.serializer(serializer)
				.entityManagerProvider(entityManagerProvider)
				.build();
		return sagaStore;
	}
}