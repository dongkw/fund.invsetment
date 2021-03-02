package fund.investment.gateway.server.commandhandler.instruction.pledge;

import fund.investment.basic.common.http.response.pledgerepo.TradeInvestResponse;
import fund.investment.basic.common.http.response.risk.RiskResultResponse;
import fund.investment.gateway.api.compliance.command.instruction.CancelComplIstrCmd;
import fund.investment.gateway.api.compliance.command.instruction.RollbackCmplIstrCmd;
import fund.investment.gateway.api.compliance.command.instruction.RollbackCmplIstrUpdateCmd;
import fund.investment.gateway.api.compliance.command.instruction.pledge.PRInstrRiskControlCmd;
import fund.investment.gateway.api.compliance.command.instruction.pledge.PRInstrUpdateRiskCmd;
import fund.investment.gateway.api.compliance.event.instruction.*;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 质押式回购投资指令下达
 */
@Component
@Slf4j
public class PRInstructionCommandHandler {

    private final CommandGateway commandGateway;
    private final EventGateway eventGateway;


    @Autowired
    public PRInstructionCommandHandler(CommandGateway commandGateway,
                                       EventGateway eventGateway){
        this.commandGateway = commandGateway;
        this.eventGateway = eventGateway;

    }

    @CommandHandler
    public void handler(PRInstrRiskControlCmd cmd) {
        log.debug("接收到质押式风控校验命令：{}", cmd);
//        PledgeInstructionAddRequest request = new PledgeInstructionAddRequest();
//        request.setControlCmd(cmd);
//        request.setPayChannelCode(PledgeInstructionAddBizEnum.FD01.getPayChannelCode());
//        AjaxResult<InstructionAddResponse> ajaxResult = pledgeInstructionAddService.action(request);
        if (true) {
//            InstructionAddResponse data = ajaxResult.getData();
//            TradeInvestResponse investResponse = data.getInvestResponse();
            String cInstrSource = "investResponse.getCInstrSource()";
            String cSourceKey = "investResponse.getCSourceKey()";
            String cSourceNo = "investResponse.getCSourceNo()";

            IstrCmplSucceedEvt evt = new IstrCmplSucceedEvt(cmd.getId(), cmd.getId()+"", cInstrSource, cSourceKey, cSourceNo);
            eventGateway.publish(evt);
            log.debug("风控校验通过返回校验成功event:{}", evt);
        } else {
//            InstructionAddResponse data = ajaxResult.getData();
            List<RiskResultResponse> riskRiskInfos = new ArrayList<>();
//            if (!Objects.isNull(data)) {
//                riskRiskInfos = data.getRiskRiskInfos();
//            }
            IstrCmplFailedEvt evt = new IstrCmplFailedEvt(cmd.getId(), cmd.getId()+"", "ajaxResult.getCode()", "ajaxResult.getMessage()", riskRiskInfos);
            eventGateway.publish(evt);
            log.debug("风控校验不通过返回校验失败event:{}", evt);
        }
    }

    @CommandHandler
    public void handler(PRInstrUpdateRiskCmd cmd) {
        log.info("receive {}", cmd);
        log.debug("接收到修改质押式风控校验命令：{}", cmd);
//        PledgeInstructionModifyRequest request = new PledgeInstructionModifyRequest();
//        request.setControlCmd(cmd);
//        request.setPayChannelCode(PledgeInstructionModifyBizEnum.FD01.getPayChannelCode());
//        AjaxResult<InstructionModifyResponse> ajaxResult = pledgeInstructionModifyService.action(request);
        if (true) {
//            InstructionModifyResponse data = ajaxResult.getData();
//            TradeInvestResponse investResponse = data.getInvestResponse();
            String cInstrSource = "investResponse.getCInstrSource()";
            String cSourceKey = "investResponse.getCSourceKey()";
            String cSourceNo = "investResponse.getCSourceNo()";
            IstrCmplUpdateSucceedEvt evt = new IstrCmplUpdateSucceedEvt(cmd.getId(), cmd.getId()+"");
            evt.setChInstrSource(cInstrSource);
            evt.setChSourceKey(cSourceKey);
            evt.setChSourceNo(cSourceNo);
            eventGateway.publish(evt);
            log.debug("风控校验通过返回校验成功event:{}", evt);
        } else {
//            InstructionModifyResponse data = ajaxResult.getData();
            List<RiskResultResponse> riskRiskInfos = new ArrayList<>();
//            if (!Objects.isNull(data)) {
//                riskRiskInfos = data.getRiskRiskInfos();
//            }
            IstrCmplUpdateFailedEvt evt = new IstrCmplUpdateFailedEvt(cmd.getId(), cmd.getId()+"", "ajaxResult.getCode()", "ajaxResult.getMessage()", riskRiskInfos);
            eventGateway.publish(evt);
            log.debug("风控校验不通过返回校验失败event:{}", evt);
        }
    }

    @CommandHandler
    public void on(CancelComplIstrCmd cmd) {
        log.debug("接收到取消校验命令：{}", cmd);
//        InstructionCancelRequest request = new InstructionCancelRequest();
//        request.setCancelComplIstrCmd(cmd);
//        request.setPayChannelCode(InstructionCancelBizEnum.FD01.getPayChannelCode());
//        AjaxResult<InstructionCancelResponse> ajaxResult = instructionCancelService.action(request);
        if (true) {
//            InstructionCancelResponse data = ajaxResult.getData();
//            TradeInvestResponse investResponse = data.getInvest();
            String cInstrSource = "investResponse.getCInstrSource()";
            String cSourceKey = "investResponse.getCSourceKey()";
            String cSourceNo = "investResponse.getCSourceNo()";

            IstrCmplCancelledEvt evt = new IstrCmplCancelledEvt(cmd.getId(), cmd.getSkId());
//            evt.setChInstrSource(cInstrSource);
//            evt.setChSourceKey(cSourceKey);
//            evt.setChSourceNo(cSourceNo);
            eventGateway.publish(evt);
            log.debug("校验取消返回成功event:{}", evt);
        }
    }

    @CommandHandler
    public void handler(RollbackCmplIstrCmd cmd) {
        log.info("receive {}", cmd);
        IstrCmplRollbackedEvt evt = new IstrCmplRollbackedEvt(cmd.getId(), cmd.getIstrId());
        eventGateway.publish(evt);
        log.debug("send {}", evt);
    }

    @CommandHandler
    public void handler(RollbackCmplIstrUpdateCmd cmd) {
        log.info("receive {}", cmd);
        IstrCmplUpdateRollbackedEvt evt = new IstrCmplUpdateRollbackedEvt(cmd.getId(), cmd.getIstrId());
        eventGateway.publish(evt);
        log.debug("send {}", evt);
    }

}
