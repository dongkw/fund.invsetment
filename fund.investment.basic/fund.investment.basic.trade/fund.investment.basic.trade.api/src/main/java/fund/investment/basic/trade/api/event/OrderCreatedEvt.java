package fund.investment.basic.trade.api.event;

import fund.investment.basic.trade.api.valueobject.TradeElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderCreatedEvt<T extends TradeElement> extends OrderEvent {

    private T tradeElement;

}
