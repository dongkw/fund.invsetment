package fund.investment.basic.inquiry.api.event;

import fund.investment.basic.inquiry.api.enumeration.InquiryAggregateVo;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class InquiryUpdatedEvt extends InquiryEvent {
    private InquiryAggregateVo originInquiryAggregateVo;

    private String originSkId;

    private String unitId;

    private String chInquireNo;

    private String chInquiryModify;

    private String chBusinClass;

    private String skProduct;

    private String skAsset;

    private String skCombi;

    private String skSecurity;

    private String skTradeType;

    private String chLeftTermUnit;

    private Long ftMinDeadline;

    private Long ftMaxDeadline;

    private BigDecimal ftParBalance;

    private String chSettleDays;

    private BigDecimal ftMinInquirePrice;

    private BigDecimal ftMaxInquirePrice;

    private String chOtherCondition;

    private String chInquireStatus;

    private String chTraderUserId;

    private String skInst;

    private String chTraderId;

    private Date dtDirectDate;

    private String chDirectTime;

    private Date tsModifyDate;

    private Date dtBeginDate;

    private String chBeginTime;

    private Date dtInstrEndDate;

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

    private Date dtRealDirectDate;

    private String skMarktExe;

    private String chMemo;

    private String chDelete;

    private String chCreateId;

    private Date tsCreateTime;

    private String chDataSource;

    private String chAuditorId;

    private String chAuditStatus;

    private Date tsAuditTime;

    private String chInstrIssuedSource;


}
