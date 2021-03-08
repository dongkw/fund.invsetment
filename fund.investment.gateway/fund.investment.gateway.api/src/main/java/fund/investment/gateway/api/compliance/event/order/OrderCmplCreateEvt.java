package fund.investment.gateway.api.compliance.event.order;

import fund.investment.basic.common.DomainEvent;
import fund.investment.basic.trade.api.valueobject.TradeElement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderCmplCreateEvt<T extends TradeElement> extends DomainEvent {
    private T tradeElement;

}
