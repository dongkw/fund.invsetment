package fund.investment.distribution.config;

import fund.investment.distribution.domain.aggregate.DistributeIstrAggregate;
import org.axonframework.common.caching.Cache;
import org.axonframework.common.caching.WeakReferenceCache;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class DistributionConfig {

    @Bean
    public EventSourcingRepository<DistributeIstrAggregate> distAggregateRepository(EventStore eventStore, Cache cache) {
        return EventSourcingRepository.builder(DistributeIstrAggregate.class)
                .cache(cache)
                .eventStore(eventStore)
                .build();
    }

    @Bean
    public Cache distCache() {
        return new WeakReferenceCache();
    }

}
