package fund.investment.basic.inqresult.server.aggregate;


import fund.investment.basic.common.DomainAggregate;
import fund.investment.basic.inqresult.api.command.CancelConfInquiryResultCmd;
import fund.investment.basic.inqresult.api.command.CreateConfirmInquiryResultCmd;
import fund.investment.basic.inqresult.api.command.CreateFailInquiryResultCmd;
import fund.investment.basic.inqresult.api.event.InquiryResultCancelledEvt;
import fund.investment.basic.inqresult.api.event.InquiryResultCompletedEvt;
import fund.investment.basic.inqresult.api.event.InquiryResultFailedEvt;
import fund.investment.basic.inqresult.server.aggregate.status.CancelledInquiryResultState;
import fund.investment.basic.inqresult.server.aggregate.status.CompletedInquiryResultState;
import fund.investment.basic.inqresult.server.aggregate.status.FailedInquiryResultState;
import fund.investment.basic.inqresult.server.aggregate.status.InquiryResultState;
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
public class InquiryResultAggregate extends DomainAggregate {

    private InquiryResultState inquiryResultState;

    private String skId;
    private Date dTradeDate;
    private String skInquiry;
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
    private Date dValueDate;
    private Date dMatureDate;
    private Date dDrawDate;
    private Date dPaymentDate;
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
    private Date dDirectDate;
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
    private Date dIssuendDate;
    private String chSecuCode;
    private String chSecuName;
    private String skIssuer;
    private String chTradeTime;
    private Date chAdvanceLimitDate;
    private String chInstrIssuedSource;

    @CommandHandler
    public void handle(CreateConfirmInquiryResultCmd cmd) {
        getInquiryResultState().createConfirm(this, cmd);
    }

    @CommandHandler
    public void handle(CreateFailInquiryResultCmd cmd) {
        getInquiryResultState().createFail(this, cmd);
    }


    @CommandHandler
    public void handle(CancelConfInquiryResultCmd cmd) {
        getInquiryResultState().cancelConfirm(this, cmd);
    }



    @EventSourcingHandler
    public void handle(InquiryResultFailedEvt evt) {
        this.chLastModifiedId = evt.getChLastModifiedId();
        this.tsLastModifiedTime = evt.getTsLastModifiedTime();
        this.inquiryResultState = new FailedInquiryResultState();
    }

    @EventSourcingHandler
    public void handle(InquiryResultCompletedEvt evt) {
        this.chLastModifiedId = evt.getChLastModifiedId();
        this.tsLastModifiedTime = evt.getTsLastModifiedTime();
        this.inquiryResultState = new CompletedInquiryResultState();
    }

    @EventSourcingHandler
    public void handle(InquiryResultCancelledEvt evt) {
        this.chLastModifiedId = evt.getChLastModifiedId();
        this.tsLastModifiedTime = evt.getTsLastModifiedTime();
        this.inquiryResultState = new CancelledInquiryResultState();
    }

}
