package fund.investment.app.pledge.repo.server.controller.trade;

import fund.investment.app.pledge.repo.api.valueobject.trade.PledgeTradeElement;
import fund.investment.basic.common.util.controller.Async2SyncController;
import fund.investment.basic.common.util.uid.UIDGenerator;
import fund.investment.gateway.api.compliance.event.order.*;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.gateway.EventGateway;
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
@RequestMapping("/mock")
public class MockTradeController extends Async2SyncController {

    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    private EventGateway eventGateway;
    @Autowired
    private UIDGenerator uidGenerator;
    //创建

    @PostMapping("/counterparty/create")
    public ResponseEntity<?> createOrder(@RequestBody OrderCmplCreateEvt<PledgeTradeElement> evt) {
        eventGateway.publish(evt);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/placed")
    public ResponseEntity<?> placed(@RequestBody OrderCmplPlacedEvt<PledgeTradeElement> evt) {
        eventGateway.publish(evt);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/counterparty/fill")
    public ResponseEntity<?> fill(@RequestBody OrderCmplFilledEvt<PledgeTradeElement> evt) {
        eventGateway.publish(evt);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/counterparty/reject")
    public ResponseEntity<?> reject(@RequestBody OrderCmplRejectedEvt<PledgeTradeElement> evt) {
        eventGateway.publish(evt);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/counterparty/cancelled")
    public ResponseEntity<?> cancelled(@RequestBody OrderCmplCancelledEvt<PledgeTradeElement> evt) {
        eventGateway.publish(evt);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/counterparty/update")
    public ResponseEntity<?> update(@RequestBody OrderCmplUpdatedEvt<PledgeTradeElement> evt) {
        eventGateway.publish(evt);
        return ResponseEntity.ok().build();
    }
}
