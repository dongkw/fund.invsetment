package fund.investment.app.pledge.repo.api.event.trade;

import fund.investment.basic.common.valueobject.InterbankPledgeBond;
import fund.investment.basic.trade.api.event.OrderModifiedEvt;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PROrderModifiedEvt extends OrderModifiedEvt {

    /*质押式回购用参数*/

    private Long ftRepDays;

    /**
     * 首期结算金额
     */
    private BigDecimal ftFirstSettleAmt;

    /**
     * 到期结算金额:用于银行间回购
     */
    private BigDecimal ftSecondSettleAmt;

    /**
     * 首期应计利息:债券业务和买断式回购存债券百元利率，债券借贷业务存利息总额
     */
    private BigDecimal ftFirstInterest;

    /**
     * 到期应计利息:用于银行间买断式回购业务
     */
    private BigDecimal ftSecondInterest;

    private List<InterbankPledgeBond> pledgeBondList;

}
