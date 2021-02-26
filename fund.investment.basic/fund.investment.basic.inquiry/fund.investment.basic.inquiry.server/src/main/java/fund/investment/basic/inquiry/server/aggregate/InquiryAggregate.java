package fund.investment.basic.inquiry.server.aggregate;

import fund.investment.basic.common.DomainAggregate;
import fund.investment.basic.inquiry.api.command.CancelConfInquiryCmd;
import fund.investment.basic.inquiry.api.command.CreateConfirmInquiryCmd;
import fund.investment.basic.inquiry.api.command.CreateFailInquiryCmd;
import fund.investment.basic.inquiry.api.event.InquiryCancelledEvt;
import fund.investment.basic.inquiry.api.event.InquiryCompletedEvt;
import fund.investment.basic.inquiry.api.event.InquiryConfirmedEvt;
import fund.investment.basic.inquiry.api.event.InquiryFailedEvt;
import fund.investment.basic.inquiry.server.aggregate.status.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class InquiryAggregate extends DomainAggregate {

    private InquiryState inquiryState;
    private String unitId;

    private String skId;
    private String skInquiry;
    private String chInquireNo;
    private String chInquiryModify;
    private Date dtCurrentDate;
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
    private String chLastModifiedId;
    private Date tsLastModifiedTime;
    private String chDataSource;
    private String chAuditorId;
    private String chAuditStatus;
    private Date tsAuditTime;
    private String chInstrIssuedSource;

    @CommandHandler
    public void handle(CreateConfirmInquiryCmd cmd) {
        getInquiryState().createConfirm(this, cmd);
    }

    @CommandHandler
    public void handle(CreateFailInquiryCmd cmd) {
        getInquiryState().createFail(this, cmd);
    }


    @CommandHandler
    public void handle(CancelConfInquiryCmd cmd) {
        getInquiryState().cancelConfirm(this, cmd);
    }

    @EventSourcingHandler
    public void handle(InquiryConfirmedEvt evt) {
        this.chLastModifiedId = evt.getChLastModifiedId();
        this.tsLastModifiedTime = evt.getTsLastModifiedTime();
        this.chSourceKey = evt.getChSourceKey();
        this.chSourceNo = evt.getChSourceNo();
        this.chInstrSource = evt.getChInstrSource();
        this.inquiryState = new ConfirmedInquiryState();
    }

    @EventSourcingHandler
    public void handle(InquiryFailedEvt evt) {
        this.chLastModifiedId = evt.getChLastModifiedId();
        this.tsLastModifiedTime = evt.getTsLastModifiedTime();
        this.inquiryState = new FailedInquiryState();
    }

    @EventSourcingHandler
    public void handle(InquiryCompletedEvt evt) {
        this.chLastModifiedId = evt.getChLastModifiedId();
        this.tsLastModifiedTime = evt.getTsLastModifiedTime();
        this.inquiryState = new CompletedInquiryState();
    }

    @EventSourcingHandler
    public void handle(InquiryCancelledEvt evt) {
        this.chLastModifiedId = evt.getChLastModifiedId();
        this.tsLastModifiedTime = evt.getTsLastModifiedTime();
        this.inquiryState = new CancelledInquiryState();
    }
}
