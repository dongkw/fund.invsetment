package fund.investment.domain.queryhandler.trade;

import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fund.investment.domain.queryhandler.trade.command.TradeAllEventQuerier;
import fund.investment.domain.queryhandler.trade.command.TradeEventByIdQuerier;
import fund.investment.infrastructure.repository.db.dao.trade.CustomTradeEventEntryRepository;
import fund.investment.infrastructure.repository.db.dao.trade.TradeEventEntry;

@Component
public class TradeQueryHandler {
	
	@Autowired
	private CustomTradeEventEntryRepository dataStore;
	
	@QueryHandler
	public TradeEventEntry queryById(TradeEventByIdQuerier query) {
		TradeEventEntry result = dataStore.findById(query.getId()).orElse(null);
		return result;
	}
	
	@QueryHandler
	public List<TradeEventEntry> queryAll(TradeAllEventQuerier query) {
		List<TradeEventEntry> result = dataStore.findAll();
		return result;
	}
}
