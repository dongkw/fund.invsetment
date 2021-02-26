package fund.investment.basic.trade.server.aggregate.state;

import fund.investment.basic.trade.api.command.ConfirmingFillCmd;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderAggrState implements OrderMatchState{

    private OrderState orderState;

    public void fillConfirming(ConfirmingFillCmd cmd) {
    }



}
