package fund.investment.app.pledge.repo.server.controller.trade;

import fund.investment.app.pledge.repo.server.utils.RequestHandleUtils;
import fund.investment.basic.common.http.response.CommonResponse;
import fund.investment.basic.common.util.controller.Async2SyncController;
import fund.investment.basic.common.util.uid.UIDGenerator;
import fund.investment.basic.trade.api.command.ConfirmingFillCmd;
import fund.investment.basic.trade.api.command.PRCancelMatchOrderCmd;
import fund.investment.basic.trade.api.command.PRMatchOrderCmd;
import fund.investment.basic.trade.api.command.RejectOrderCmd;
import fund.investment.basic.trade.api.event.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api
@Slf4j
@RestController
@RequestMapping("/investment/oppo/order/sync")
public class OppositeOrderSyncController extends Async2SyncController {

    private final UIDGenerator uIDGenerator;

    private final CommandGateway commandGateway;

    private final EventGateway eventGateway;

    @Autowired
    public OppositeOrderSyncController(UIDGenerator UIDGenerator, CommandGateway commandGateway, EventGateway eventGateway) {
        this.uIDGenerator = UIDGenerator;
        this.commandGateway = commandGateway;
        this.eventGateway = eventGateway;
    }

    @RequestMapping(value = "/fill", method = RequestMethod.POST)
    @ApiOperation(value = "成交确认中", tags = "OPPOSITE_ORDER")
    public ResponseEntity<?> fill(@RequestBody ConfirmingFillCmd cmd) {
        return RequestHandleUtils.execute(() -> {
            //cmd.setId(cmd.getId());
            cmd.setRequestId(uIDGenerator.nextId());
            commandGateway.send(cmd);
            return waitResponse(cmd.getRequestId());
        });
    }

    @RequestMapping(value = "/match", method = RequestMethod.POST)
    @ApiOperation(value = "手动匹配", tags = "OPPOSITE_ORDER")
    public ResponseEntity<?> match(@RequestBody PRMatchOrderCmd cmd) {
        return RequestHandleUtils.execute(() -> {
//            cmd.setId(cmd.getSkId());
            cmd.setRequestId(uIDGenerator.nextId());
            log.info("手动匹配cmd:{}", cmd);
            commandGateway.send(cmd);
            return waitResponse(cmd.getRequestId());
        });
    }

    @RequestMapping(value = "/cancel-match", method = RequestMethod.POST)
    @ApiOperation(value = "取消匹配", tags = "OPPOSITE_ORDER")
    public ResponseEntity<?> cancelMatch(@RequestBody PRCancelMatchOrderCmd cmd) {
        return RequestHandleUtils.execute(() -> {
            cmd.setRequestId(uIDGenerator.nextId());
            log.info("取消匹配cmd:{}", cmd);
            commandGateway.send(cmd);
            return waitResponse(cmd.getRequestId());
        });
    }

    @RequestMapping(value = "/reject-order", method = RequestMethod.POST)
    @ApiOperation(value = "拒绝报价", tags = "OPPOSITE_ORDER")
    public ResponseEntity<RejectOrderCmd> rejectOrder(@RequestBody RejectOrderCmd cmd) {
        return RequestHandleUtils.execute(() -> {
            cmd.setId(cmd.getId());
            cmd.setRequestId(uIDGenerator.nextId());
            commandGateway.send(cmd);
            return waitResponse(cmd.getRequestId());
        });
    }

    @EventHandler
    public void handler(OrderChangedRejectingEvt evt) {
        log.debug("response：拒绝确认事件{}", evt);
        CommonResponse response = new CommonResponse();
        response.setCode(String.valueOf(HttpStatus.OK.value()));
        response.setMessage("success");
        response.setSuccess(false);
        addResponse(evt.getRequestId(), response);
    }


    @EventHandler
    public void handler(PRMatchRemoteSuccessEvt evt) {
        CommonResponse response = new CommonResponse();
        response.setCode(String.valueOf(HttpStatus.OK.value()));
        response.setMessage("success");
        response.setSuccess(true);
        addResponse(evt.getRequestId(), response);
    }


    @EventHandler
    public void handler(PRMatchRemoteFailEvt evt) {
        CommonResponse response = new CommonResponse();
        response.setCode(String.valueOf(HttpStatus.OK.value()));
        response.setMessage(evt.getErrorMsg());
        response.setSuccess(false);
        addResponse(evt.getRequestId(), response);
    }

    @EventHandler
    public void handler(PRCancelMatchRemoteSuccessEvt evt) {
        CommonResponse response = new CommonResponse();
        response.setCode(String.valueOf(HttpStatus.OK.value()));
        response.setMessage("success");
        response.setSuccess(true);
        addResponse(evt.getRequestId(), response);
    }


    @EventHandler
    public void handler(PRCancelMatchRemoteFailEvt evt) {
        CommonResponse response = new CommonResponse();
        response.setCode(String.valueOf(HttpStatus.OK.value()));
        response.setMessage(evt.getErrorMsg());
        response.setSuccess(false);
        addResponse(evt.getRequestId(), response);
    }

    @EventHandler
    public void handler(FillConfirmingSuccessEvt evt) {
        CommonResponse response = new CommonResponse();
        response.setCode(String.valueOf(HttpStatus.OK.value()));
        response.setMessage("success");
        response.setSuccess(true);
        addResponse(evt.getRequestId(), response);
    }


}
