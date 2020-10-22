package fund.investment.infrastructure.instruction.domain.model.event;

import fund.investment.infrastructure.instruction.domain.model.enumeration.ApprovalStatus;
import fund.investment.infrastructure.instruction.domain.model.enumeration.DistributeStatus;
import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IstrCancelledEvt extends InstructionEvent {

    private String unitId;

    private String description;

    private ApprovalStatus approvalStatus;

    private DistributeStatus distributeStatus;

    private String securityCode;

    private long quantity;

    private long cancelQuantity;

    public IstrCancelledEvt(TradeType tradeType, String id, String unitId, String description, ApprovalStatus approvalStatus, DistributeStatus distributeStatus, String securityCode, long quantity, long cancelQuantity) {
        super(tradeType, id);
        this.unitId = unitId;
        this.description = description;
        this.approvalStatus = approvalStatus;
        this.distributeStatus = distributeStatus;
        this.securityCode = securityCode;
        this.quantity = quantity;
        this.cancelQuantity = cancelQuantity;
    }
}
