package fund.investment.domain.eventhandler.approval;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fund.investment.infrastructure.approve.domain.event.AprvIstrInitializationEvt;
import fund.investment.infrastructure.approve.domain.event.AprvIstrPassEvt;
import fund.investment.infrastructure.approve.domain.event.AprvIstrRejectedEvt;
import fund.investment.infrastructure.repository.db.dao.approval.ApprovalEventEntry;
import fund.investment.infrastructure.repository.db.dao.approval.CustomAprovalEventEntryRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ApprovalMessageListener {
	
	@Autowired
	private CustomAprovalEventEntryRepository repository;
	
	@EventHandler
	@Transactional(rollbackOn = Exception.class)
	public void ofType(AprvIstrInitializationEvt evt) {
		ApprovalEventEntry aprvItem = ApprovalEventEntry.builder()
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
		Optional<ApprovalEventEntry> aprvWrapper = repository.findById(evt.getId());
		if(aprvWrapper.isPresent()) {
			ApprovalEventEntry historyItem = aprvWrapper.get();
			
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
		Optional<ApprovalEventEntry> aprvWrapper = repository.findById(evt.getId());
		if(aprvWrapper.isPresent()) {
			ApprovalEventEntry historyItem = aprvWrapper.get();
			
			historyItem.setStatus(evt.getStatus());
			historyItem.setUserId(evt.getUserId());
			historyItem.setOperatorId(evt.getOperatorId());
			
			repository.saveAndFlush(historyItem);
			log.info(evt.toString());
		}
	}
}
