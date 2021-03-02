package fund.investment.app.pledge.repo.api.command.instruction;

import fund.investment.app.pledge.repo.api.valueobject.instruction.PledgeTradeElement;
import fund.investment.basic.instruction.api.command.CreateIstrCmd;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PRCreateIstrCmd extends CreateIstrCmd<PledgeTradeElement> {

}
