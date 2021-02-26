package fund.investment.app.pledge.repo.api.command.instruction;

import fund.investment.basic.instruction.api.command.CreateIstrOrderCmd;
import fund.investment.basic.instruction.api.valueobject.OrderTradeElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PRCreateIstrOrderCmd extends CreateIstrOrderCmd {

    @Override
    public OrderTradeElement getOrderTradeElement() {
        return null;
    }
}

