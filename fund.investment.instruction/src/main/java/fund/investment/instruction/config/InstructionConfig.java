package fund.investment.instruction.config;

import fund.investment.instruction.domain.model.aggregate.InstructionAggregate;
import lombok.var;
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
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;

@Component
@ComponentScan({"bank.investment.infrastructure.util"})
public class InstructionConfig {
	
	@Value("${snapshot.event.count.threshold}")
	private int snapshotThreshold;

	@Bean
    public EventSourcingRepository<InstructionAggregate> instructionAggregateRepository(EventStore eventStore, SnapshotTriggerDefinition snapshotTriggerDefinition, Cache cache) {
        return EventSourcingRepository.builder(InstructionAggregate.class)
                .cache(cache)
                .snapshotTriggerDefinition(snapshotTriggerDefinition)
                .eventStore(eventStore)
                .build();
    }

	@Bean
    public Cache cache() {
        return new WeakReferenceCache();
    }
	
	
	@Bean("instructionSnapshotTrigger")
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
