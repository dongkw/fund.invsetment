package fund.investment.basic.instruction.server.aggregate.status;


import fund.investment.basic.instruction.api.command.DistributeIstrCmd;
import fund.investment.basic.instruction.api.enumeration.InstructionStatus;
import fund.investment.basic.instruction.api.event.IstrPendingEvt;
import fund.investment.basic.instruction.server.aggregate.InstructionAggregate;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;

@Slf4j
public class PassedInstructionState extends CancelableInstructionState {

    public PassedInstructionState() {
        super(InstructionStatus.APRV_PASS);
    }

    @Override
    public void distribute(InstructionAggregate instructionAggregate, DistributeIstrCmd distributeIstrCmd) {
        IstrPendingEvt istrPendingEvt = new IstrPendingEvt();
        istrPendingEvt.setId(distributeIstrCmd.getId());
        istrPendingEvt.setRequestId(distributeIstrCmd.getRequestId());
        istrPendingEvt.setSkId(instructionAggregate.getSkId());
        istrPendingEvt.setSkInstr(instructionAggregate.getSkInstr());

        istrPendingEvt.setChInsDispStatus(distributeIstrCmd.getChInsDispStatus());

        istrPendingEvt.setChLastModifiedId(distributeIstrCmd.getChLastModifiedId());
        istrPendingEvt.setTsLastModifiedTime(distributeIstrCmd.getTsLastModifiedTime());
        istrPendingEvt.setUserId(distributeIstrCmd.getUserId());
        AggregateLifecycle.apply(istrPendingEvt);
    }
}
