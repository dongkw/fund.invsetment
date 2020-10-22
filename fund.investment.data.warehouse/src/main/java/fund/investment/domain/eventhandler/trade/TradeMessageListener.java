package fund.investment.domain.eventhandler.trade;

import java.time.LocalDateTime;
import java.util.Optional;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fund.investment.infrastructure.repository.db.dao.trade.CustomTradeEventEntryRepository;
import fund.investment.infrastructure.repository.db.dao.trade.TradeEventEntry;
import fund.investment.util.Constant;
import infrastructure.trade.domain.model.event.OrderCancelledEvt;
import infrastructure.trade.domain.model.event.OrderCreatedEvt;
import infrastructure.trade.domain.model.event.OrderFilledEvt;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TradeMessageListener {
	
	@Autowired
	private CustomTradeEventEntryRepository datastore;
	
	@EventHandler
	@Transactional(rollbackFor = Exception.class)
	public void ofType(OrderCreatedEvt evt) {
		TradeEventEntry item = TradeEventEntry.builder()
				.id(evt.getId())
				.instructionId(evt.getInstructionId())
				.creatTime(LocalDateTime.now())
				.updateTime(LocalDateTime.now())
				.build();
		
			datastore.saveAndFlush(item);
			log.info(Constant.MESSAGE_BASE.concat(evt.toString()));
	}
	
	@EventHandler
	@Transactional(rollbackFor = Exception.class)
	public void ofType(OrderCancelledEvt evt) {
		Optional<TradeEventEntry> history = datastore.findById(evt.getId());
		if(history.isPresent()) {
			TradeEventEntry freshItem = history.get();
			freshItem.setUpdateTime(LocalDateTime.now());
			datastore.saveAndFlush(freshItem);
			log.info(Constant.MESSAGE_BASE.concat(evt.toString()));
		}
	}
		
	@EventHandler
	@Transactional(rollbackFor = Exception.class)
	public void ofType(OrderFilledEvt evt) {
		Optional<TradeEventEntry> history = datastore.findById(evt.getId());
		if(history.isPresent()) {
			TradeEventEntry freshItem = history.get();
			freshItem.setUpdateTime(LocalDateTime.now());
			datastore.saveAndFlush(freshItem);
			log.info(Constant.MESSAGE_BASE.concat(evt.toString()));
		}
	}
}
