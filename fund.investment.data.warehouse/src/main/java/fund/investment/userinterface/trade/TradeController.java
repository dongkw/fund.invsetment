package fund.investment.userinterface.trade;

import java.time.LocalDateTime;
import java.util.List;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fund.investment.domain.queryhandler.trade.command.TradeAllEventQuerier;
import fund.investment.domain.queryhandler.trade.command.TradeEventByIdQuerier;
import fund.investment.infrastructure.repository.db.dao.trade.TradeEventEntry;
import fund.investment.util.SwaggerTag;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping("/investment/orders")
public class TradeController {
	
	@Autowired
    private QueryGateway queryGateway;
	
	@RequestMapping(value = "/{orderId}", method = RequestMethod.GET)
	@ApiOperation(value = "findById", tags = SwaggerTag.TRADE)
	public ResponseEntity<TradeEventEntry> findById(@PathVariable("orderId") String orderId) {
		try {
			TradeEventEntry result = queryGateway.query(TradeEventByIdQuerier.builder().id(orderId).build(), ResponseTypes.instanceOf(TradeEventEntry.class)).join();
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ApiOperation(value = "findAll", tags = SwaggerTag.TRADE)
	public ResponseEntity<List<TradeEventEntry>> findAll() {
		try {
			List<TradeEventEntry> result = queryGateway.subscriptionQuery(TradeAllEventQuerier.builder().time(LocalDateTime.now()).build(), ResponseTypes.multipleInstancesOf(TradeEventEntry.class), ResponseTypes.instanceOf(TradeEventEntry.class)).initialResult().block();
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
