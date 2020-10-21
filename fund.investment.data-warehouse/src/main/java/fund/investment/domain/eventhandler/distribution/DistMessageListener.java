package fund.investment.domain.eventhandler.distribution;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fund.investment.infrastructure.distribution.domain.event.DistIstrInitilazationEvt;
import fund.investment.infrastructure.distribution.domain.event.DistIstrRejectedEvt;
import fund.investment.infrastructure.distribution.domain.event.DistributedIstrEvt;
import fund.investment.infrastructure.repository.db.dao.distribution.CustomDistEventEntryRepository;
import fund.investment.infrastructure.repository.db.dao.distribution.DistEventEntry;
import fund.investment.util.Constant;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DistMessageListener {
	
	@Autowired
	private CustomDistEventEntryRepository datastore;
	
	@EventHandler
	@Transactional(rollbackOn = Exception.class)
	public void ofType(DistIstrInitilazationEvt evt) {
		DistEventEntry item = DistEventEntry.builder()
				.id(evt.getId())
				.instructionId(evt.getInstructionId())
				.status(evt.getStatus())
				.creatTime(LocalDateTime.now())
				.updateTime(LocalDateTime.now())
				.build();
		datastore.saveAndFlush(item);
		
		log.info(Constant.MESSAGE_BASE + evt.toString());

	}
	
	@EventHandler
	@Transactional(rollbackOn = Exception.class)
	public void ofType(DistIstrRejectedEvt evt) {
		Optional<DistEventEntry> optionalItem =datastore.findById(evt.getId());
		if(optionalItem.isPresent()) {
			DistEventEntry item = optionalItem.get();
			
			item.setOperatorId(evt.getOperatorId());
			item.setUserId(evt.getUserId());
			item.setUpdateTime(LocalDateTime.now());
			
			datastore.saveAndFlush(item);
			
			log.info(Constant.MESSAGE_BASE + evt.toString());
			
		}
		
	}
	
	@EventHandler
	@Transactional(rollbackOn = Exception.class)
	public void ofType(DistributedIstrEvt evt) {
		Optional<DistEventEntry> optionalItem =datastore.findById(evt.getId());
		if(optionalItem.isPresent()) {
			DistEventEntry item = optionalItem.get();
			
			item.setOperatorId(evt.getOperatorId());
			item.setUserId(evt.getUserId());
			item.setUpdateTime(LocalDateTime.now());
			
			datastore.saveAndFlush(item);
			
			log.info(Constant.MESSAGE_BASE + evt.toString());
			
		}
		
	}

}
