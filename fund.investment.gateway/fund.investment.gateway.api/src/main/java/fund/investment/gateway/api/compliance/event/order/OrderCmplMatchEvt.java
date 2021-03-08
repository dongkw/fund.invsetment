package fund.investment.gateway.api.compliance.event.order;

import fund.investment.basic.common.DomainEvent;
import fund.investment.basic.trade.api.valueobject.TradeElement;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author dongkw
 * @Date 2021/3/8、1:48 下午
 **/
@Getter
@Setter
public class OrderCmplMatchEvt<T extends TradeElement> extends DomainEvent {
    private T tradeElement;
}
