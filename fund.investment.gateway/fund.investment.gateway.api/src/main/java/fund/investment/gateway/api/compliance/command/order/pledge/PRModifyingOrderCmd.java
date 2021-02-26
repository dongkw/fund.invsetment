package fund.investment.gateway.api.compliance.command.order.pledge;

import fund.investment.basic.common.valueobject.InterbankPledgeBond;
import fund.investment.gateway.api.compliance.command.order.PlacingOrderCmd;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PRModifyingOrderCmd extends PlacingOrderCmd {

    /**
     * 质押债券信息
     */
    private List<InterbankPledgeBond> pledgeBondList;

}
