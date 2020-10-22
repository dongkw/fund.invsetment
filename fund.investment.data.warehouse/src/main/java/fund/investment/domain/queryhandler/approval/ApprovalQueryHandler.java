package fund.investment.domain.queryhandler.approval;

import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fund.investment.domain.queryhandler.approval.command.ApprovalAllEventQuerier;
import fund.investment.domain.queryhandler.approval.command.ApprovalEventByIdQuerier;
import fund.investment.infrastructure.repository.db.dao.approval.ApprovalEventEntry;
import fund.investment.infrastructure.repository.db.dao.approval.CustomAprovalEventEntryRepository;

@Component
public class ApprovalQueryHandler {
	
	@Autowired
	private CustomAprovalEventEntryRepository dataStore;
	
	@QueryHandler
	public ApprovalEventEntry queryById(ApprovalEventByIdQuerier query) {
		ApprovalEventEntry result = dataStore.findById(query.getId()).orElse(null);
		return result;
	}
	
	@QueryHandler
	public List<ApprovalEventEntry> queryById(ApprovalAllEventQuerier query) {
		List<ApprovalEventEntry> result = dataStore.findAll();
		return result;
	}
}
