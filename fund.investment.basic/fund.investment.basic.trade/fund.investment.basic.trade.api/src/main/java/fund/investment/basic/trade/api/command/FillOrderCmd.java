package fund.investment.basic.trade.api.command;

import fund.investment.basic.trade.api.valueobject.TradeElement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FillOrderCmd<T extends TradeElement> extends OrderCommand {

    private T tradeElement;

}
