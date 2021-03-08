package fund.investment.gateway.api.compliance.command.order;

import fund.investment.basic.common.DomainCommand;
import fund.investment.basic.trade.api.valueobject.TradeElement;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PlacingOrderCmplCmd<T extends TradeElement> extends DomainCommand {
    private T tradeElement;
}
