package fund.investment.app.pledge.repo.server.aggregate.instruction;

import fund.investment.app.pledge.repo.api.command.instruction.*;
import fund.investment.app.pledge.repo.api.event.instruction.*;
import fund.investment.app.pledge.repo.api.valueobject.instruction.PledgeRepoIstrAggrVO;
//import fund.investment.app.pledge.repo.server.utils.datarec.PledgeInvestDataRecAggrDto;
import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.common.valueobject.InterbankPledgeBond;
import fund.investment.basic.instruction.api.entity.IstrTradeElement;
import fund.investment.basic.instruction.api.event.IstrCancellingEvt;
import fund.investment.basic.instruction.server.aggregate.InstructionAggregate;
import fund.investment.basic.instruction.server.aggregate.status.CancellingInstructionState;
import fund.investment.basic.instruction.server.aggregate.status.CreatedInstructionState;
import fund.investment.gateway.api.compliance.command.instruction.pledge.PRInstrDataRecCmd;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Slf4j
@Aggregate(snapshotTriggerDefinition = "snapshotTrigger")
@NoArgsConstructor
public class PledgeRepoIstrAggr extends InstructionAggregate {

    /**
     * 回购指令表代理键
     */
    private String skHgInstr;

    /**
     * 回购天数
     */
    private Long ftRepDays;

    /**
     * 首期结算日期
     */
    private Date dtFirstSettleDate;

    /**
     * 到期结算日期
     */
    private Date dtSecondSettleDate;

    /**
     * 首期结算方式
     */
    private String chFirstSettleType;

    /**
     * 到期结算方式
     */
    private String chSecondSettleType;

    /**
     * 回购需求方
     */
    private String chRequireOrigin;

    /**
     * 回购用途类别
     */
    private String chPurposeType;

    /**
     * 报价发起方
     */
    private String chQuoteSide;

    /**
     * 实际占款天数
     */
    private Long ftUseDays;

    /**
     * 到期结算金额
     */
    private BigDecimal ftSecondSettleAmt;

    /**
     * 质押债券信息
     */
    private List<InterbankPledgeBond> pledgeBondList;


    @CommandHandler
    public PledgeRepoIstrAggr(PRCreateIstrCmd cmd) {
        PRIstrCreatedEvt prIstrCreatedEvt = new PRIstrCreatedEvt();
        BeanUtils.copyProperties(cmd, prIstrCreatedEvt);
        AggregateLifecycle.apply(prIstrCreatedEvt);
    }

    @CommandHandler
    public void handle(PRCancelIstrCmd cmd) {
        getInstructionState().cancel(this, cmd);
    }

    @CommandHandler
    public void handle(PRUpdateIstrCmd cmd) {
        PRIstrUpdatedEvt prIstrUpdatedEvt = new PRIstrUpdatedEvt();
        BeanUtils.copyProperties(cmd, prIstrUpdatedEvt);

        //修改前指令信息
        PledgeRepoIstrAggrVO vo = new PledgeRepoIstrAggrVO();
        BeanUtils.copyProperties(this, vo);
        prIstrUpdatedEvt.setOriginPledgeRepoIstrAggrVO(vo);
//        prIstrUpdatedEvt.setSkOriginId(this.getSkId());
        AggregateLifecycle.apply(prIstrUpdatedEvt);
    }

    @CommandHandler
    public void handle(PRUpdateFailIstrCmd cmd) {
        PRIstrUpdatedFailedEvt evt = new PRIstrUpdatedFailedEvt();
        BeanUtils.copyProperties(cmd, evt);
        evt.setOriginPledgeRepoIstrAggrVO(cmd.getOriginPledgeRepoIstrAggrVO());
        evt.setInstructionAggregateVO(cmd.getOriginPledgeRepoIstrAggrVO());
        AggregateLifecycle.apply(evt);
    }

    @CommandHandler
    public void handle(PRUpdateConfirmIstrCmd cmd) {
        PRIstrUpdateConfirmedEvt evt = new PRIstrUpdateConfirmedEvt();
        BeanUtils.copyProperties(cmd, evt);
        evt.setSkOriginId(cmd.getOriginPledgeRepoIstrAggrVO().getSkId());
        AggregateLifecycle.apply(evt);
    }

    @EventSourcingHandler
    public void on(PRIstrUpdatedFailedEvt evt) {
        BeanUtils.copyProperties(evt.getOriginPledgeRepoIstrAggrVO(), this);
        setTsLastModifiedTime(new Date());
    }

    @EventSourcingHandler
    public void on(PRIstrCreatedEvt evt) {
        setInstructionState(new CreatedInstructionState());
        assignAggrValue(evt);
    }

    @EventSourcingHandler
    public void on(PRIstrUpdatedEvt evt) {
        PRIstrCreatedEvt prIstrCreatedEvt = new PRIstrCreatedEvt();
        BeanUtils.copyPropertiesIgnoreNull(evt, prIstrCreatedEvt);
        assignAggrValue(prIstrCreatedEvt);
    }

    /**
     * 给聚合根赋值
     *
     * @param evt
     */
    private void assignAggrValue(PRIstrCreatedEvt evt) {
        setId(evt.getId());
        setUnitId(evt.getUnitId());
        setInquiryResultId(evt.getInquiryResultId());

        IstrTradeElement istrTradeElement = new IstrTradeElement();
        istrTradeElement.setTradeType(evt.getTradeType());
        setIstrTradeElement(istrTradeElement);
        //指令公共属性
        setSkId(evt.getSkId());
        setSkInstr(evt.getSkInstr());
        setChInstrNo(evt.getChInstrNo());
        setChInstrModify(evt.getChInstrModify());
        setChStockNo(evt.getChStockNo());
        setDtCurrentDate(evt.getDtCurrentDate());
        setDtTradeDate(evt.getDtTradeDate());
        setSkProduct(evt.getSkProduct());
        setSkAsset(evt.getSkAsset());
        setSkCombi(evt.getSkCombi());
        setChInstrStatus(evt.getChInstrStatus());
        setDtDirectDate(evt.getDtDirectDate());
        setChDirectTime(evt.getChDirectTime());
        setTsModifyDate(evt.getTsModifyDate());
        setDtBeginDate(evt.getDtBeginDate());
        setChBeginTime(evt.getChBeginTime());
        setDtInstrEndDate(evt.getDtInstrEndDate());
        setChEndTime(evt.getChEndTime());
        setChPreInstrDesc(evt.getChPreInstrDesc());
        setChPreIndexModify(evt.getChPreIndexModify());
        setSkMarktExe(evt.getSkMarktExe());
        setSkTradeType(evt.getSkTradeType());
        setSkSecurity(evt.getSkSecurity());
        setChInvestType(evt.getChInvestType());
        setFtInstrQtty(evt.getFtInstrQtty());
        setFtInstrAmt(evt.getFtInstrAmt());
        setFtInstrPrice(evt.getFtInstrPrice());
        setChSettleDays(evt.getChSettleDays());
        setChTraderUserId(evt.getChTraderUserId());
        setSkInst(evt.getSkInst());
        setChTraderId(evt.getChTraderId());
        setChInstrSource(evt.getChInstrSource());
        setChSourceKey(evt.getChSourceKey());
        setChSourceNo(evt.getChSourceNo());
        setChInstrSourceTwo(evt.getChInstrSourceTwo());
        setChSourceKeyTwo(evt.getChSourceKeyTwo());
        setChSourceNoTwo(evt.getChSourceNoTwo());
        setTsTimestamp(evt.getTsTimestamp());
        setChMemo(evt.getChMemo());
        setChDelete(evt.getChDelete());
        setChCreateId(evt.getChCreateId());
        setTsCreateTime(evt.getTsCreateTime());
        setChLastModifiedId(evt.getChLastModifiedId());
        setTsLastModifiedTime(evt.getTsLastModifiedTime());
        setChDataSource(evt.getChDataSource());
        setChAuditorId(evt.getChAuditorId());
        setChAuditStatus(evt.getChAuditStatus());
        setTsAuditTime(evt.getTsAuditTime());
        setChIsLock(evt.getChIsLock());
        setChInstrIssuedSource(evt.getChInstrIssuedSource());
        //质押式回购属性
        this.skHgInstr = evt.getSkInstr();
//        this.dtTradeDate = evt.getdTradeDate();
        this.ftRepDays = evt.getFtRepDays();
        this.dtFirstSettleDate = evt.getDtFirstSettleDate();
        this.dtSecondSettleDate = evt.getDtSecondSettleDate();
        this.chFirstSettleType = evt.getChFirstSettleType();
        this.chSecondSettleType = evt.getChSecondSettleType();
        this.chRequireOrigin = evt.getChRequireOrigin();
        this.chPurposeType = evt.getChPurposeType();
        this.chQuoteSide = evt.getChQuoteSide();
        this.ftUseDays = evt.getFtUseDays();
        this.ftSecondSettleAmt = evt.getFtSecondSettleAmt();
        this.pledgeBondList = evt.getPledgeBondList();
    }

    @EventSourcingHandler
    public void on(IstrCancellingEvt evt) {
        PRIstrCancellingEvt prIstrCancellingEvt = new PRIstrCancellingEvt();
        BeanUtils.copyProperties(evt, prIstrCancellingEvt);
        setInstructionState(new CancellingInstructionState());
        AggregateLifecycle.apply(prIstrCancellingEvt);
    }

    @EventSourcingHandler
    public void on(PRIstrCancellingEvt evt) {
        log.info("Receive event: {}", evt);
    }

//    @CommandHandler
//    public void handle(PRInstrDataRecCmd cmd) {
//        log.debug("接收到指令数据同步命令:{}", cmd);
//
//        if (this.getTsAuditTime() != null) {
//            //判断修改时间是否大于上次修改时间
//            if (!(cmd.getTsAuditTime().getTime() - this.getTsAuditTime().getTime() > 0)) {
//                //修改时间判定不通过则不修改数据
//                return;
//            }
//        }
//        this.setTsAuditTime(cmd.getTsAuditTime());
//
//        InvestDataHolder<PRDataRecIstrEvt> investDataHolder = new InvestDataHolder<>(PRDataRecIstrEvt::new);
//        PledgeInvestDataRecAggrDto.compareInvestDataAndAggregate(cmd, this, investDataHolder);
//
//        //全部数据同步功能
//        if (investDataHolder.getInvestEvt() != null) {
//            //有数据需要修改，发送数据同步evt
//            PRDataRecIstrEvt dataRecIstrEvt = investDataHolder.getInvestEvt();
//
//            dataRecIstrEvt.setId(this.getId());
//            dataRecIstrEvt.setSkId(this.getSkId());
//            dataRecIstrEvt.setChLastModifiedId(this.getChLastModifiedId());
//            dataRecIstrEvt.setTsLastModifiedTime(this.getTsLastModifiedTime());
//            AggregateLifecycle.apply(dataRecIstrEvt);
//            log.debug("发送数据同步事件:{}", dataRecIstrEvt);
//        } else {
//            log.debug("数据没有变动：{}", cmd);
//        }
//    }
//
//    @EventSourcingHandler
//    public void on(PRDataRecIstrEvt evt) {
//        log.debug("开始处理数据同步事件: {}", evt);
//
//        PledgeInvestDataRecAggrDto pledgeInvestDataRecAggrDto = new PledgeInvestDataRecAggrDto(this);
//        InvestDataHolder<PledgeInvestDataRecAggrDto> investDataHolder = new InvestDataHolder<>(() -> pledgeInvestDataRecAggrDto);
//        PledgeInvestDataRecAggrDto.compareInvestDataAndAggregate(evt,
//                this,
//                investDataHolder);
//    }


}
