package fund.investment.app.pledge.repo.server.controller.inqresult;

import fund.investment.app.pledge.repo.api.command.inqresult.PRCancelInquiryResultCmd;
import fund.investment.app.pledge.repo.api.command.inqresult.PRCreateInquiryResultCmd;
import fund.investment.app.pledge.repo.api.command.inqresult.PRUpdateInquiryResultCmd;
import fund.investment.basic.common.util.uid.UIDGenerator;
import fund.investment.basic.inqresult.api.command.CommitInqResultCmd;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Api
@RestController
@RequestMapping("/pledge-repo/inqresult")
public class InqResultController {

    private final UIDGenerator UIDGenerator;

    private final CommandGateway commandGateway;

    @Autowired
    public InqResultController(UIDGenerator UIDGenerator, CommandGateway commandGateway) {
        this.UIDGenerator = UIDGenerator;
        this.commandGateway = commandGateway;
    }

    @RequestMapping(value = "/creation", method = RequestMethod.POST)
    @ApiOperation(value = "创建询价结果")
    public ResponseEntity create(@RequestBody PRCreateInquiryResultCmd cmd) {
        try {
            cmd.setId(cmd.getSkId());
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
    @RequestMapping(value = "/commit", method = RequestMethod.POST)
    @ApiOperation(value = "提交询价结果")
    public ResponseEntity commit(@RequestBody CommitInqResultCmd cmd) {
        try {
            cmd.setId(cmd.getSkId());
            cmd.setChLastModifiedId("kkkk");
            cmd.setTsLastModifiedTime(new Date());
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
    @ApiOperation(value = "取消询价结果")
    public ResponseEntity confirm(@RequestParam String id) {
        try {
            PRCancelInquiryResultCmd cmd = new PRCancelInquiryResultCmd(id);
            cmd.setChLastModifiedId("kkkk");
            cmd.setTsLastModifiedTime(new Date());
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
    @ApiOperation(value = "修改询价结果")
    public ResponseEntity cancel(@RequestBody PRUpdateInquiryResultCmd cmd) {
        try {
            cmd.setId(cmd.getSkId());
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
