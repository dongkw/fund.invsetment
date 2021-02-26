package fund.investment.app.pledge.repo.server.aggregate.inquiry;

import fund.investment.app.pledge.repo.api.command.inquiry.*;
import fund.investment.app.pledge.repo.api.event.inquiry.*;
import fund.investment.app.pledge.repo.api.valueobject.inquiry.PledgeRepoInquiryAggregateVo;
import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.inquiry.api.enumeration.InquiryAggregateVo;
import fund.investment.basic.inquiry.server.aggregate.InquiryAggregate;
import fund.investment.basic.inquiry.server.aggregate.status.CancellingInquiryState;
import fund.investment.basic.inquiry.server.aggregate.status.CreatedInquiryState;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Slf4j
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Aggregate(snapshotTriggerDefinition = "snapshotTrigger")
public class PledgeRepoInquiryAggregate extends InquiryAggregate {

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

    @CommandHandler
    public PledgeRepoInquiryAggregate(PRCreateInquiryCmd cmd) {

//        initAggregate();
        PRInquiryCreatedEvt evt = new PRInquiryCreatedEvt();
        BeanUtils.copyProperties(cmd, evt);
        AggregateLifecycle.apply(evt);


    }

    @CommandHandler
    public void handler(PRUpdateInquiryCmd cmd) {
        PRInquiryUpdateEvt evt = new PRInquiryUpdateEvt();
        BeanUtils.copyProperties(cmd, evt);
        InquiryAggregateVo inquiryAggregateVo = new InquiryAggregateVo();
        PledgeRepoInquiryAggregateVo pledgeRepoInquiryAggregateVo = new PledgeRepoInquiryAggregateVo();
        BeanUtils.copyProperties(this, pledgeRepoInquiryAggregateVo);
        BeanUtils.copyProperties(this, inquiryAggregateVo);
        evt.setOriginalPledgeRepoInquiryAggregateVo(pledgeRepoInquiryAggregateVo);
        evt.setOriginInquiryAggregateVo(inquiryAggregateVo);
        AggregateLifecycle.apply(evt);
    }

    @CommandHandler
    public void handler(PRCancelInquiryCmd cmd) {
        PRInquiryCancellingEvt istrCancellingEvt = new PRInquiryCancellingEvt();
        istrCancellingEvt.setId(cmd.getId());
        istrCancellingEvt.setUnitId(getUnitId());
        istrCancellingEvt.setSkId(getSkId());
        istrCancellingEvt.setSkInquiry(getSkInquiry());
        istrCancellingEvt.setCancelType(cmd.getCancelType());
        istrCancellingEvt.setCancelMsg(cmd.getCancelMsg());
        istrCancellingEvt.setChLastModifiedId(cmd.getChLastModifiedId());
        istrCancellingEvt.setTsLastModifiedTime(cmd.getTsLastModifiedTime());
        istrCancellingEvt.setRequestId(cmd.getRequestId());
        istrCancellingEvt.setChSourceNo(getChSourceNo());
        istrCancellingEvt.setChMemo(getChMemo());
        istrCancellingEvt.setUserId(cmd.getUserId());
        AggregateLifecycle.apply(istrCancellingEvt);
    }

    @CommandHandler
    public void handler(PRUpdateConfirmInquiryCmd cmd) {
        PRInquiryUpdateConfirmedEvt evt = new PRInquiryUpdateConfirmedEvt();
        BeanUtils.copyPropertiesIgnoreNull(cmd, evt);
        AggregateLifecycle.apply(evt);
    }

    @CommandHandler
    public void handler(PRUpdateFailedInquiryCmd cmd) {
        PRInquiryUpdateFailedEvt evt = new PRInquiryUpdateFailedEvt();
        BeanUtils.copyPropertiesIgnoreNull(cmd, evt);
        AggregateLifecycle.apply(evt);
    }

    @EventSourcingHandler
    public void on(PRInquiryCreatedEvt evt) {
        setInquiryState(new CreatedInquiryState());
        BeanUtils.copyProperties(evt, this);

    }

    @EventSourcingHandler
    public void on(PRInquiryCancellingEvt evt) {

        setInquiryState(new CancellingInquiryState());

    }

    @EventSourcingHandler
    public void on(PRInquiryUpdateEvt evt) {
        setChLastModifiedId(evt.getChLastModifiedId());
        setTsLastModifiedTime(evt.getTsLastModifiedTime());
        BeanUtils.copyPropertiesIgnoreNullAndId(evt, this);


    }

    @EventSourcingHandler
    public void on(PRInquiryUpdateConfirmedEvt evt) {

        setChLastModifiedId(evt.getChLastModifiedId());
        setTsLastModifiedTime(evt.getTsLastModifiedTime());
        BeanUtils.copyPropertiesIgnoreNullAndId(evt, this);

    }

    @EventSourcingHandler
    public void on(PRInquiryUpdateFailedEvt evt) {
        setChLastModifiedId(evt.getChLastModifiedId());
        setTsLastModifiedTime(evt.getTsLastModifiedTime());
        BeanUtils.copyPropertiesIgnoreNullAndId(evt, this);

    }
}
