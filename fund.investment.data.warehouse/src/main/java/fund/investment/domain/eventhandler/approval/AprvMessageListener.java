package fund.investment.domain.eventhandler.approval;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import fund.investment.infrastructure.approve.domain.event.AprvIstrInitializationEvt;
import fund.investment.infrastructure.approve.domain.event.AprvIstrPassEvt;
import fund.investment.infrastructure.approve.domain.event.AprvIstrRejectedEvt;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fund.investment.infrastructure.repository.db.dao.approval.AprvEventEntry;
import fund.investment.infrastructure.repository.db.dao.approval.CustomAprvEventEntryRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AprvMessageListener {
	
	@Autowired
	private CustomAprvEventEntryRepository repository;
	
	@EventHandler
	@Transactional(rollbackOn = Exception.class)
	public void ofType(AprvIstrInitializationEvt evt) {
		AprvEventEntry aprvItem = AprvEventEntry.builder()
				.id(evt.getId())
				.instructionId(evt.getInstructionId())
				.status(evt.getStatus())
				.operatorId(evt.getOperatorId())
				.userId(evt.getUserId())
				.creatTime(LocalDateTime.now())
				.updateTime(LocalDateTime.now())
				.build();
		
		repository.save(aprvItem);
		log.info(evt.toString());
		
	}
	
	@EventHandler
	@Transactional(rollbackOn = Exception.class)
	public void ofType(AprvIstrPassEvt evt) {
		Optional<AprvEventEntry> aprvWrapper = repository.findById(evt.getId());
		if(aprvWrapper.isPresent()) {
			AprvEventEntry historyItem = aprvWrapper.get();
			
			historyItem.setStatus(evt.getStatus());
			historyItem.setUserId(evt.getUserId());
			historyItem.setOperatorId(evt.getOperatorId());
			
			repository.saveAndFlush(historyItem);
			log.info(evt.toString());
			
		}
		
	}
	
	@EventHandler
	@Transactional(rollbackOn = Exception.class)
	public void ofType(AprvIstrRejectedEvt evt) {
		Optional<AprvEventEntry> aprvWrapper = repository.findById(evt.getId());
		if(aprvWrapper.isPresent()) {
			AprvEventEntry historyItem = aprvWrapper.get();
			
			historyItem.setStatus(evt.getStatus());
			historyItem.setUserId(evt.getUserId());
			historyItem.setOperatorId(evt.getOperatorId());
			
			repository.saveAndFlush(historyItem);
			log.info(evt.toString());
			
		}
		
	}
	
}
