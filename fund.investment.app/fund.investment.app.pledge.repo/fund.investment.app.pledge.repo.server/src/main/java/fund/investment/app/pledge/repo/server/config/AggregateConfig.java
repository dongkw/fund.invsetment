package fund.investment.app.pledge.repo.server.config;

import fund.investment.app.pledge.repo.server.aggregate.instruction.PledgeRepoIstrAggr;
import fund.investment.app.pledge.repo.server.aggregate.trade.PledgeOrderAggregate;
import org.axonframework.common.caching.Cache;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.SnapshotTriggerDefinition;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AggregateConfig {
//    @Bean
//    public EventSourcingRepository<PledgeRepoInquiryAggregate> aggregateInquiryRepository(EventStore eventStore, SnapshotTriggerDefinition snapshotTrigger, Cache cache) {
//        configEventBus(eventStore);
//        return EventSourcingRepository
//                .builder(PledgeRepoInquiryAggregate.class)
//                .cache(cache)
//                .snapshotTriggerDefinition(snapshotTrigger)
//                .eventStore(eventStore)
//                .build();
//    }
//
//    @Bean
//    public EventSourcingRepository<PledgeRepoInquiryResultAggregate> aggregateInquiryResultRepository(EventStore eventStore, SnapshotTriggerDefinition snapshotTrigger, Cache cache) {
//        configEventBus(eventStore);
//        return EventSourcingRepository
//                .builder(PledgeRepoInquiryResultAggregate.class)
//                .cache(cache)
//                .snapshotTriggerDefinition(snapshotTrigger)
//                .eventStore(eventStore)
//                .build();
//    }

    @Bean
    public EventSourcingRepository<PledgeRepoIstrAggr> aggregateIstrRepository(EventStore eventStore, SnapshotTriggerDefinition snapshotTrigger, Cache cache) {
        configEventBus(eventStore);
        return EventSourcingRepository
                .builder(PledgeRepoIstrAggr.class)
                .cache(cache)
                .snapshotTriggerDefinition(snapshotTrigger)
                .eventStore(eventStore)
                .build();
    }

    @Bean
    public EventSourcingRepository<PledgeOrderAggregate> aggregateTradeRepository(EventStore eventStore, SnapshotTriggerDefinition snapshotTrigger, Cache cache) {
        configEventBus(eventStore);
        return EventSourcingRepository
                .builder(PledgeOrderAggregate.class)
                .cache(cache)
                .snapshotTriggerDefinition(snapshotTrigger)
                .eventStore(eventStore)
                .build();
    }

    private void configEventBus(EventBus eventBus) {
//        eventBus.registerDispatchInterceptor(new EventDispatcherInterceptor());
    }
}
