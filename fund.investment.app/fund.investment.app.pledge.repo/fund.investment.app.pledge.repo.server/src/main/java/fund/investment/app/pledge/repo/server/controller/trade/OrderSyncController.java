package fund.investment.app.pledge.repo.server.controller.trade;

import fund.investment.app.pledge.repo.api.command.trade.*;
import fund.investment.app.pledge.repo.api.event.trade.PRChangeOrderPlacingEvt;
import fund.investment.app.pledge.repo.api.event.trade.PROrderUpdatedEvt;
import fund.investment.app.pledge.repo.server.utils.RequestHandleUtils;
import fund.investment.basic.common.http.response.CommonResponse;
import fund.investment.basic.common.util.controller.Async2SyncController;
import fund.investment.basic.common.util.uid.UIDGenerator;
import fund.investment.basic.trade.api.command.ConfirmOrderCmd;
import fund.investment.basic.trade.api.event.OrderChangedCancellingEvt;
import fund.investment.basic.trade.api.event.OrderConfirmedEvt;
import fund.investment.basic.trade.api.event.OrderDeleteEvt;
import fund.investment.gateway.api.compliance.event.order.pledge.OrderChangeModifyingEvt;
import fund.investment.gateway.api.compliance.event.order.pledge.PROrderDraModifyingEvt;
import fund.investment.gateway.api.compliance.event.order.pledge.PROrderPlacingEvt;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Api
@Slf4j
@RestController
@RequestMapping("/investment/order/sync")
public class OrderSyncController extends Async2SyncController {

    private final UIDGenerator uIDGenerator;

    private final CommandGateway commandGateway;

    @Autowired
    public OrderSyncController(UIDGenerator UIDGenerator, CommandGateway commandGateway) {
        this.uIDGenerator = UIDGenerator;
        this.commandGateway = commandGateway;
    }

    @RequestMapping(value = "/creation", method = RequestMethod.POST)
    @ApiOperation(value = "创建委托", tags = SwaggerTag.SYNC_TRADE)
    public ResponseEntity create(@RequestBody PRCreateOrderCmd cmd) {
        return RequestHandleUtils.execute(() -> {
            cmd.setId(cmd.getSkId());
            cmd.setRequestId(uIDGenerator.nextId());
            commandGateway.send(cmd);
            ResponseEntity responseEntity = waitResponse(cmd.getRequestId());
            log.debug("{}", responseEntity);
            return responseEntity;
        });
    }

    @EventHandler
    public void handler(OrderConfirmedEvt evt) {
        log.debug("response：交易确认事件{}", evt);
        CommonResponse response = new CommonResponse();
        response.setCode(String.valueOf(HttpStatus.OK.value()));
        response.setMessage("success");
        addResponse(evt.getRequestId(), response);
        log.debug("response：交易确认事件{}", evt);
    }


    @RequestMapping(value = "/confirmation", method = RequestMethod.POST)
    @ApiOperation(value = "确认委托", tags = SwaggerTag.SYNC_TRADE)
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
            return waitResponse(cmd.getRequestId());
        });
    }

    @RequestMapping(value = "/multi-place", method = RequestMethod.POST)
    @ApiOperation(value = "批量发送委托", tags = SwaggerTag.SYNC_TRADE)
    public ResponseEntity<List<PRPlaceOrderCmd>> place(@RequestBody List<PRPlaceOrderCmd> cmds) {
        try {
            Optional<PRPlaceOrderCmd> cmdOptional = cmds.stream().filter(cmd -> {
                cmd.setRequestId(uIDGenerator.nextId());
                commandGateway.send(cmd);
                ResponseEntity responseEntity = waitResponse(cmd.getRequestId());
                if (!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
                    return true;
                }
                return false;
            }).findFirst();
            if (cmdOptional.isPresent()) {
                ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @EventHandler
    public void handler(PROrderPlacingEvt evt) {
        log.debug("response：正报中事件{}", evt);
        CommonResponse response = new CommonResponse();
        response.setCode(String.valueOf(HttpStatus.OK.value()));
        response.setMessage("success");
        addResponse(evt.getRequestId(), response);
    }


    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ApiOperation(value = "删除未报委托", tags = SwaggerTag.SYNC_TRADE)
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
            cmd.setRequestId(uIDGenerator.nextId());
            commandGateway.send(cmd);
            return waitResponse(cmd.getRequestId());
        });
    }

    @EventHandler
    public void handler(OrderDeleteEvt evt) {
        log.debug("response：删除事件{}", evt);
        CommonResponse response = new CommonResponse();
        response.setCode(String.valueOf(HttpStatus.OK.value()));
        response.setMessage("success");
        addResponse(evt.getRequestId(), response);
    }


//    @RequestMapping(value = "/cancellation", method = RequestMethod.POST)
//    @ApiOperation(value = "撤销报单", tags = SwaggerTag.SYNC_TRADE)
//    public ResponseEntity<CancelOrderCmd> cancel(
//            @RequestParam String id,
//            @RequestParam String instructionId,
//            @RequestParam String tradeType,
//            @RequestParam String unitId,
//            @RequestParam Long cancelQuantity,
//            @RequestParam String userId,
//            @RequestParam String skId,
//            @RequestParam String cLastModifiedId
//    ) {
//        return RequestHandleUtils.execute(() -> {
//            PRCancelOrderCmd cmd = new PRCancelOrderCmd(id, instructionId, tradeType, unitId, cancelQuantity,
//                    userId,
//                    skId,
//                    cLastModifiedId,
//                    null);
//            cmd.setRequestId(uIDGenerator.nextId());
//            commandGateway.send(cmd);
//
//            return waitResponse(cmd.getRequestId());
//        });
//    }

    @EventHandler
    public void handler(OrderChangedCancellingEvt evt) {
        log.debug("response：取消事件{}", evt);
        CommonResponse response = new CommonResponse();
        response.setCode(String.valueOf(HttpStatus.OK.value()));
        response.setMessage("success");
        addResponse(evt.getRequestId(), response);
    }

    @RequestMapping(value = "/multi-cancellation", method = RequestMethod.POST)
    @ApiOperation(value = "批量撤销报单", tags = SwaggerTag.SYNC_TRADE)
    public ResponseEntity<List<PRCancelOrderCmd>> cancel(@RequestBody List<PRCancelOrderCmd> cmds) {
        PRCancelOrderCmd cmd = cmds.get(0);
        cmd.setRequestId(uIDGenerator.nextId());
        commandGateway.send(cmd);
        return waitResponse(cmd.getRequestId());
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    @ApiOperation(value = "修改委托", tags = SwaggerTag.SYNC_TRADE)
    public ResponseEntity<PRModifyOrderCmd> modify(@RequestBody PRModifyOrderCmd cmd) {
        return RequestHandleUtils.execute(() -> {
            cmd.setRequestId(uIDGenerator.nextId());
            commandGateway.sendAndWait(cmd, 500, TimeUnit.SECONDS);
            return waitResponse(cmd.getRequestId());
        });
    }

    @EventHandler
    public void handler(PROrderUpdatedEvt evt) {
        log.debug("response：更新事件{}", evt);
        CommonResponse response = new CommonResponse();
        response.setCode(String.valueOf(HttpStatus.OK.value()));
        response.setMessage("success");
        addResponse(evt.getRequestId(), response);
    }

    @EventHandler
    public void handler(PRChangeOrderPlacingEvt evt) {
        log.debug("response：修改事件{}", evt);
        CommonResponse response = new CommonResponse();
        response.setCode(String.valueOf(HttpStatus.OK.value()));
        response.setMessage("success");
        addResponse(evt.getRequestId(), response);
    }

    @EventHandler
    public void handler(OrderChangeModifyingEvt evt) {
        log.debug("response：修改事件{}", evt);
        CommonResponse response = new CommonResponse();
        response.setCode(String.valueOf(HttpStatus.OK.value()));
        response.setMessage("success");
        addResponse(evt.getRequestId(), response);
    }

    @EventHandler
    public void handler(PROrderDraModifyingEvt evt) {
        log.debug("response：修改事件{}", evt);
        CommonResponse response = new CommonResponse();
        response.setCode(String.valueOf(HttpStatus.OK.value()));
        response.setMessage("success");
        addResponse(evt.getRequestId(), response);
    }
}
