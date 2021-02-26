package fund.investment.basic.instruction.api.event;

import fund.investment.basic.instruction.api.enumeration.ApprovalStatus;
import fund.investment.basic.instruction.api.enumeration.DistributeStatus;
import fund.investment.basic.instruction.api.enumeration.TradeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IstrCancelledEvt extends InstructionEvent {

    private String unitId;

    private String description;

    private ApprovalStatus approvalStatus;

    private DistributeStatus distributeStatus;

    private String securityCode;

    private long quantity;

    private long cancelQuantity;

    private String cancelType;

    private String cancelMsg;

    private String chInstrSource;

    private String chSourceKey;

    private String chSourceNo;

    public IstrCancelledEvt(String id, Long requestId, TradeType tradeType, String userId, String skId, String skInstr, String cLastModifiedId, Date tLastModifiedTime, String unitId, String description, ApprovalStatus approvalStatus, DistributeStatus distributeStatus, String securityCode, long quantity, long cancelQuantity, String cancelType, String cancelMsg) {
        super(id, requestId, tradeType, userId, skId, skInstr, cLastModifiedId, tLastModifiedTime);
        this.unitId = unitId;
        this.description = description;
        this.approvalStatus = approvalStatus;
        this.distributeStatus = distributeStatus;
        this.securityCode = securityCode;
        this.quantity = quantity;
        this.cancelQuantity = cancelQuantity;
        this.cancelType = cancelType;
        this.cancelMsg = cancelMsg;
    }
}
