package fund.investment.app.pledge.repo.server.controller.trade;

import fund.investment.app.pledge.repo.api.command.trade.PRCreateOrderCmd;
import fund.investment.app.pledge.repo.api.command.trade.PRMatchOrderCmd;
import fund.investment.app.pledge.repo.api.command.trade.PRUpdateOrderCmd;
import fund.investment.app.pledge.repo.api.event.trade.PROrderUpdateConfirmEvt;
import fund.investment.app.pledge.repo.api.valueobject.trade.PledgeTradeElement;
import fund.investment.basic.common.util.controller.Async2SyncController;
import fund.investment.basic.common.util.uid.UIDGenerator;
import fund.investment.basic.trade.api.command.CancelOrderCmd;
import fund.investment.basic.trade.api.event.OrderCancellingEvt;
import fund.investment.basic.trade.api.event.OrderCreateConfirmedEvt;
import fund.investment.basic.trade.api.event.OrderFailedEvt;
import fund.investment.basic.trade.api.valueobject.SourceType;
import fund.investment.gateway.api.compliance.command.order.PlacingOrderCmplCmd;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author dongkw
 * @Date 2021/3/5、3:38 下午
 **/
@RestController
@RequestMapping("/trade")
public class TradeSyncController extends Async2SyncController {

    @Autowired
    private CommandGateway commandGateway;
    @Autowired
    private UIDGenerator uidGenerator;
    //创建

    @PostMapping("/create")
    public ResponseEntity createOrder(@RequestBody PRCreateOrderCmd cmd) {
        Long requestId = uidGenerator.nextId();
        cmd.setRequestId(requestId);
        cmd.setId(uidGenerator.nextId());
        cmd.setSourceType(SourceType.THIS_SIDE);
        commandGateway.send(cmd);
        return waitResponse(requestId);
    }

    @EventHandler
    public void handler(OrderCreateConfirmedEvt evt) {
        addResponse(evt.getRequestId(), evt);
    }

    @EventHandler
    public void handler(OrderFailedEvt evt) {
        addResponse(evt.getRequestId(), evt);
    }

    //提交
    @PostMapping("/commit")
    public ResponseEntity commit(@RequestBody PlacingOrderCmplCmd<PledgeTradeElement> cmd) {
        Long requestId = uidGenerator.nextId();
        cmd.setRequestId(requestId);
        commandGateway.send(cmd);
        return ResponseEntity.ok().build();
    }

    //取消


    //提交
    @PostMapping("/cancel")
    public ResponseEntity cancel(@RequestBody CancelOrderCmd cmd) {
        Long requestId = uidGenerator.nextId();
        cmd.setRequestId(requestId);
        commandGateway.send(cmd);
        return waitResponse(requestId);
    }

    @EventHandler
    public void handler(OrderCancellingEvt evt) {
        addResponse(evt.getRequestId(), evt);
    }


    //修改
    @PostMapping("/update")
    public ResponseEntity update(@RequestBody PRUpdateOrderCmd<PledgeTradeElement> cmd) {
        Long requestId = uidGenerator.nextId();
        cmd.setRequestId(requestId);
        commandGateway.send(cmd);
        return waitResponse(requestId);
    }

    @EventHandler
    public void handler(PROrderUpdateConfirmEvt<PledgeTradeElement> evt) {
        addResponse(evt.getRequestId(), evt);
    }


    //匹配
    @PostMapping("/match")
    public ResponseEntity update(@RequestBody PRMatchOrderCmd cmd) {
        Long requestId = uidGenerator.nextId();
        cmd.setRequestId(requestId);
        commandGateway.send(cmd);
        return waitResponse(requestId);
    }

    //成交

    //拒绝

    //撤销匹配
}
