package fund.investment.approval.config;

import fund.investment.approval.domain.aggregate.ApprovalIstrAggregate;
import org.axonframework.common.caching.Cache;
import org.axonframework.common.caching.WeakReferenceCache;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class ApprovalConfig {
	
	@Bean
    public EventSourcingRepository<ApprovalIstrAggregate> aprvAggregateRepository(EventStore eventStore, Cache cache) {
        return EventSourcingRepository.builder(ApprovalIstrAggregate.class)
                .cache(cache)
                .eventStore(eventStore)
                .build();
    }
	
	@Bean
    public Cache distCache() {
        return new WeakReferenceCache();
    }
		
		
}
