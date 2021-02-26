package fund.investment.app.pledge.repo.server.controller.trade;

import fund.investment.app.pledge.repo.api.command.trade.PRCancelOrderCmd;
import fund.investment.app.pledge.repo.api.command.trade.PRCreateOrderCmd;
import fund.investment.app.pledge.repo.api.command.trade.PRDeleteOrderCmd;
import fund.investment.app.pledge.repo.api.command.trade.PRModifyOrderCmd;
import fund.investment.app.pledge.repo.server.utils.RequestHandleUtils;
import fund.investment.basic.common.util.controller.Async2SyncController;
import fund.investment.basic.common.util.uid.UIDGenerator;
import fund.investment.basic.trade.api.command.CancelOrderCmd;
import fund.investment.basic.trade.api.command.ConfirmOrderCmd;
import fund.investment.basic.trade.api.command.PlaceOrderCmd;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Api
@RestController
@RequestMapping("/investment/order")
public class OrderController extends Async2SyncController {

    private final UIDGenerator uIDGenerator;

    private final CommandGateway commandGateway;

    @Autowired
    public OrderController(UIDGenerator UIDGenerator, CommandGateway commandGateway) {
        this.uIDGenerator = UIDGenerator;
        this.commandGateway = commandGateway;
    }

    @RequestMapping(value = "/creation", method = RequestMethod.POST)
    @ApiOperation(value = "创建委托", tags = SwaggerTag.TRADE)
    public ResponseEntity create(@RequestBody PRCreateOrderCmd cmd) {
        return RequestHandleUtils.execute(() -> {
            cmd.setId(cmd.getSkId());
            cmd.setRequestId(uIDGenerator.nextId());
            commandGateway.send(cmd);
            return waitResponse(cmd.getRequestId());
        });
    }

    @RequestMapping(value = "/confirmation", method = RequestMethod.POST)
    @ApiOperation(value = "确认委托", tags = SwaggerTag.TRADE)
    public ResponseEntity<ConfirmOrderCmd> confirm(
            @RequestParam String id,
            @RequestParam String instructionId,
            @RequestParam String tradeType,
            @RequestParam String userId,
            @RequestParam String skId,
            @RequestParam String cLastModifiedId
    ) {
        return RequestHandleUtils.execute(() -> {
            ConfirmOrderCmd cmd = new ConfirmOrderCmd(id,
                    tradeType,
                    instructionId,
                    userId,
                    skId,
                    cLastModifiedId,
                    null);
            cmd.setRequestId(uIDGenerator.nextId());
            commandGateway.sendAndWait(cmd);
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        });
    }

    @RequestMapping(value = "/multi-place", method = RequestMethod.POST)
    @ApiOperation(value = "批量发送委托", tags = SwaggerTag.TRADE)
    public ResponseEntity<List<PlaceOrderCmd>> place(@RequestBody List<PlaceOrderCmd> cmds) {
        try {
            cmds.stream().forEach(cmd -> commandGateway.sendAndWait(cmd));
            return new ResponseEntity<>(cmds, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ApiOperation(value = "删除未报委托", tags = SwaggerTag.TRADE)
    public ResponseEntity<PRDeleteOrderCmd> delete(
            @RequestParam String id,
            @RequestParam String instructionId,
            @RequestParam String tradeType,
            @RequestParam String unitId,
            @RequestParam Long cancelQuantity,
            @RequestParam String userId,
            @RequestParam String skId,
            @RequestParam String cLastModifiedId
    ) {
        return RequestHandleUtils.execute(() -> {
            PRDeleteOrderCmd cmd = new PRDeleteOrderCmd(id, instructionId, tradeType, unitId, cancelQuantity,
                    userId,
                    skId,
                    cLastModifiedId,
                    null);
            commandGateway.send(cmd);

            return new ResponseEntity<>(cmd, HttpStatus.OK);
        });
    }

    @RequestMapping(value = "/cancellation", method = RequestMethod.POST)
    @ApiOperation(value = "撤销报单", tags = SwaggerTag.TRADE)
    public ResponseEntity<CancelOrderCmd> cancel(
            @RequestParam String id,
            @RequestParam String instructionId,
            @RequestParam String tradeType,
            @RequestParam String unitId,
            @RequestParam Long cancelQuantity,
            @RequestParam String userId,
            @RequestParam String skId,
            @RequestParam String cLastModifiedId
    ) {
        return RequestHandleUtils.execute(() -> {
            PRCancelOrderCmd cmd = new PRCancelOrderCmd(id, instructionId, tradeType, unitId, cancelQuantity,
                    userId,
                    skId,
                    cLastModifiedId,
                    null);
            commandGateway.send(cmd);

            return new ResponseEntity<>(cmd, HttpStatus.OK);
        });
    }

    @RequestMapping(value = "/multi-cancellation", method = RequestMethod.POST)
    @ApiOperation(value = "批量撤销报单", tags = SwaggerTag.TRADE)
    public ResponseEntity<List<PRCancelOrderCmd>> cancel(@RequestBody List<PRCancelOrderCmd> cmds) {
        try {
            cmds.forEach(commandGateway::send);
            return new ResponseEntity<>(cmds, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    @ApiOperation(value = "修改委托", tags = SwaggerTag.TRADE)
    public ResponseEntity<PRModifyOrderCmd> modify(@RequestBody PRModifyOrderCmd cmd) {
        return RequestHandleUtils.execute(() -> {
            if (StringUtils.isEmpty(cmd.getSkOriginId())) {
                cmd.setId(cmd.getSkId());
            } else {
                cmd.setId(cmd.getSkOriginId());
            }
            Object result = commandGateway.sendAndWait(cmd, 500, TimeUnit.SECONDS);
            Assert.notNull(result, "command dispatching timeout");

            return new ResponseEntity<>(cmd, HttpStatus.OK);
        });
    }



}
