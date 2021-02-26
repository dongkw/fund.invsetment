package fund.investment.app.pledge.repo.server.controller.trade;

import fund.investment.app.pledge.repo.server.utils.RequestHandleUtils;
import fund.investment.basic.common.util.controller.Async2SyncController;
import fund.investment.basic.common.util.uid.UIDGenerator;
import fund.investment.basic.trade.api.command.AutoMatchOrderCmd;
import fund.investment.basic.trade.api.command.ConfirmRejectOrderCmd;
import fund.investment.basic.trade.api.command.ConfirmedFillCmd;
import fund.investment.gateway.api.compliance.command.order.pledge.PRCreateNewRecOrderCmd;
import fund.investment.gateway.api.compliance.command.order.pledge.PROppositeModifyOrderCmd;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Api
@RestController
@RequestMapping("/mock/opposite")
public class OppositeReportMockController extends Async2SyncController {

    private final UIDGenerator uIDGenerator;

    private final CommandGateway commandGateway;

    @Autowired
    public OppositeReportMockController(UIDGenerator UIDGenerator, CommandGateway commandGateway) {
        this.uIDGenerator = UIDGenerator;
        this.commandGateway = commandGateway;
    }

    @RequestMapping(value = "/auto-match", method = RequestMethod.POST)
    @ApiOperation(value = "自动匹配", tags = "OPPOSITE_MOCK")
    public ResponseEntity<AutoMatchOrderCmd> autoMatch(@RequestBody AutoMatchOrderCmd cmd) {
        return RequestHandleUtils.execute(() -> {
            cmd.setId(cmd.getSkId());
            Object result = commandGateway.sendAndWait(cmd, 500, TimeUnit.SECONDS);

            return new ResponseEntity<>(cmd, HttpStatus.OK);
        });
    }

    @RequestMapping(value = "/receive-create", method = RequestMethod.POST)
    @ApiOperation(value = "对手方报价", tags = "OPPOSITE_MOCK")
    public ResponseEntity<PRCreateNewRecOrderCmd> receiveCreate(@RequestBody PRCreateNewRecOrderCmd cmd) {
        return RequestHandleUtils.execute(() -> {
            cmd.setId(cmd.getSkId());
            Object result = commandGateway.sendAndWait(cmd, 500, TimeUnit.SECONDS);

            return new ResponseEntity<>(cmd, HttpStatus.OK);
        });
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    @ApiOperation(value = "成交确认", tags = "OPPOSITE_MOCK")
    public ResponseEntity<ConfirmedFillCmd> fillConfirm(@RequestBody ConfirmedFillCmd cmd) {
        return RequestHandleUtils.execute(() -> {
            cmd.setId(cmd.getId());
            Object result = commandGateway.sendAndWait(cmd, 500, TimeUnit.SECONDS);

            return new ResponseEntity<>(cmd, HttpStatus.OK);
        });
    }

    @RequestMapping(value = "/reject-confirm", method = RequestMethod.POST)
    @ApiOperation(value = "拒绝确认", tags = "OPPOSITE_MOCK")
    public ResponseEntity<ConfirmRejectOrderCmd> confirmRejectOrder(@RequestBody ConfirmRejectOrderCmd cmd) {
        return RequestHandleUtils.execute(() -> {
            cmd.setId(cmd.getSkId());
            cmd.setRequestId(uIDGenerator.nextId());
            Object result = commandGateway.sendAndWait(cmd, 500, TimeUnit.SECONDS);

            return waitResponse(cmd.getRequestId());
        });
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    @ApiOperation(value = "对手方修改", tags = "OPPOSITE_MOCK")
    public ResponseEntity<PROppositeModifyOrderCmd> modify(@RequestBody PROppositeModifyOrderCmd cmd) {
        return RequestHandleUtils.execute(() -> {
            cmd.setId(cmd.getSkOriginId());
            Object result = commandGateway.sendAndWait(cmd, 500, TimeUnit.SECONDS);

            return new ResponseEntity<>(cmd, HttpStatus.OK);
        });
    }

}
