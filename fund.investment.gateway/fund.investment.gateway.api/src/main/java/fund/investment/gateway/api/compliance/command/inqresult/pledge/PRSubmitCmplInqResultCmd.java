package fund.investment.gateway.api.compliance.command.inqresult.pledge;

import fund.investment.basic.common.valueobject.InterbankPledgeBond;
import fund.investment.gateway.api.compliance.command.inqresult.SubmitCmplInqResultCmd;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class PRSubmitCmplInqResultCmd extends SubmitCmplInqResultCmd {

    /**
     * 询价价格:存交易价格或者利率
     */
    private BigDecimal ftInquirePrice;

    /**
     * 回购天数/存款期限:用于回购和存款业务，其他业务填空或0
     */
    private Long ftInquireDays;
    /**
     * 实际占款天数:用于银行间回购和债券借贷业务
     */
    private Long ftUseDays;

    /**
     * 首期结算日期
     */
    private Date dtFirstSettleDate;

    /**
     * 到期结算日期
     */
    private Date dtSecondSettleDate;
    /**
     * 首期结算金额
     */
    private BigDecimal ftFirstSettleAmt;
    /**
     * 到期结算金额:用于银行间回购
     */
    private BigDecimal ftSecondSettleAmt;
    /**
     * 质押债券信息
     */
    private List<InterbankPledgeBond> pledgeBondList;
}
