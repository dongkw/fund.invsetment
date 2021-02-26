package fund.investment.app.pledge.repo.server.controller.inqresult;

import fund.investment.app.pledge.repo.api.command.inqresult.PRCancelInquiryResultCmd;
import fund.investment.app.pledge.repo.api.command.inqresult.PRCommitInquiryResultCmd;
import fund.investment.app.pledge.repo.api.command.inqresult.PRUpdateInquiryResultCmd;
import fund.investment.app.pledge.repo.api.event.inqresult.PRInquiryResultCreatedEvt;
import fund.investment.app.pledge.repo.api.event.inqresult.PRInquiryResultUpdateConfirmedEvt;
import fund.investment.app.pledge.repo.api.event.inqresult.PRInquiryResultUpdateFailedEvt;
import fund.investment.app.pledge.repo.api.event.inqresult.PRInquiryResultUpdateSuccEvt;
import fund.investment.basic.common.http.response.CommonResponse;
import fund.investment.basic.common.http.response.risk.RiskResultDtoResponse;
import fund.investment.basic.common.util.controller.Async2SyncController;
import fund.investment.basic.common.util.uid.UIDGenerator;
import fund.investment.basic.inqresult.api.command.BackInquiryResultCmd;
import fund.investment.basic.inqresult.api.event.InquiryResultCancelledEvt;
import fund.investment.basic.inqresult.api.event.InquiryResultConfirmedEvt;
import fund.investment.basic.inqresult.api.event.InquiryResultFailedEvt;
import fund.investment.basic.inqresult.api.event.InquiryResultRejectEvt;
import fund.investment.gateway.api.compliance.command.inqresult.RejectCmplInqResultCmd;
import fund.investment.gateway.api.compliance.command.inqresult.pledge.PRDirectCmplInqResultCmd;
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

import java.util.Collections;

@Api
@RestController
@RequestMapping("/pledge-repo/inqresult/sync")
@Slf4j
public class InqResultSyncController extends Async2SyncController {

    private final fund.investment.basic.common.util.uid.UIDGenerator UIDGenerator;

    private final CommandGateway commandGateway;

    @Autowired
    public InqResultSyncController(UIDGenerator UIDGenerator, CommandGateway commandGateway) {
        this.UIDGenerator = UIDGenerator;
        this.commandGateway = commandGateway;
    }

    @RequestMapping(value = "/creation", method = RequestMethod.POST)
    @ApiOperation(value = "创建询价结果")
    public ResponseEntity create(@RequestBody PRDirectCmplInqResultCmd cmd) {
        try {
            cmd.setId(cmd.getSkId());
            cmd.setRequestId(UIDGenerator.nextId());
            commandGateway.send(cmd);
            ResponseEntity<CommonResponse> responseEntity = waitResponse(cmd.getRequestId());
            log.info("response:{}", responseEntity);
            return responseEntity;
        } catch (IllegalArgumentException illE) {
            illE.printStackTrace();
            return new ResponseEntity<>(HttpStatus.REQUEST_TIMEOUT);
        } catch (CommandExecutionException cmdE) {
            cmdE.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @EventHandler
    public void handler(PRInquiryResultCreatedEvt evt) {
        CommonResponse response = new CommonResponse();
        response.setSuccess(true);
        addResponse(evt.getRequestId(), response);
    }


    @RequestMapping(value = "/commit", method = RequestMethod.POST)
    @ApiOperation(value = "提交询价结果")
    public ResponseEntity commit(@RequestBody PRCommitInquiryResultCmd cmd) {
        try {
            cmd.setRequestId(UIDGenerator.nextId());
            cmd.setId(cmd.getSkId());
            commandGateway.send(cmd);
            ResponseEntity<CommonResponse> responseEntity = waitResponse(cmd.getRequestId());
            log.info("response:{}", responseEntity);
            return responseEntity;
        } catch (IllegalArgumentException illE) {
            illE.printStackTrace();
            return new ResponseEntity<>(HttpStatus.REQUEST_TIMEOUT);
        } catch (CommandExecutionException cmdE) {
            cmdE.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @EventHandler
    public void handler(InquiryResultFailedEvt evt) {
        CommonResponse response = new CommonResponse();
        RiskResultDtoResponse riskResultDtoResponse = new RiskResultDtoResponse();
        riskResultDtoResponse.setRiskInfos(evt.getRiskInfos());
        response.setRiskInfos(Collections.singletonList(riskResultDtoResponse));
        response.setSuccess(false);
        addResponse(evt.getRequestId(), response);
    }

    @EventHandler
    public void handler(InquiryResultConfirmedEvt evt) {
        CommonResponse response = new CommonResponse();
        response.setSuccess(true);
        addResponse(evt.getRequestId(), response);
    }


    @RequestMapping(value = "/back", method = RequestMethod.POST)
    @ApiOperation(value = "询价结果打回")
    public ResponseEntity back(@RequestBody BackInquiryResultCmd cmd) {
        try {
            cmd.setRequestId(UIDGenerator.nextId());
            cmd.setId(cmd.getSkId());

            commandGateway.send(cmd);
            ResponseEntity<CommonResponse> responseEntity = waitResponse(cmd.getRequestId());
            log.info("response:{}", responseEntity);
            return responseEntity;
        } catch (IllegalArgumentException illE) {
            illE.printStackTrace();
            return new ResponseEntity<>(HttpStatus.REQUEST_TIMEOUT);
        } catch (CommandExecutionException cmdE) {
            cmdE.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/reject", method = RequestMethod.POST)
    @ApiOperation(value = "询价结果拒绝")
    public ResponseEntity back(@RequestBody RejectCmplInqResultCmd cmd) {
        try {
            cmd.setRequestId(UIDGenerator.nextId());
            cmd.setId(cmd.getSkId());

            commandGateway.send(cmd);
            ResponseEntity<CommonResponse> responseEntity = waitResponse(cmd.getRequestId());
            log.info("response:{}", responseEntity);
            return responseEntity;
        } catch (IllegalArgumentException illE) {
            illE.printStackTrace();
            return new ResponseEntity<>(HttpStatus.REQUEST_TIMEOUT);
        } catch (CommandExecutionException cmdE) {
            cmdE.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @EventHandler
    public void handler(InquiryResultRejectEvt evt) {
        CommonResponse response = new CommonResponse();
        response.setSuccess(true);
        addResponse(evt.getRequestId(), response);
    }

    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    @ApiOperation(value = "取消询价结果")
    public ResponseEntity confirm(@RequestBody PRCancelInquiryResultCmd cmd) {
        try {
            cmd.setRequestId(UIDGenerator.nextId());
            cmd.setId(cmd.getSkId());
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
    public void handler(InquiryResultCancelledEvt evt) {
        CommonResponse response = new CommonResponse();
        response.setSuccess(true);
        addResponse(evt.getRequestId(), response);
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "修改询价结果")
    public ResponseEntity cancel(@RequestBody PRUpdateInquiryResultCmd cmd) {
        try {
            cmd.setId(cmd.getSkId());
            cmd.setRequestId(UIDGenerator.nextId());
            commandGateway.send(cmd);
            ResponseEntity<CommonResponse> responseEntity = waitResponse(cmd.getRequestId());
            log.info("response:{}", responseEntity);
            return responseEntity;
        } catch (CommandExecutionException cmdE) {
            cmdE.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @EventHandler
    public void handler(PRInquiryResultUpdateSuccEvt evt) {
        CommonResponse response = new CommonResponse();
        response.setSuccess(true);
        addResponse(evt.getRequestId(), response);
    }


    @EventHandler
    public void handler(PRInquiryResultUpdateConfirmedEvt evt) {
        CommonResponse response = new CommonResponse();
        response.setSuccess(true);
        addResponse(evt.getRequestId(), response);
    }


    @EventHandler
    public void handler(PRInquiryResultUpdateFailedEvt evt) {
        CommonResponse response = new CommonResponse();
        response.setSuccess(false);
        RiskResultDtoResponse riskResultDtoResponse = new RiskResultDtoResponse();
        riskResultDtoResponse.setRiskInfos(evt.getRiskInfos());
        response.setRiskInfos(Collections.singletonList(riskResultDtoResponse));
        addResponse(evt.getRequestId(), response);
    }


}
