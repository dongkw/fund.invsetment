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
import fund.investment.infrastructure.repository.db.dao.instruction.CustomInstructionEventEntryRepository;
import fund.investment.infrastructure.repository.db.dao.instruction.InstructionEventEntry;
import fund.investment.util.Constant;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class InstructionMessageListener {
	
	@Autowired
	private CustomInstructionEventEntryRepository datastore;
	
	@EventHandler
	@Transactional(rollbackFor = Exception.class)
	public void ofType(IstrCreatedEvt evt) {
		InstructionEventEntry item = InstructionEventEntry.builder()
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
		Optional<InstructionEventEntry> history = datastore.findById(evt.getId());
		if(history.isPresent()) {
			InstructionEventEntry freshItem = history.get();
			freshItem.setUpdateTime(LocalDateTime.now());
			datastore.saveAndFlush(freshItem);
			log.info(Constant.MESSAGE_BASE.concat(evt.toString()));
		}
	}
	
	@EventHandler
	@Transactional(rollbackFor = Exception.class)
	public void ofType(AprvIstrPassEvt evt) {
		Optional<InstructionEventEntry> history = datastore.findById(evt.getId());
		if(history.isPresent()) {
			InstructionEventEntry freshItem = history.get();
			freshItem.setUpdateTime(LocalDateTime.now());
			datastore.saveAndFlush(freshItem);
			log.info(Constant.MESSAGE_BASE.concat(evt.toString()));
		}
	}
	
	@EventHandler
	@Transactional(rollbackFor = Exception.class)
	public void ofType(AprvIstrRejectedEvt evt) {
		Optional<InstructionEventEntry> history = datastore.findById(evt.getId());
		if(history.isPresent()) {
			InstructionEventEntry freshItem = history.get();
			freshItem.setUpdateTime(LocalDateTime.now());
			datastore.saveAndFlush(freshItem);
			log.info(Constant.MESSAGE_BASE.concat(evt.toString()));
		}
	}
	
	@EventHandler
	@Transactional(rollbackFor = Exception.class)
	public void ofType(IstrCancelledEvt evt) {
		Optional<InstructionEventEntry> history = datastore.findById(evt.getId());
		if(history.isPresent()) {
			InstructionEventEntry freshItem = history.get();
			freshItem.setUpdateTime(LocalDateTime.now());
			datastore.saveAndFlush(freshItem);
			log.info(Constant.MESSAGE_BASE.concat(evt.toString()));
		}
	}
	
	@EventHandler
	@Transactional(rollbackFor = Exception.class)
	public void ofType(IstrCompletedEvt evt) {
		Optional<InstructionEventEntry> history = datastore.findById(evt.getId());
		if(history.isPresent()) {
			InstructionEventEntry freshItem = history.get();
			freshItem.setUpdateTime(LocalDateTime.now());
			datastore.saveAndFlush(freshItem);
			log.info(Constant.MESSAGE_BASE.concat(evt.toString()));
		}
	}
	
	@EventHandler
	@Transactional(rollbackFor = Exception.class)
	public void ofType(IstrConfirmedEvt evt) {
		Optional<InstructionEventEntry> history = datastore.findById(evt.getId());
		if(history.isPresent()) {
			InstructionEventEntry freshItem = history.get();
			freshItem.setUpdateTime(LocalDateTime.now());
			datastore.saveAndFlush(freshItem);
			log.info(Constant.MESSAGE_BASE.concat(evt.toString()));
		}
	}
	
	@EventHandler
	@Transactional(rollbackFor = Exception.class)
	public void ofType(DistIstrRejectedEvt evt) {
		Optional<InstructionEventEntry> history = datastore.findById(evt.getId());
		if(history.isPresent()) {
			InstructionEventEntry freshItem = history.get();
			freshItem.setUpdateTime(LocalDateTime.now());
			datastore.saveAndFlush(freshItem);
			log.info(Constant.MESSAGE_BASE.concat(evt.toString()));
		}
	}
	
	@EventHandler
	@Transactional(rollbackFor = Exception.class)
	public void ofType(DistributedIstrEvt evt) {
		Optional<InstructionEventEntry> history = datastore.findById(evt.getId());
		if(history.isPresent()) {
			InstructionEventEntry freshItem = history.get();
			freshItem.setUpdateTime(LocalDateTime.now());
			datastore.saveAndFlush(freshItem);
			log.info(Constant.MESSAGE_BASE.concat(evt.toString()));
		}
	}
	
	@EventHandler
	@Transactional(rollbackFor = Exception.class)
	public void ofType(IstrOrderCancelledEvt evt) {
		Optional<InstructionEventEntry> history = datastore.findById(evt.getId());
		if(history.isPresent()) {
			InstructionEventEntry freshItem = history.get();
			freshItem.setUpdateTime(LocalDateTime.now());
			datastore.saveAndFlush(freshItem);
			log.info(Constant.MESSAGE_BASE.concat(evt.toString()));
		}
	}
	
	@EventHandler
	@Transactional(rollbackFor = Exception.class)
	public void ofType(IstrOrderCreatedEvt evt) {
		Optional<InstructionEventEntry> history = datastore.findById(evt.getId());
		if(history.isPresent()) {
			InstructionEventEntry freshItem = history.get();
			freshItem.setUpdateTime(LocalDateTime.now());
			datastore.saveAndFlush(freshItem);
			log.info(Constant.MESSAGE_BASE.concat(evt.toString()));
		}
	}
}
