package fund.investment.basic.instruction.api.event;

import fund.investment.basic.instruction.api.enumeration.TradeType;
import fund.investment.basic.instruction.api.valueobject.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IstrCancellingEvt extends InstructionEvent {

    private String unitId;

    private String securityCode;

    private List<Order> orders;

    private String cancelType;

    private String cancelMsg;

    private String chInstrSource;

    private String chSourceKey;

    private String chSourceNo;

    public IstrCancellingEvt(String id, Long requestId, TradeType tradeType, String userId, String skId, String skInstr, String cLastModifiedId, Date tLastModifiedTime, String unitId, String securityCode, List<Order> orders, String cancelType, String cancelMsg) {
        super(id, requestId, tradeType, userId, skId, skInstr, cLastModifiedId, tLastModifiedTime);
        this.unitId = unitId;
        this.securityCode = securityCode;
        this.orders = orders;
        this.cancelType = cancelType;
        this.cancelMsg = cancelMsg;
    }
}
