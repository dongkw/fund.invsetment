package fund.investment.domain.queryhandler.approval;

import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fund.investment.domain.queryhandler.approval.command.AprvAllEventQuerier;
import fund.investment.domain.queryhandler.approval.command.AprvEventByIdQuerier;
import fund.investment.infrastructure.repository.db.dao.approval.AprvEventEntry;
import fund.investment.infrastructure.repository.db.dao.approval.CustomAprvEventEntryRepository;

@Component
public class AprvQueryHandler {
	@Autowired
	private CustomAprvEventEntryRepository dataStore;
	
	@QueryHandler
	public AprvEventEntry queryById(AprvEventByIdQuerier query) {
		AprvEventEntry result = dataStore.findById(query.getId()).orElse(null);
		return result;
		
	}
	
	@QueryHandler
	public List<AprvEventEntry> queryById(AprvAllEventQuerier query) {
		List<AprvEventEntry> result = dataStore.findAll();
		return result;
		
	}
}
