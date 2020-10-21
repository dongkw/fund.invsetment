package fund.investment.domain.queryhandler.instruction;

import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fund.investment.domain.queryhandler.instruction.command.IstrAllEventQuerier;
import fund.investment.domain.queryhandler.instruction.command.IstrEventByIdQuerier;
import fund.investment.infrastructure.repository.db.dao.instruction.CustomIstrEventEntryRepository;
import fund.investment.infrastructure.repository.db.dao.instruction.IstrEventEntry;

@Component
public class IstrQueryHandler {
	@Autowired
	private CustomIstrEventEntryRepository dataStore;
	
	@QueryHandler
	public IstrEventEntry queryById(IstrEventByIdQuerier query) {
		IstrEventEntry result = dataStore.findById(query.getId()).orElse(null);
		return result;
		
	}
	
	@QueryHandler
	public List<IstrEventEntry> queryAll(IstrAllEventQuerier query) {
		List<IstrEventEntry> result = dataStore.findAll();
		return result;
		
	}
}
