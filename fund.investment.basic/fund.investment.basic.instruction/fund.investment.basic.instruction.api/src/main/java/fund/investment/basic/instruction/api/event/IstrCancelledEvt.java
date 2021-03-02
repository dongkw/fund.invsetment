package fund.investment.basic.instruction.api.event;

import fund.investment.basic.instruction.api.enumeration.ApprovalStatus;
import fund.investment.basic.instruction.api.enumeration.DistributeStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IstrCancelledEvt extends InstructionEvent {

    private String description;

    private ApprovalStatus approvalStatus;

    private DistributeStatus distributeStatus;

    private String securityCode;

    private String cancelType;

    private String cancelMsg;


}
