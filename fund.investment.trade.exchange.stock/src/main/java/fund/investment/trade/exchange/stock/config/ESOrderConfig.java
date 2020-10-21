package fund.investment.trade.exchange.stock.config;

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

import fund.investment.trade.exchange.stock.domain.model.aggregate.ExchangeStockOrderAggregate;
import lombok.var;

@Component
public class ESOrderConfig {
	
	@Value("${snapshot.event.count.threshold}")
	private int snapshotThreshold;
	
	@Bean
    public EventSourcingRepository<ExchangeStockOrderAggregate> orderAggregateRepository(EventStore eventStore, SnapshotTriggerDefinition orderSnapshotTrigger, Cache cache) {
        return EventSourcingRepository.builder(ExchangeStockOrderAggregate.class)
                .cache(cache)
                .snapshotTriggerDefinition(orderSnapshotTrigger)
                .eventStore(eventStore)
                .build();
    }
	
	@Bean
    public Cache cache() {
        return new WeakReferenceCache();
    }
	
	@Bean
	public SnapshotTriggerDefinition orderSnapshotTrigger(Snapshotter snapshotter) {
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
