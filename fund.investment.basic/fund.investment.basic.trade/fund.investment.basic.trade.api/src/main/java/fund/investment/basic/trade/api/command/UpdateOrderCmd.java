package fund.investment.basic.trade.api.command;

import fund.investment.basic.trade.api.valueobject.TradeElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderCmd<T extends TradeElement> extends OrderCommand {

    private T tradeElement;
}
