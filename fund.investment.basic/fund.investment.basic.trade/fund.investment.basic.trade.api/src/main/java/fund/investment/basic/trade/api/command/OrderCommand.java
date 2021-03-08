package fund.investment.basic.trade.api.command;

import fund.investment.basic.common.DomainCommand;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderCommand extends DomainCommand {

    private Long instructionId;

    private String tradeType;

}
