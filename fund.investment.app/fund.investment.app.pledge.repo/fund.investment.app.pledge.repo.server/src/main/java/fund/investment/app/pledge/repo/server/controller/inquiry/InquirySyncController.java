package fund.investment.app.pledge.repo.server.controller.inquiry;

import fund.investment.app.pledge.repo.api.command.inquiry.PRCancelInquiryCmd;
import fund.investment.app.pledge.repo.api.command.inquiry.PRCreateInquiryCmd;
import fund.investment.app.pledge.repo.api.command.inquiry.PRUpdateInquiryCmd;
import fund.investment.app.pledge.repo.api.event.inquiry.PRInquiryUpdateConfirmedEvt;
import fund.investment.app.pledge.repo.api.event.inquiry.PRInquiryUpdateFailedEvt;
import fund.investment.basic.common.http.response.CommonResponse;
import fund.investment.basic.common.http.response.risk.RiskResultDtoResponse;
import fund.investment.basic.common.util.controller.Async2SyncController;
import fund.investment.basic.common.util.uid.UIDGenerator;
import fund.investment.basic.inquiry.api.event.InquiryCancelledEvt;
import fund.investment.basic.inquiry.api.event.InquiryConfirmedEvt;
import fund.investment.basic.inquiry.api.event.InquiryFailedEvt;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@Api
@Slf4j
@RestController
@RequestMapping("/pledge/inquiry/sync")
public class InquirySyncController extends Async2SyncController {

    private final fund.investment.basic.common.util.uid.UIDGenerator UIDGenerator;

    private final CommandGateway commandGateway;

    @Autowired
    public InquirySyncController(UIDGenerator UIDGenerator, CommandGateway commandGateway) {
        this.UIDGenerator = UIDGenerator;
        this.commandGateway = commandGateway;
    }

    @RequestMapping(value = "/creation", method = RequestMethod.POST)
    @ApiOperation(value = "创建询价指令")
    public ResponseEntity<CommonResponse> create(@RequestBody PRCreateInquiryCmd cmd) {

        cmd.setId(cmd.getSkInquiry());
        cmd.setRequestId(UIDGenerator.nextId());
        commandGateway.send(cmd);
        ResponseEntity<CommonResponse> responseEntity = waitResponse(cmd.getRequestId());
        log.info("response:{}", responseEntity);
        return responseEntity;
    }

    @EventHandler
    public void handler(InquiryConfirmedEvt evt) {
        CommonResponse response = new CommonResponse();
        response.setSuccess(true);
        addResponse(evt.getRequestId(), response);
    }

    @EventHandler
    public void handler(InquiryFailedEvt evt) {
        CommonResponse response = new CommonResponse();
        response.setSuccess(false);
        response.setRiskInfos(Arrays.asList(new RiskResultDtoResponse()));
        addResponse(evt.getRequestId(), response);
    }


    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    @ApiOperation(value = "取消询价指令")
    public ResponseEntity confirm(@RequestBody PRCancelInquiryCmd cmd) {
        try {
            cmd.setRequestId(UIDGenerator.nextId());
            cmd.setId(cmd.getSkInquiry());
//            cmd.setLastmodifiedId("kkkk");
//            cmd.setLastmodifiedTime(new Date());
            commandGateway.send(cmd);
            return waitResponse(cmd.getRequestId());
        } catch (CommandExecutionException cmdE) {
            cmdE.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @EventHandler
    public void handler(InquiryCancelledEvt evt) {
        CommonResponse response = new CommonResponse();
        response.setSuccess(true);
        addResponse(evt.getRequestId(), response);
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "修改询价指令")
    public ResponseEntity cancel(@RequestBody PRUpdateInquiryCmd cmd) {
        try {
            cmd.setRequestId(UIDGenerator.nextId());
            cmd.setId(cmd.getSkInquiry());
            commandGateway.send(cmd);

            return waitResponse(cmd.getRequestId());
        } catch (CommandExecutionException cmdE) {
            cmdE.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @EventHandler
    public void handler(PRInquiryUpdateConfirmedEvt evt) {
        CommonResponse response = new CommonResponse();
        response.setSuccess(true);
        addResponse(evt.getRequestId(), response);
    }

    @EventHandler
    public void handler(PRInquiryUpdateFailedEvt evt) {
        CommonResponse response = new CommonResponse();
        response.setSuccess(false);
        addResponse(evt.getRequestId(), response);
    }


}
