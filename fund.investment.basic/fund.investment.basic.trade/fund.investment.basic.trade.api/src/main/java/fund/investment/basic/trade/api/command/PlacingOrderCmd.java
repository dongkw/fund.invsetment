package fund.investment.basic.trade.api.command;

import fund.investment.basic.trade.api.valueobject.TradeElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * 正报
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlacingOrderCmd<T extends TradeElement> extends OrderCommand {

    private T tradeElement;
}
