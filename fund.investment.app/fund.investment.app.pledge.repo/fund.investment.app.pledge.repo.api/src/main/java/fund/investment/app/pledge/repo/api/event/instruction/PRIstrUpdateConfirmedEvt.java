package fund.investment.app.pledge.repo.api.event.instruction;

import fund.investment.basic.common.valueobject.InterbankPledgeBond;
import fund.investment.basic.instruction.api.event.IstrUpdateConfirmedEvt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PRIstrUpdateConfirmedEvt extends IstrUpdateConfirmedEvt {

    /**
     * 回购指令表代理键
     */
    private String skHgInstr;

    /**
     * 交易日期
     */
    private Date dtTradeDate;

    /**
     * 回购天数
     */
    private Long ftRepDays;

    /**
     * 首期结算日期
     */
    private Date dtFirstSettleDate;

    /**
     * 到期结算日期
     */
    private Date dtSecondSettleDate;

    /**
     * 首期结算方式
     */
    private String chFirstSettleType;

    /**
     * 到期结算方式
     */
    private String chSecondSettleType;

    /**
     * 回购需求方
     */
    private String chRequireOrigin;

    /**
     * 回购用途类别
     */
    private String chPurposeType;

    /**
     * 报价发起方
     */
    private String chQuoteSide;

    /**
     * 实际占款天数
     */
    private Long ftUseDays;

    /**
     * 到期结算金额
     */
    private BigDecimal ftSecondSettleAmt;

    /**
     * 质押债券信息
     */
    private List<InterbankPledgeBond> pledgeBondList;

}