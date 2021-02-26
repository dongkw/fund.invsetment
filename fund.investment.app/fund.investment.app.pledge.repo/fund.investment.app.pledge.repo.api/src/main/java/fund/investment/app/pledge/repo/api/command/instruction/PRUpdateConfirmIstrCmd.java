package fund.investment.app.pledge.repo.api.command.instruction;

import fund.investment.app.pledge.repo.api.valueobject.instruction.PledgeRepoIstrAggrVO;
import fund.investment.basic.common.valueobject.InterbankPledgeBond;
import fund.investment.basic.instruction.api.command.UpdateConfirmIstrCmd;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PRUpdateConfirmIstrCmd extends UpdateConfirmIstrCmd {

    private PledgeRepoIstrAggrVO originPledgeRepoIstrAggrVO;

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
