package fund.investment.infrastructure.instruction.domain.model.event;

import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeType;
import fund.investment.infrastructure.instruction.domain.model.vo.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @Author dongkw
 * @Date 2020/10/10、10:08 上午
 **/
@Getter
@Setter
@NoArgsConstructor
public class IstrCancellingEvt extends InstructionEvent {

    private String unitId;

    private String securityCode;

    private List<Order> orders;

    public IstrCancellingEvt(TradeType tradeType, String id, String unitId, String securityCode, List<Order> orders) {
        super(tradeType, id);
        this.unitId = unitId;
        this.securityCode = securityCode;
        this.orders = orders;
    }
}
