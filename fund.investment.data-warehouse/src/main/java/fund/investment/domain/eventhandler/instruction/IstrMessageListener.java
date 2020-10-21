package fund.investment.domain.eventhandler.instruction;

import java.time.LocalDateTime;
import java.util.Optional;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fund.investment.infrastructure.approve.domain.event.AprvIstrPassEvt;
import fund.investment.infrastructure.approve.domain.event.AprvIstrRejectedEvt;
import fund.investment.infrastructure.distribution.domain.event.DistIstrRejectedEvt;
import fund.investment.infrastructure.distribution.domain.event.DistributedIstrEvt;
import fund.investment.infrastructure.instruction.domain.model.event.IstrCancelledEvt;
import fund.investment.infrastructure.instruction.domain.model.event.IstrCompletedEvt;
import fund.investment.infrastructure.instruction.domain.model.event.IstrConfirmedEvt;
import fund.investment.infrastructure.instruction.domain.model.event.IstrCreatedEvt;
import fund.investment.infrastructure.instruction.domain.model.event.IstrFillReceivedEvt;
import fund.investment.infrastructure.instruction.domain.model.event.IstrOrderCancelledEvt;
import fund.investment.infrastructure.instruction.domain.model.event.IstrOrderCreatedEvt;
import fund.investment.infrastructure.repository.db.dao.instruction.CustomIstrEventEntryRepository;
import fund.investment.infrastructure.repository.db.dao.instruction.IstrEventEntry;
import fund.investment.util.Constant;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class IstrMessageListener {
	
	@Autowired
	private CustomIstrEventEntryRepository datastore;
	
	@EventHandler
	@Transactional(rollbackFor = Exception.class)
	public void ofType(IstrCreatedEvt evt) {
		IstrEventEntry item = IstrEventEntry.builder()
				.id(evt.getId())
				.creatTime(LocalDateTime.now())
				.updateTime(LocalDateTime.now())
				.build();
		
		datastore.saveAndFlush(item);
		log.info(Constant.MESSAGE_BASE.concat(evt.toString()));
	}
	
	@EventHandler
	@Transactional(rollbackFor = Exception.class)
	public void ofType(IstrFillReceivedEvt evt) {
		Optional<IstrEventEntry> history = datastore.findById(evt.getId());
		if(history.isPresent()) {
			IstrEventEntry freshItem = history.get();
			freshItem.setUpdateTime(LocalDateTime.now());
			datastore.saveAndFlush(freshItem);
			log.info(Constant.MESSAGE_BASE.concat(evt.toString()));
			
		}
	}
	
	@EventHandler
	@Transactional(rollbackFor = Exception.class)
	public void ofType(AprvIstrPassEvt evt) {
		Optional<IstrEventEntry> history = datastore.findById(evt.getId());
		if(history.isPresent()) {
			IstrEventEntry freshItem = history.get();
			freshItem.setUpdateTime(LocalDateTime.now());
			datastore.saveAndFlush(freshItem);
			log.info(Constant.MESSAGE_BASE.concat(evt.toString()));
		}
	}
	
	@EventHandler
	@Transactional(rollbackFor = Exception.class)
	public void ofType(AprvIstrRejectedEvt evt) {
		Optional<IstrEventEntry> history = datastore.findById(evt.getId());
		if(history.isPresent()) {
			IstrEventEntry freshItem = history.get();
			
			freshItem.setUpdateTime(LocalDateTime.now());

			datastore.saveAndFlush(freshItem);
			log.info(Constant.MESSAGE_BASE.concat(evt.toString()));
		}
	}
	
	@EventHandler
	@Transactional(rollbackFor = Exception.class)
	public void ofType(IstrCancelledEvt evt) {
		Optional<IstrEventEntry> history = datastore.findById(evt.getId());
		if(history.isPresent()) {
			IstrEventEntry freshItem = history.get();
			
			freshItem.setUpdateTime(LocalDateTime.now());

			datastore.saveAndFlush(freshItem);
			log.info(Constant.MESSAGE_BASE.concat(evt.toString()));
		}
	}
	
	@EventHandler
	@Transactional(rollbackFor = Exception.class)
	public void ofType(IstrCompletedEvt evt) {
		Optional<IstrEventEntry> history = datastore.findById(evt.getId());
		if(history.isPresent()) {
			IstrEventEntry freshItem = history.get();
			
			freshItem.setUpdateTime(LocalDateTime.now());
			
			datastore.saveAndFlush(freshItem);
			log.info(Constant.MESSAGE_BASE.concat(evt.toString()));
			
		}
	}
	
	@EventHandler
	@Transactional(rollbackFor = Exception.class)
	public void ofType(IstrConfirmedEvt evt) {
		Optional<IstrEventEntry> history = datastore.findById(evt.getId());
		if(history.isPresent()) {
			IstrEventEntry freshItem = history.get();
			
			freshItem.setUpdateTime(LocalDateTime.now());
			datastore.saveAndFlush(freshItem);
			log.info(Constant.MESSAGE_BASE.concat(evt.toString()));
		}
	}
	
	@EventHandler
	@Transactional(rollbackFor = Exception.class)
	public void ofType(DistIstrRejectedEvt evt) {
		Optional<IstrEventEntry> history = datastore.findById(evt.getId());
		if(history.isPresent()) {
			IstrEventEntry freshItem = history.get();
			
			freshItem.setUpdateTime(LocalDateTime.now());

			datastore.saveAndFlush(freshItem);
			log.info(Constant.MESSAGE_BASE.concat(evt.toString()));
		}
	}
	
	@EventHandler
	@Transactional(rollbackFor = Exception.class)
	public void ofType(DistributedIstrEvt evt) {
		Optional<IstrEventEntry> history = datastore.findById(evt.getId());
		if(history.isPresent()) {
			IstrEventEntry freshItem = history.get();
			
			freshItem.setUpdateTime(LocalDateTime.now());

			datastore.saveAndFlush(freshItem);
			log.info(Constant.MESSAGE_BASE.concat(evt.toString()));
		}
	}
	
	@EventHandler
	@Transactional(rollbackFor = Exception.class)
	public void ofType(IstrOrderCancelledEvt evt) {
		Optional<IstrEventEntry> history = datastore.findById(evt.getId());
		if(history.isPresent()) {
			IstrEventEntry freshItem = history.get();
			
			freshItem.setUpdateTime(LocalDateTime.now());
			datastore.saveAndFlush(freshItem);
			log.info(Constant.MESSAGE_BASE.concat(evt.toString()));
		}
	}
	
	@EventHandler
	@Transactional(rollbackFor = Exception.class)
	public void ofType(IstrOrderCreatedEvt evt) {
		Optional<IstrEventEntry> history = datastore.findById(evt.getId());
		if(history.isPresent()) {
			IstrEventEntry freshItem = history.get();
			
			freshItem.setUpdateTime(LocalDateTime.now());
//			freshItem.setStatus(evt.getInstructionState().getInstructionStatus().statusId());
			
			datastore.saveAndFlush(freshItem);
			log.info(Constant.MESSAGE_BASE.concat(evt.toString()));
		}
	}
	
}
