package fund.investment.basic.instruction.server.aggregate.status;

import fund.investment.basic.instruction.api.command.CreateConfirmIstrCmd;
import fund.investment.basic.instruction.api.command.CreateFailIstrCmd;
import fund.investment.basic.instruction.api.enumeration.InstructionStatus;
import fund.investment.basic.instruction.api.event.IstrConfirmedEvt;
import fund.investment.basic.instruction.api.event.IstrFailedEvt;
import fund.investment.basic.instruction.server.aggregate.InstructionAggregate;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;

import java.util.Date;

@Slf4j
public class CreatedInstructionState extends InstructionState {

    public CreatedInstructionState() {
        super(InstructionStatus.CREATED);
    }

    @Override
    public void createConfirm(InstructionAggregate instructionAggregate, CreateConfirmIstrCmd createConfirmIstrCmd) {
        IstrConfirmedEvt istrConfirmedEvt = new IstrConfirmedEvt();
        istrConfirmedEvt.setId(createConfirmIstrCmd.getId());
        istrConfirmedEvt.setRequestId(createConfirmIstrCmd.getRequestId());
        istrConfirmedEvt.setTradeType(createConfirmIstrCmd.getTradeType());
        istrConfirmedEvt.setSkId(instructionAggregate.getSkId());
        istrConfirmedEvt.setSkInstr(instructionAggregate.getSkInstr());
        istrConfirmedEvt.setChLastModifiedId(createConfirmIstrCmd.getChLastModifiedId());
        istrConfirmedEvt.setTsLastModifiedTime(new Date());
        istrConfirmedEvt.setInquiryResultId(createConfirmIstrCmd.getInquiryResultId());
        istrConfirmedEvt.setChInstrSource(createConfirmIstrCmd.getChInstrSource());
        istrConfirmedEvt.setChSourceKey(createConfirmIstrCmd.getChSourceKey());
        istrConfirmedEvt.setChSourceNo(createConfirmIstrCmd.getChSourceNo());
        AggregateLifecycle.apply(istrConfirmedEvt);
    }

    @Override
    public void createFail(InstructionAggregate instructionAggregate, CreateFailIstrCmd createFailIstrCmd) {
        IstrFailedEvt istrFailedEvt = new IstrFailedEvt();
        istrFailedEvt.setId(createFailIstrCmd.getId());
        istrFailedEvt.setRequestId(createFailIstrCmd.getRequestId());
        istrFailedEvt.setSkId(instructionAggregate.getSkId());
        istrFailedEvt.setSkInstr(instructionAggregate.getSkInstr());
        istrFailedEvt.setFailCode(createFailIstrCmd.getFailCode());
        istrFailedEvt.setFailMsg(createFailIstrCmd.getFailMsg());
        istrFailedEvt.setChLastModifiedId(createFailIstrCmd.getChLastModifiedId());
        istrFailedEvt.setTsLastModifiedTime(new Date());
        istrFailedEvt.setRiskRiskInfos(createFailIstrCmd.getRiskRiskInfos());
        AggregateLifecycle.apply(istrFailedEvt);
    }
}
