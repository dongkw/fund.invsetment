package fund.investment.basic.instruction.server.aggregate.status;

import fund.investment.basic.instruction.api.command.ApproveIstrCmd;
import fund.investment.basic.instruction.api.enumeration.InstructionStatus;
import fund.investment.basic.instruction.api.event.IstrPassedEvt;
import fund.investment.basic.instruction.server.aggregate.InstructionAggregate;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;

@Slf4j
public class ConfirmedInstructionState extends CancelableInstructionState {

    public ConfirmedInstructionState() {
        super(InstructionStatus.CONFIRMED);
    }

    @Override
    public void aprvPass(InstructionAggregate instructionAggregate, ApproveIstrCmd approveIstrCmd) {
        IstrPassedEvt istrPassedEvt = new IstrPassedEvt();
        istrPassedEvt.setTradeType(approveIstrCmd.getTradeType());
        istrPassedEvt.setId(approveIstrCmd.getId());
        istrPassedEvt.setRequestId(approveIstrCmd.getRequestId());
        istrPassedEvt.setSkId(instructionAggregate.getSkId());
        istrPassedEvt.setSkInstr(instructionAggregate.getSkInstr());

        istrPassedEvt.setChFlowApproveStatus(approveIstrCmd.getChFlowApproveStatus());

        istrPassedEvt.setChLastModifiedId(approveIstrCmd.getChLastModifiedId());
        istrPassedEvt.setTsLastModifiedTime(approveIstrCmd.getTsLastModifiedTime());
        AggregateLifecycle.apply(istrPassedEvt);
    }
}
