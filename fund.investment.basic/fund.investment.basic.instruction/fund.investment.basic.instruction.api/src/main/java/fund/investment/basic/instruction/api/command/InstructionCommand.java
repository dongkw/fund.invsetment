package fund.investment.basic.instruction.api.command;


import fund.investment.basic.common.DomainCommand;
import fund.investment.basic.instruction.api.enumeration.TradeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstructionCommand extends DomainCommand {

    private TradeType tradeType;

}
