package fund.investment.basic.inquiry.api.enumeration;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class InquiryAggregateVo {
    private String id;
    private String unitId;
    private String skId;
    private String skInquiry;
    private String chInquireNo;
    private String chInquiryModify;
    private Date dCurrentDate;
    private String chBusinClass;
    private String skProduct;
    private String skAsset;
    private String skCombi;
    private String skSecurity;
    private String skTradeType;
    private BigDecimal ftMinExpectRate;
    private BigDecimal ftMaxExpectRate;
    private String chLeftTermUnit;
    private Long ftMinDeadline;
    private Long ftMaxDeadline;
    private BigDecimal ftInquireQtty;
    private BigDecimal ftInquireAmt;
    private BigDecimal ftParBalance;
    private String chSettleDays;
    private BigDecimal ftMinInquirePrice;
    private BigDecimal ftMaxInquirePrice;
    private String chOtherCondition;
    private String chInquireStatus;
    private String chTraderUserId;
    private String skInst;
    private String chTraderId;
    private Date dDirectDate;
    private String chDirectTime;
    private Date tsModifyDate;
    private Date dBeginDate;
    private String chBeginTime;
    private Date dInstrEndDate;
    private String chEndTime;
    private String chInvestType;
    private String chStockholderCode;
    private String chReportSeat;
    private String chInstrSource;
    private String chSourceKey;
    private String chSourceNo;
    private String chInstrSourceTwo;
    private String chSourceKeyTwo;
    private String chSourceNoTwo;
    private String skInstr;
    private Date dRealDirectDate;
    private String skMarktExe;
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
    private String chInstrIssuedSource;

}
