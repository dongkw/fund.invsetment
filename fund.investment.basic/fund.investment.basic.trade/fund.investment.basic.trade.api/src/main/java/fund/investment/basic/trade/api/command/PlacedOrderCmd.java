package fund.investment.basic.trade.api.command;

import fund.investment.basic.trade.api.valueobject.TradeElement;
import lombok.Getter;
import lombok.Setter;

/**
 * 已报
 */
@Getter
@Setter
public class PlacedOrderCmd<T extends TradeElement> extends OrderCommand {

    private T tradeElement;
}
