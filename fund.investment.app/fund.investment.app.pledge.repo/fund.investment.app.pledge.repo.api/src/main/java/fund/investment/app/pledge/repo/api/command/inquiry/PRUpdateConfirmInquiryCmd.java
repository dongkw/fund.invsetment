package fund.investment.app.pledge.repo.api.command.inquiry;

import fund.investment.basic.inquiry.api.command.UpdateConfirmInquiryCmd;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;


@Getter
@Setter
public class PRUpdateConfirmInquiryCmd extends UpdateConfirmInquiryCmd {


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
