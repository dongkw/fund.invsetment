package fund.investment.domain.queryhandler.trade;

import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fund.investment.domain.queryhandler.trade.command.OrderAllEventQuerier;
import fund.investment.domain.queryhandler.trade.command.OrderEventByIdQuerier;
import fund.investment.infrastructure.repository.db.dao.trade.CustomOrderEventEntryRepository;
import fund.investment.infrastructure.repository.db.dao.trade.OrderEventEntry;

@Component
public class OrderQueryHandler {
	@Autowired
	private CustomOrderEventEntryRepository dataStore;
	
	@QueryHandler
	public OrderEventEntry queryById(OrderEventByIdQuerier query) {
		OrderEventEntry result = dataStore.findById(query.getId()).orElse(null);
		return result;
		
	}
	
	@QueryHandler
	public List<OrderEventEntry> queryAll(OrderAllEventQuerier query) {
		List<OrderEventEntry> result = dataStore.findAll();
		return result;
		
	}
}
