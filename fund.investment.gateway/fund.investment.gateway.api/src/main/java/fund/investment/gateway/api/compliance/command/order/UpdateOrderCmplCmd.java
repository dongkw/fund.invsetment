package fund.investment.gateway.api.compliance.command.order;

import fund.investment.basic.common.DomainCommand;
import fund.investment.basic.trade.api.valueobject.TradeElement;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author dongkw
 * @Date 2021/3/8、2:11 下午
 **/
@Getter
@Setter
public class UpdateOrderCmplCmd<T extends TradeElement> extends DomainCommand {
    private T tradeElement;
}
