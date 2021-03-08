package fund.investment.basic.trade.api.command;

import fund.investment.basic.trade.api.valueobject.TradeElement;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author dongkw
 * @Date 2021/3/4、3:28 下午
 **/
@Getter
@Setter
public class CounterpartyUpdateCmd<T extends TradeElement> extends OrderCommand {

    private T tradeElement;
}
