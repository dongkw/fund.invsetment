package fund.investment.basic.trade.api.event;

import fund.investment.basic.trade.api.valueobject.TradeElement;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author dongkw
 * @Date 2021/3/4、3:31 下午
 **/
@Getter
@Setter
public class OrderCounterpartyUpdateEvt<T extends TradeElement> extends OrderEvent {
    private T tradeElement;
}
