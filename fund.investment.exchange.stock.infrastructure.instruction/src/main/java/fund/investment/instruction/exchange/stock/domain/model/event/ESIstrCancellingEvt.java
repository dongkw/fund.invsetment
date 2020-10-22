package fund.investment.instruction.exchange.stock.domain.model.event;

import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeType;
import fund.investment.infrastructure.instruction.domain.model.event.IstrCancellingEvt;
import fund.investment.infrastructure.instruction.domain.model.vo.Order;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
public class ESIstrCancellingEvt extends IstrCancellingEvt {

    public ESIstrCancellingEvt(TradeType tradeType, String id, String unitId, String securityCode, List<Order> orders) {
        super(tradeType, id, unitId, securityCode, orders);
    }
}
