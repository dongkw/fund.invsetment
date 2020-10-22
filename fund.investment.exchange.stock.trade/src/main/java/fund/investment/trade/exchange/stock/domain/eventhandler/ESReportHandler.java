package fund.investment.trade.exchange.stock.domain.eventhandler;

import fund.investment.trade.exchange.stock.mock.MockFactory;
import fund.investment.trade.exchange.stock.mock.report.*;
import infrastructure.trade.domain.model.command.*;
import infrastructure.trade.userinterface.dto.ReportHandler;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ESReportHandler extends ReportHandler {

    @Autowired
    private CommandGateway commandGateway;

    @EventHandler
    public void receivePlaceResponse(ZmpPlaceResponse res) {
        log.info("Recieved Event: {}", res);

        PlaceOrderCmd cmd = MockFactory.parse(res);
        commandGateway.sendAndWait(cmd);

        log.info("Dispatched Command: {}", cmd);
    }

    @EventHandler
    public void receivedPlacedReport(ZmqPlacedReport rep) {
        log.info("Recieved Event: {}", rep);

        PlaceConfirmOrderCmd cmd = MockFactory.parse(rep);
        commandGateway.sendAndWait(cmd);

        log.info("Dispatched Command: {}", cmd);
    }

    @EventHandler
    public void receiveCancelResponse(ZmqCancelResponse res) {
        log.info("Recieved Event: {}", res);

        PlaceCancelOrderCmd cmd = MockFactory.parse(res);
        commandGateway.sendAndWait(cmd);

        log.info("Dispatched Command: {}", cmd);
    }

    @EventHandler
    public void ReceiveCancellationReport(ZmqCancellationReport rep) {
        log.info("Recieved Event: {}", rep);

        CancelConfirmOrderCmd cmd = MockFactory.parse(rep);
        commandGateway.sendAndWait(cmd);

        log.info("Dispatched Command: {}", cmd);
    }

    @EventHandler
    public void receiveFillReport(ZmqFillReport rep) {
        log.info("Recieved Event: {}", rep);

        FillOrderCmd cmd = MockFactory.parse(rep);
        commandGateway.sendAndWait(cmd);

        log.info("Dispatched Command: {}", cmd);
    }
}
