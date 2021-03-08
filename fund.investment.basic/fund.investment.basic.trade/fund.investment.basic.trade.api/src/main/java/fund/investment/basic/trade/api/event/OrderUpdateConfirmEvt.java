package fund.investment.basic.trade.api.event;

import fund.investment.basic.trade.api.valueobject.TradeElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderUpdateConfirmEvt<T extends TradeElement> extends OrderEvent {

    private T tradeElement;
}
