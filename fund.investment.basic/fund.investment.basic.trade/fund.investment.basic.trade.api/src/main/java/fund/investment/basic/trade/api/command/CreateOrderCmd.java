package fund.investment.basic.trade.api.command;

import fund.investment.basic.trade.api.valueobject.SourceType;
import fund.investment.basic.trade.api.valueobject.TradeElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderCmd<T extends TradeElement> extends OrderCommand {

    private T tradeElement;

    private SourceType sourceType;
}
