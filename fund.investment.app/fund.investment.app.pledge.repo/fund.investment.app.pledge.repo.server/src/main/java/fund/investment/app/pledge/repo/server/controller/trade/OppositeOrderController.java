package fund.investment.app.pledge.repo.server.controller.trade;

import fund.investment.app.pledge.repo.server.utils.RequestHandleUtils;
import fund.investment.basic.common.util.controller.Async2SyncController;
import fund.investment.basic.common.util.uid.UIDGenerator;
import fund.investment.basic.trade.api.command.CancelMatchOrderCmd;
import fund.investment.basic.trade.api.command.ConfirmingFillCmd;
import fund.investment.basic.trade.api.command.MatchOrderCmd;
import fund.investment.basic.trade.api.command.RejectOrderCmd;
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
@RequestMapping("/investment/opposite")
public class OppositeOrderController extends Async2SyncController {

    private final UIDGenerator uIDGenerator;

    private final CommandGateway commandGateway;

    @Autowired
    public OppositeOrderController(UIDGenerator UIDGenerator, CommandGateway commandGateway) {
        this.uIDGenerator = UIDGenerator;
        this.commandGateway = commandGateway;
    }

    @RequestMapping(value = "/match", method = RequestMethod.POST)
    @ApiOperation(value = "手动匹配", tags = "OPPOSITE_ORDER")
    public ResponseEntity<MatchOrderCmd> match(@RequestBody MatchOrderCmd cmd) {
        return RequestHandleUtils.execute(() -> {
            cmd.setId(cmd.getInstrSkInstr());
            Object result = commandGateway.sendAndWait(cmd, 500, TimeUnit.SECONDS);

            return new ResponseEntity<>(cmd, HttpStatus.OK);
        });
    }

    @RequestMapping(value = "/cancel-match", method = RequestMethod.POST)
    @ApiOperation(value = "取消匹配", tags = "OPPOSITE_ORDER")
    public ResponseEntity<CancelMatchOrderCmd> cancelMatch(@RequestBody CancelMatchOrderCmd cmd) {
        return RequestHandleUtils.execute(() -> {
            cmd.setId(cmd.getId());
            Object result = commandGateway.sendAndWait(cmd, 500, TimeUnit.SECONDS);

            return new ResponseEntity<>(cmd, HttpStatus.OK);
        });
    }

    @RequestMapping(value = "/placing", method = RequestMethod.POST)
    @ApiOperation(value = "成交确认中", tags = "OPPOSITE_ORDER")
    public ResponseEntity<ConfirmingFillCmd> cancelMatch(@RequestBody ConfirmingFillCmd cmd) {
        return RequestHandleUtils.execute(() -> {
            cmd.setId(cmd.getId());
            Object result = commandGateway.sendAndWait(cmd, 500, TimeUnit.SECONDS);

            return new ResponseEntity<>(cmd, HttpStatus.OK);
        });
    }

    @RequestMapping(value = "/reject-order", method = RequestMethod.POST)
    @ApiOperation(value = "拒绝报价", tags = "OPPOSITE_ORDER")
    public ResponseEntity<RejectOrderCmd> rejectOrder(@RequestBody RejectOrderCmd cmd) {
        return RequestHandleUtils.execute(() -> {
            cmd.setId(cmd.getId());
            cmd.setRequestId(uIDGenerator.nextId());
            Object result = commandGateway.sendAndWait(cmd, 500, TimeUnit.SECONDS);

            return waitResponse(cmd.getRequestId());
        });
    }

}
