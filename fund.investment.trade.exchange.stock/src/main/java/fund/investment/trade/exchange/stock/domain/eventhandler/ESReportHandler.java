package fund.investment.trade.exchange.stock.domain.eventhandler;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fund.investment.trade.exchange.stock.domain.factory.Factory;
import fund.investment.trade.exchange.stock.domain.report.mock.ZmpPlaceResponse;
import fund.investment.trade.exchange.stock.domain.report.mock.ZmqCancelResponse;
import fund.investment.trade.exchange.stock.domain.report.mock.ZmqCancellationReport;
import fund.investment.trade.exchange.stock.domain.report.mock.ZmqFillReport;
import fund.investment.trade.exchange.stock.domain.report.mock.ZmqPlacedReport;
import infrastructure.trade.domain.model.command.CancelConfirmOrderCmd;
import infrastructure.trade.domain.model.command.FillOrderCmd;
import infrastructure.trade.domain.model.command.PlaceCancelOrderCmd;
import infrastructure.trade.domain.model.command.PlaceConfirmOrderCmd;
import infrastructure.trade.domain.model.command.PlaceOrderCmd;
import infrastructure.trade.userinterface.dto.ReportHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ESReportHandler extends ReportHandler {

	@Autowired
	private CommandGateway commandGateway;

	@EventHandler
	public void receivePlaceResponse(ZmpPlaceResponse res) {
		log.info("Recieved Event: {}", res);

		PlaceOrderCmd cmd = Factory.parse(res);
		commandGateway.sendAndWait(cmd);

		log.info("Dispatched Command: {}", cmd);
	}

	@EventHandler
	public void receivedPlacedReport(ZmqPlacedReport rep) {
		log.info("Recieved Event: {}", rep);

		PlaceConfirmOrderCmd cmd = Factory.parse(rep);
		commandGateway.sendAndWait(cmd);
		
		log.info("Dispatched Command: {}", cmd);
	}

	@EventHandler
	public void receiveCancelResponse(ZmqCancelResponse res) {
		log.info("Recieved Event: {}", res);
		
		PlaceCancelOrderCmd cmd = Factory.parse(res);
		commandGateway.sendAndWait(cmd);
		
		log.info("Dispatched Command: {}", cmd);
	}

	@EventHandler
	public void ReceiveCancellationReport(ZmqCancellationReport rep) {
		log.info("Recieved Event: {}", rep);
		
		CancelConfirmOrderCmd cmd = Factory.parse(rep);
		commandGateway.sendAndWait(cmd);
		
		log.info("Dispatched Command: {}", cmd);
	}

	@EventHandler
	public void receiveFillReport(ZmqFillReport rep) {
		log.info("Recieved Event: {}", rep);
		
		FillOrderCmd cmd = Factory.parse(rep);
		commandGateway.sendAndWait(cmd);
		
		log.info("Dispatched Command: {}", cmd);
	}

}
