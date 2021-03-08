package fund.investment.app.pledge.repo.api.valueobject.trade;

import fund.investment.basic.common.valueobject.InterbankPledgeBond;
import fund.investment.basic.trade.api.valueobject.TradeElement;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author dongkw
 * @Date 2021/3/3、9:41 上午
 **/
@Data
public class PledgeTradeElement extends TradeElement {
    /**
     * 回购天数
     */
    @ApiModelProperty(value = "回购天数", example = "", notes = "回购天数", required = true)
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

    /**
     * 质押券列表
     */
    @ApiModelProperty(value = "质押券列表", example = "", notes = "新增草稿委托时需要")
    private List<InterbankPledgeBond> pledgeBondList;


}
