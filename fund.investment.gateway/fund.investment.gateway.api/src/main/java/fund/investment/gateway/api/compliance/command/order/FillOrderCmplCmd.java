package fund.investment.gateway.api.compliance.command.order;

import fund.investment.basic.common.DomainCommand;
import fund.investment.basic.trade.api.valueobject.TradeElement;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author dongkw
 * @Date 2021/3/9、9:17 上午
 **/
@Getter
@Setter
public class FillOrderCmplCmd<T extends TradeElement> extends DomainCommand {
    private T tradeElement;
}
