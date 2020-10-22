package fund.investment.domain.queryhandler.instruction;

import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fund.investment.domain.queryhandler.instruction.command.InstructionAllEventQuerier;
import fund.investment.domain.queryhandler.instruction.command.InstructionEventByIdQuerier;
import fund.investment.infrastructure.repository.db.dao.instruction.CustomInstructionEventEntryRepository;
import fund.investment.infrastructure.repository.db.dao.instruction.InstructionEventEntry;

@Component
public class InstructionQueryHandler {
	
	@Autowired
	private CustomInstructionEventEntryRepository dataStore;
	
	@QueryHandler
	public InstructionEventEntry queryById(InstructionEventByIdQuerier query) {
		InstructionEventEntry result = dataStore.findById(query.getId()).orElse(null);
		return result;
	}
	
	@QueryHandler
	public List<InstructionEventEntry> queryAll(InstructionAllEventQuerier query) {
		List<InstructionEventEntry> result = dataStore.findAll();
		return result;
	}
}
