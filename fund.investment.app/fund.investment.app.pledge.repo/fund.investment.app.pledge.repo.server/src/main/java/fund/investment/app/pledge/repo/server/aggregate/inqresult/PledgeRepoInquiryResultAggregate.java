package fund.investment.app.pledge.repo.server.aggregate.inqresult;

import fund.investment.app.pledge.repo.api.command.inqresult.*;
import fund.investment.app.pledge.repo.api.event.inqresult.*;
import fund.investment.app.pledge.repo.server.aggregate.inqresult.status.ConfirmedPledgeRepoInqResultState;
import fund.investment.app.pledge.repo.server.aggregate.inqresult.status.CreatedPledgeRepoInqResultState;
import fund.investment.app.pledge.repo.server.aggregate.inqresult.status.RejectPledgeRepoInqResultState;
import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.common.valueobject.InterbankPledgeBond;
import fund.investment.basic.inqresult.api.command.BackInquiryResultCmd;
import fund.investment.basic.inqresult.api.command.FinishInquiryResultCmd;
import fund.investment.basic.inqresult.api.command.RejectInquiryResultCmd;
import fund.investment.basic.inqresult.api.command.UpdateFailInquiryResultCmd;
import fund.investment.basic.inqresult.api.event.InquiryResultBackEvt;
import fund.investment.basic.inqresult.api.event.InquiryResultConfirmedEvt;
import fund.investment.basic.inqresult.api.event.InquiryResultFinishEvt;
import fund.investment.basic.inqresult.api.event.InquiryResultRejectEvt;
import fund.investment.basic.inqresult.server.aggregate.InquiryResultAggregate;
import fund.investment.basic.inqresult.server.aggregate.status.CancellingInquiryResultState;
import fund.investment.basic.inqresult.server.aggregate.status.CommitInquiryResultState;
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
import java.util.List;

@Getter
@Setter
@Slf4j
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Aggregate(snapshotTriggerDefinition = "snapshotTrigger")
public class PledgeRepoInquiryResultAggregate extends InquiryResultAggregate {

    /**
     * 询价价格:存交易价格或者利率
     */
    private BigDecimal ftInquirePrice;

    /**
     * 回购天数/存款期限:用于回购和存款业务，其他业务填空或0
     */
    private Long ftInquireDays;
    /**
     * 实际占款天数:用于银行间回购和债券借贷业务
     */
    private Long ftUseDays;

    /**
     * 首期结算日期
     */
    private Date dtFirstSettleDate;

    /**
     * 到期结算日期
     */
    private Date dtSecondSettleDate;
    /**
     * 首期结算金额
     */
    private BigDecimal ftFirstSettleAmt;
    /**
     * 到期结算金额:用于银行间回购
     */
    private BigDecimal ftSecondSettleAmt;
    /**
     * 质押债券信息
     */
    private List<InterbankPledgeBond> pledgeBondList;

    @CommandHandler
    public PledgeRepoInquiryResultAggregate(PRCreateInquiryResultCmd cmd) {

//        initAggregate();
        //TODO 修改字段映射
        PRInquiryResultCreatedEvt evt = new PRInquiryResultCreatedEvt();
        BeanUtils.copyProperties(cmd, evt);
        AggregateLifecycle.apply(evt);

    }


    @CommandHandler
    public void on(BackInquiryResultCmd cmd) {
        InquiryResultBackEvt evt = new InquiryResultBackEvt();
        BeanUtils.copyPropertiesIgnoreNull(cmd, evt);
        AggregateLifecycle.apply(evt);
    }

    @CommandHandler
    public void on(RejectInquiryResultCmd cmd) {
        InquiryResultRejectEvt evt = new InquiryResultRejectEvt();
        BeanUtils.copyPropertiesIgnoreNull(cmd, evt);
        AggregateLifecycle.apply(evt);
    }

    @CommandHandler
    public void on(UpdateFailInquiryResultCmd cmd) {
        InquiryResultRejectEvt evt = new InquiryResultRejectEvt();
        BeanUtils.copyPropertiesIgnoreNull(cmd, evt);
        AggregateLifecycle.apply(evt);
    }

    @CommandHandler
    public void handler(PRUpdateInquiryResultCmd cmd) {
        getInquiryResultState().update(this, cmd);
    }

    @CommandHandler
    public void handler(PRCancelInquiryResultCmd cmd) {
        PRInquiryResultCancellingEvt istrCancellingEvt = new PRInquiryResultCancellingEvt();
        istrCancellingEvt.setRequestId(cmd.getRequestId());
        istrCancellingEvt.setId(cmd.getId());
        istrCancellingEvt.setUnitId(getSkCombi());
        istrCancellingEvt.setSkId(getSkId());
        istrCancellingEvt.setSkInquiry(getSkInquiry());
        istrCancellingEvt.setCancelType(cmd.getCancelType());
        istrCancellingEvt.setCancelMsg(cmd.getCancelMsg());
        istrCancellingEvt.setChLastModifiedId(cmd.getChLastModifiedId());
        istrCancellingEvt.setTsLastModifiedTime(cmd.getTsLastModifiedTime());
//        istrCancellingEvt.setChSourceKey(getChSourceKey());
        istrCancellingEvt.setChMemo(getChMemo());
        istrCancellingEvt.setUserId(cmd.getUserId());
        AggregateLifecycle.apply(istrCancellingEvt);
    }

    @CommandHandler
    public void handler(PRUpdateConfirmInquiryResultCmd cmd) {
        getInquiryResultState().updateConfirm(this, cmd);

    }

    @CommandHandler
    public void handler(PRUpdateFailedInquiryResultCmd cmd) {
        getInquiryResultState().updateFail(this, cmd);

    }

    @CommandHandler
    public void handle(PRCommitInquiryResultCmd cmd) {
        getInquiryResultState().commit(this, cmd);
    }


    @CommandHandler
    public void handle(BackInquiryResultCmd cmd) {
        getInquiryResultState().back(this, cmd);
    }

    @CommandHandler
    public void handle(RejectInquiryResultCmd cmd) {
        getInquiryResultState().reject(this, cmd);
    }

    @CommandHandler
    public void handle(FinishInquiryResultCmd cmd) {
        getInquiryResultState().finish(this, cmd);
    }

    @CommandHandler
    public void handle(PRUpdateInquiryResultDraCmd cmd) {
        PRInquiryResultUpdateSuccEvt evt = new PRInquiryResultUpdateSuccEvt();
        BeanUtils.copyProperties(cmd, evt);
        AggregateLifecycle.apply(evt);

    }

    @EventSourcingHandler
    public void handle(PRInquiryResultCommitEvt evt) {

        setInquiryResultState(new CommitInquiryResultState());
    }

    @EventSourcingHandler
    public void on(PRInquiryResultCreatedEvt evt) {
        setInquiryResultState(new CreatedPledgeRepoInqResultState());
        BeanUtils.copyProperties(evt, this);

    }

    @EventSourcingHandler
    public void handle(InquiryResultConfirmedEvt evt) {
        setChLastModifiedId(evt.getChLastModifiedId());
        setTsLastModifiedTime(evt.getTsLastModifiedTime());
        setInquiryResultState(new ConfirmedPledgeRepoInqResultState());
    }


    @EventSourcingHandler
    public void on(PRInquiryResultCancellingEvt evt) {

        setInquiryResultState(new CancellingInquiryResultState());

    }

    @EventSourcingHandler
    public void on(PRInquiryResultUpdateEvt evt) {
//        setChLastModifiedId(evt.getChLastModifiedId());
//        setTsLastModifiedTime(evt.getTsLastModifiedTime());
//        BeanUtils.copyPropertiesIgnoreNullAndId(evt, this);
//

    }

    @EventSourcingHandler
    public void on(PRInquiryResultUpdateConfirmedEvt evt) {

        setChLastModifiedId(evt.getChLastModifiedId());
        setTsLastModifiedTime(evt.getTsLastModifiedTime());
        BeanUtils.copyPropertiesIgnoreNullAndId(evt, this);

    }

    @EventSourcingHandler
    public void handler(PRInquiryResultUpdateSuccEvt evt) {

        setChLastModifiedId(evt.getChLastModifiedId());
        setTsLastModifiedTime(evt.getTsLastModifiedTime());
        BeanUtils.copyPropertiesIgnoreNullAndId(evt, this);

    }

    @EventSourcingHandler
    public void handler(PRInquiryResultUpdateFailedEvt evt) {
        setChLastModifiedId(evt.getChLastModifiedId());
        setTsLastModifiedTime(evt.getTsLastModifiedTime());
        BeanUtils.copyPropertiesIgnoreNullAndId(evt, this);

    }

    @EventSourcingHandler
    public void on(InquiryResultBackEvt evt) {
        setChLastModifiedId(evt.getChLastModifiedId());
        setTsLastModifiedTime(evt.getTsLastModifiedTime());
        BeanUtils.copyPropertiesIgnoreNullAndId(evt, this);
    }

    @EventSourcingHandler
    public void on(InquiryResultRejectEvt evt) {
        setInquiryResultState(new RejectPledgeRepoInqResultState());

    }

    @EventSourcingHandler
    public void on(InquiryResultFinishEvt evt) {
        setChLastModifiedId(evt.getChLastModifiedId());
        setTsLastModifiedTime(evt.getTsLastModifiedTime());
        BeanUtils.copyPropertiesIgnoreNullAndId(evt, this);
    }


}
