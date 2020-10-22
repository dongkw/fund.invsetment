package fund.investment.trade.config;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.commandhandling.gateway.IntervalRetryScheduler;
import org.axonframework.commandhandling.gateway.RetryScheduler;
import org.axonframework.common.caching.Cache;
import org.axonframework.common.caching.WeakReferenceCache;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.eventhandling.TrackingEventProcessorConfiguration;
import org.axonframework.eventsourcing.EventCountSnapshotTriggerDefinition;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.SnapshotTriggerDefinition;
import org.axonframework.eventsourcing.Snapshotter;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.spring.eventsourcing.SpringAggregateSnapshotterFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import fund.investment.trade.domain.model.aggregate.OrderAggregate;
import lombok.var;

@Component
public class ESOrderConfig {

	@Autowired
	private CommandBus commandBus;
	
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
    public EventSourcingRepository<OrderAggregate> orderAggregateRepository(EventStore eventStore, Cache cache) {
        return EventSourcingRepository.builder(OrderAggregate.class)
                .cache(cache)
                .eventStore(eventStore)
                .build();
    }
	
	@Bean
    public Cache cache() {
        return new WeakReferenceCache();
    }
	
	@Bean("orderSnapshotTrigger")
	public SnapshotTriggerDefinition instructionSnapshotTrigger(Snapshotter snapshotter) {
	    return new EventCountSnapshotTriggerDefinition(snapshotter, snapshotThreshold);
	}
	
	@Bean
	public SpringAggregateSnapshotterFactoryBean snapshotter() {
	    var springAggregateSnapshotterFactoryBean = new SpringAggregateSnapshotterFactoryBean();
	    //Setting async executors
	    springAggregateSnapshotterFactoryBean.setExecutor(Executors.newSingleThreadExecutor());
	    return springAggregateSnapshotterFactoryBean;
	}

	@Autowired
	public void config(EventProcessingConfigurer configurer) {
		configurer.registerTrackingEventProcessorConfiguration(
				c -> TrackingEventProcessorConfiguration.forParallelProcessing(eventParallel)
		);
	}
	
	@Bean
    public CommandGateway commandGatewayWithRetry(){
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(retryExecutorCount);
        RetryScheduler rs = IntervalRetryScheduler.builder()
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
		
}
