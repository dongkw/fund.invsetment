package fund.investment.app.pledge.repo.api.event.instruction;

import fund.investment.app.pledge.repo.api.valueobject.instruction.PledgeRepoIstrAggrVO;
import fund.investment.basic.instruction.api.event.IstrUpdatedFailedEvt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PRIstrUpdatedFailedEvt extends IstrUpdatedFailedEvt {

    private PledgeRepoIstrAggrVO originPledgeRepoIstrAggrVO;

}
