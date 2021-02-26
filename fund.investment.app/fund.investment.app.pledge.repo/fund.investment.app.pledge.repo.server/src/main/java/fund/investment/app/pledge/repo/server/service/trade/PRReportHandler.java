package fund.investment.app.pledge.repo.server.service.trade;

import fund.investment.app.pledge.repo.api.command.trade.PRChangeModifyingOrderCmd;
import fund.investment.app.pledge.repo.server.controller.trade.mock.MockFactory;
import fund.investment.app.pledge.repo.server.controller.trade.mock.report.*;
import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.trade.api.command.*;
import fund.investment.basic.trade.api.dto.ReportHandler;
import fund.investment.basic.trade.api.event.FillConfirmingSuccessEvt;
import fund.investment.gateway.api.compliance.event.order.pledge.OrderChangeModifyingEvt;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PRReportHandler extends ReportHandler {

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

    @EventHandler
    public void receiveModifySucceed(OrderChangeModifyingEvt evt) {
        log.info("Recieved Event: {}", evt);
        PRChangeModifyingOrderCmd cmd = new PRChangeModifyingOrderCmd();
        BeanUtils.copyProperties(evt,cmd);
        commandGateway.send(cmd);
        log.info("Send command: {}", cmd);
    }


    @EventHandler
    public void receiveFillConfirmingSucceed(FillConfirmingSuccessEvt evt) {
        log.info("Recieved Event: {}", evt);
        ConfirmingFillSuccessCmd cmd = new ConfirmingFillSuccessCmd();
        BeanUtils.copyProperties(evt,cmd);
        commandGateway.send(cmd);
        log.info("Send command: {}", cmd);
    }

}
