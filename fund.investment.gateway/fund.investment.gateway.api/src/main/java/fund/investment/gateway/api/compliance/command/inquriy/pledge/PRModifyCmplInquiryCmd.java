package fund.investment.gateway.api.compliance.command.inquriy.pledge;

import fund.investment.gateway.api.compliance.command.inquriy.ModifyCmplInquiryCmd;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class PRModifyCmplInquiryCmd extends ModifyCmplInquiryCmd {

    /**
     * 当前交易时间（yyyyMMdd->date）
     */
    private Date dtCurrentDate;
    /**
     * 指令金额（万元->元）
     */
    private BigDecimal ftInquireAmt;
    /**
     * 指令数量（指令金额/100）
     */
    private BigDecimal ftInquireQtty;
    /**
     * 最低利率（百分百->小数）
     */
    private BigDecimal ftMinExpectRate;
    /**
     * 最高利率（百分百->小数）
     */
    private BigDecimal ftMaxExpectRate;
}
