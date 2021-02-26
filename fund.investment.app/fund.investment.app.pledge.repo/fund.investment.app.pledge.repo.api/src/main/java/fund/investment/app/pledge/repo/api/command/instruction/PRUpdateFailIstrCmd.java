package fund.investment.app.pledge.repo.api.command.instruction;

import fund.investment.app.pledge.repo.api.valueobject.instruction.PledgeRepoIstrAggrVO;
import fund.investment.basic.instruction.api.command.UpdateFailIstrCmd;
import fund.investment.basic.instruction.api.enumeration.TradeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PRUpdateFailIstrCmd extends UpdateFailIstrCmd {

    private PledgeRepoIstrAggrVO originPledgeRepoIstrAggrVO;

    public PRUpdateFailIstrCmd(TradeType tradeType, String id, String skId, String skInstr) {
        super(tradeType, id, skId, skInstr);
    }
}
