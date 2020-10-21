package fund.investment.domain.queryhandler.distribution;

import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fund.investment.domain.queryhandler.distribution.command.DistAllEventQuerier;
import fund.investment.domain.queryhandler.distribution.command.DistEventByIdQuerier;
import fund.investment.infrastructure.repository.db.dao.distribution.CustomDistEventEntryRepository;
import fund.investment.infrastructure.repository.db.dao.distribution.DistEventEntry;

@Component
public class DistQueryHandler {
	@Autowired
	private CustomDistEventEntryRepository dataStore;
	
	@QueryHandler
	public DistEventEntry queryById(DistEventByIdQuerier query) {
		DistEventEntry result = dataStore.findById(query.getId()).orElse(null);
		return result;
		
	}
	
	@QueryHandler
	public List<DistEventEntry> queryAll(DistAllEventQuerier query) {
		List<DistEventEntry> result = dataStore.findAll();
		return result;
		
	}
}
