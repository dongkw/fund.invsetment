package fund.investment.app.pledge.repo.server.controller.inquiry;

import fund.investment.app.pledge.repo.api.command.inquiry.PRCancelInquiryCmd;
import fund.investment.app.pledge.repo.api.command.inquiry.PRCreateInquiryCmd;
import fund.investment.app.pledge.repo.api.command.inquiry.PRUpdateInquiryCmd;
import fund.investment.basic.common.util.uid.UIDGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
@RequestMapping("/pledge/inquiry")
public class InquiryController {

    private final UIDGenerator UIDGenerator;

    private final CommandGateway commandGateway;

    @Autowired
    public InquiryController(UIDGenerator UIDGenerator, CommandGateway commandGateway) {
        this.UIDGenerator = UIDGenerator;
        this.commandGateway = commandGateway;
    }

    @RequestMapping(value = "/creation", method = RequestMethod.POST)
    @ApiOperation(value = "创建询价指令")
    public ResponseEntity create(@RequestBody PRCreateInquiryCmd cmd) {
        try {
            cmd.setId(cmd.getSkInquiry());
            Object result = commandGateway.send(cmd);
            return new ResponseEntity<>(cmd, HttpStatus.OK);
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

    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    @ApiOperation(value = "取消询价指令")
    public ResponseEntity confirm(@RequestBody PRCancelInquiryCmd cmd) {
        try {
            cmd.setId(cmd.getSkInquiry());
            cmd.setRequestId(UIDGenerator.nextId());
            commandGateway.send(cmd);
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (CommandExecutionException cmdE) {
            cmdE.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "修改询价指令")
    public ResponseEntity cancel(@RequestBody PRUpdateInquiryCmd cmd) {
        try {
            cmd.setId(cmd.getSkInquiry());
            commandGateway.send(cmd);

            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (CommandExecutionException cmdE) {
            cmdE.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
