package fund.investment.basic.trade.api.event;


import fund.investment.basic.trade.api.valueobject.TradeElement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderPlacedEvt<T extends TradeElement> extends OrderEvent {

    private T tradeElement;

}
