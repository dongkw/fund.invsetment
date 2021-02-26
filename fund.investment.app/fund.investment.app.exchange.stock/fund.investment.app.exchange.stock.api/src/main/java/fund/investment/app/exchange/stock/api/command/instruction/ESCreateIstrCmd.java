package fund.investment.app.exchange.stock.api.command.instruction;

import fund.investment.basic.instruction.api.command.CreateIstrCmd;
import fund.investment.basic.instruction.api.enumeration.TradeSide;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ESCreateIstrCmd extends CreateIstrCmd {

    private String price;

    private TradeSide side;

    private long amount;


}
