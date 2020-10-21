package fund.investment.trade.config;

import java.util.concurrent.Executors;

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
	
	@Value("${snapshot.event.count.threshold}")
	private int snapshotThreshold;
	
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
				c -> TrackingEventProcessorConfiguration.forParallelProcessing(2)
		);
	}
		
}
