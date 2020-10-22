package fund.investment.domain.queryhandler.distribution;

import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fund.investment.domain.queryhandler.distribution.command.DistributionAllEventQuerier;
import fund.investment.domain.queryhandler.distribution.command.DistributionEventByIdQuerier;
import fund.investment.infrastructure.repository.db.dao.distribution.CustomDistributionEventEntryRepository;
import fund.investment.infrastructure.repository.db.dao.distribution.DistributionEventEntry;

@Component
public class DistributionQueryHandler {
	
	@Autowired
	private CustomDistributionEventEntryRepository dataStore;
	
	@QueryHandler
	public DistributionEventEntry queryById(DistributionEventByIdQuerier query) {
		DistributionEventEntry result = dataStore.findById(query.getId()).orElse(null);
		return result;
	}
	
	@QueryHandler
	public List<DistributionEventEntry> queryAll(DistributionAllEventQuerier query) {
		List<DistributionEventEntry> result = dataStore.findAll();
		return result;
	}
}
