package fund.investment.app.pledge.repo.api.event.trade;

import fund.investment.basic.common.valueobject.InterbankPledgeBond;
import fund.investment.basic.trade.api.event.OrderCreatedEvt;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PROrderCreatedEvt extends OrderCreatedEvt {

    /**
     * 原委托id（修改用）
     */
    private String skOriginId;

    /*质押式回购用参数*/
    private List<InterbankPledgeBond> pledgeBondList;

}
