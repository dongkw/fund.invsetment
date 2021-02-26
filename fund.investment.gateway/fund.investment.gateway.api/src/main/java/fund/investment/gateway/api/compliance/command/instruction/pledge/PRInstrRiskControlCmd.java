package fund.investment.gateway.api.compliance.command.instruction.pledge;

import fund.investment.basic.common.valueobject.InterbankPledgeBond;
import fund.investment.gateway.api.compliance.command.instruction.InstrRiskControlCmd;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class PRInstrRiskControlCmd extends InstrRiskControlCmd {

    /**
     * 回购指令表代理键
     */
    private String skHgInstr;

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
