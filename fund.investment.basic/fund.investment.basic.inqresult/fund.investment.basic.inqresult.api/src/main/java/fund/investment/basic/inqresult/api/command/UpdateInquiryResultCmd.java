package fund.investment.basic.inqresult.api.command;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class UpdateInquiryResultCmd extends InquiryResultCommand {


    private Date dtTradeDate;
    private String skProduct;
    private String skAsset;
    private String skCombi;
    private String chInvestType;
    private String chIsPreLimit;
    private String skSecurity;
    private String skTradeType;
    private BigDecimal ftInquireQtty;
    private BigDecimal ftInquireAmt;
    private String chSettleDays;
    private Date dtValueDate;
    private Date dtMatureDate;
    private Date dtDrawDate;
    private Date dtPaymentDate;
    private String chEndType;
    private String chIntModeCode;
    private String chAccrualFreq;
    private String chAdvanceLimitFlag;
    private String chFirstSettleType;
    private String chSecondSettleType;
    private String chInquireStatus;
    private String skInstr;
    private String chInstrSource;
    private String chSourceKey;
    private String chSourceNo;
    private String chInstrSourceTwo;
    private String chSourceKeyTwo;
    private String chSourceNoTwo;
    private Date dtDirectDate;
    private String chReportSeat;
    private String chTraderId;
    private String skInst;
    private String skInstBranch;
    private BigDecimal ftFirstNetPrice;
    private BigDecimal ftFirstFullPrice;
    private BigDecimal ftFirstProfitRatio;
    private BigDecimal ftFirstInterest;
    private BigDecimal ftSecondNetPrice;
    private BigDecimal ftSecondFullPrice;
    private BigDecimal ftSecondProfitRatio;
    private BigDecimal ftSecondInterest;
    private String chPriceControlType;
    private String chMemo;
    private String chDelete;
    private String chCreateId;
    private Date tsCreateTime;
    private String chLastModifiedId;
    private Date tsLastModifiedTime;
    private String chDataSource;
    private String chAuditorId;
    private String chAuditStatus;
    private Date tsAuditTime;
    private String chTermUnit;
    private Date dtIssuendDate;
    private String chSecuCode;
    private String chSecuName;
    private String skIssuer;
    private String chTradeTime;
    private Date chAdvanceLimitDate;
    private String chInstrIssuedSource;
}
