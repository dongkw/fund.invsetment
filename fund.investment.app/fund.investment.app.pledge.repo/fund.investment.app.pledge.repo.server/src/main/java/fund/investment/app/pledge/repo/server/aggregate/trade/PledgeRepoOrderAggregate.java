package fund.investment.app.pledge.repo.server.aggregate.trade;

import fund.investment.app.pledge.repo.api.command.trade.*;
import fund.investment.app.pledge.repo.api.event.trade.*;
import fund.investment.app.pledge.repo.server.aggregate.trade.status.*;
import fund.investment.basic.common.enums.dict.CMatchFlagEnums;
import fund.investment.basic.common.enums.dict.entrust.CEntrustStatusEnums;
import fund.investment.basic.common.enums.dict.entrust.COfferStatusEnums;
import fund.investment.basic.common.enums.dict.entrust.COperateTypeEnums;
import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.common.valueobject.InterbankPledgeBond;
import fund.investment.basic.trade.api.command.*;
import fund.investment.basic.trade.api.dto.OrderDataHolder;
import fund.investment.basic.trade.api.event.*;
import fund.investment.basic.trade.server.aggregate.OrderAggregate;
import fund.investment.basic.trade.server.aggregate.state.*;
import fund.investment.gateway.api.compliance.command.order.pledge.PRCreateNewRecOrderCmd;
import fund.investment.gateway.api.compliance.command.order.pledge.PRDataRecPlacingOrderCmd;
import fund.investment.gateway.api.compliance.command.order.pledge.PROppositeModifyOrderCmd;
import fund.investment.gateway.api.compliance.event.order.pledge.PROrderDraModifyingEvt;
import fund.investment.gateway.api.compliance.event.order.pledge.PROrderModifyingEvt;
import fund.investment.gateway.api.compliance.event.order.pledge.PROrderPlacingEvt;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Getter
@Setter
@Slf4j
@NoArgsConstructor
//@AllArgsConstructor
@Aggregate(snapshotTriggerDefinition = "snapshotTrigger")
public class PledgeRepoOrderAggregate extends OrderAggregate {

    private PledgeRepoOrderAggregate originPledgeRepoOrderAggregate;

    /**
     * 回购天数
     */
    @ApiModelProperty(value = "回购天数", example = "", notes = "回购天数", required = true)
    private Long ftRepDays;

    /**
     * 首期结算金额
     */
    private BigDecimal ftFirstSettleAmt;

    /**
     * 到期结算金额:用于银行间回购
     */
    private BigDecimal ftSecondSettleAmt;

    /**
     * 首期应计利息:债券业务和买断式回购存债券百元利率，债券借贷业务存利息总额
     */
    private BigDecimal ftFirstInterest;

    /**
     * 到期应计利息:用于银行间买断式回购业务
     */
    private BigDecimal ftSecondInterest;

    /**
     * 质押券列表
     */
    @ApiModelProperty(value = "质押券列表", example = "", notes = "新增草稿委托时需要")
    private List<InterbankPledgeBond> pledgeBondList;


    /**
     * 指令委托手工匹配
     *
     * @param cmd
     */
    @CommandHandler
    public void on(PRMatchOrderCmd cmd) {
        log.debug("接收到指令委托手工匹配命令:{}", cmd);
//        setOrderAggrState(new UnmatchOrderStateImpl(getOrderAggrState().getOrderState()));
        PRMatchRemoteEvt evt = new PRMatchRemoteEvt();
        BeanUtils.copyPropertiesIgnoreNull(cmd, evt);
        AggregateLifecycle.apply(evt);
        getOrderAggrState().matching(cmd);
    }

    /**
     * 指令委托取消匹配
     *
     * @param cmd
     */
    @CommandHandler
    public void on(PRCancelMatchOrderCmd cmd) {
        log.debug("接收到取消匹配命令:{}", cmd);
        PRCancelMatchRemoteEvt evt = new PRCancelMatchRemoteEvt();
        BeanUtils.copyPropertiesIgnoreNull(cmd, evt);
        AggregateLifecycle.apply(evt);
        getOrderAggrState().unmatching(cmd);
    }

    @CommandHandler
    public void on(PROrderMatchRollbackCmd cmd) {
        PRMatchRemoteFailEvt evt = new PRMatchRemoteFailEvt();
        BeanUtils.copyPropertiesIgnoreNull(cmd, evt);
        AggregateLifecycle.apply(evt);
        log.debug("手工匹配远端调用返回失败:{}", cmd);
        getOrderAggrState().matchRollback(cmd);
    }

    @CommandHandler
    public void on(PROrderCancelMatchRollbackCmd cmd) {
        PRCancelMatchRemoteFailEvt evt = new PRCancelMatchRemoteFailEvt();
        BeanUtils.copyPropertiesIgnoreNull(cmd, evt);
        AggregateLifecycle.apply(evt);
        log.debug("取消匹配远端调用返回失败:{}", cmd);
        getOrderAggrState().unmatchRollback(cmd);
    }

    /**
     * 指令委托匹配成功
     *
     * @param cmd
     */
    @CommandHandler
    public void on(PROrderMatchRemoteSuccessCmd cmd) {
        log.debug("接收到指令委托匹配成功命令:{}", cmd);
        // controller接收 返回消息
        PRMatchRemoteSuccessEvt successEvt = new PRMatchRemoteSuccessEvt();
        successEvt.setRequestId(cmd.getRequestId());
        AggregateLifecycle.apply(successEvt);
        log.debug("发送成功事件给controller:{}", successEvt);
        getOrderAggrState().match(cmd);
    }


    /**
     * 指令委托匹配成功
     *
     * @param cmd
     */
    @CommandHandler
    public void on(PROrderCancelMatchRemoteSuccessCmd cmd) {
        log.debug("接收到指令委托取消匹配成功命令:{}", cmd);
        // controller接收 返回消息
        PRCancelMatchRemoteSuccessEvt successEvt = new PRCancelMatchRemoteSuccessEvt();
        successEvt.setRequestId(cmd.getRequestId());
        AggregateLifecycle.apply(successEvt);
        getOrderAggrState().cancelMatch(cmd);
    }


    @CommandHandler
    public PledgeRepoOrderAggregate(PRCreateOrderCmd cmd) {
        log.debug("接收到委托创建命令:{}", cmd);
        PROrderCreatedEvt evt = new PROrderCreatedEvt();
        BeanUtils.copyProperties(cmd, evt);
        AggregateLifecycle.apply(evt);

        log.info("发送委托创建事件: {}", evt);
    }

    @CommandHandler
    public PledgeRepoOrderAggregate(PRCreateNewRecOrderCmd cmd) {
        log.debug("接收到未匹配委托创建命令:{}", cmd);
        PRNewRecOrderCreatedEvt evt = new PRNewRecOrderCreatedEvt();
        BeanUtils.copyProperties(cmd, evt);
        AggregateLifecycle.apply(evt);

        log.info("发送未匹配委托创建事件: {}", evt);
    }

    @CommandHandler
    public void on(PRFailOrderCmd cmd) {
        log.debug("接收到委托失败命令:{}", cmd);
        getOrderAggrState().getOrderState().fail(cmd);
    }

    @CommandHandler
    public void on(PRCancelOrderCmd cmd) {
        log.debug("接收到委托撤销命令:{}", cmd);
        getOrderAggrState().getOrderState().cancel(cmd);
    }

    @CommandHandler
    public void on(PRModifyOrderCmd cmd) {
        log.debug("接收到委托修改命令:{}", cmd);
        getOrderAggrState().getOrderState().modify(cmd);
    }

    @CommandHandler
    public void on(PRChangeModifyingOrderCmd cmd) {
        log.debug("接收到委托修改中命令:{}", cmd);
        getOrderAggrState().getOrderState().modifying(cmd);
    }


    @CommandHandler
    public void on(PRDeleteOrderCmd cmd) {
        log.debug("接收到委托删除命令:{}", cmd);
        getOrderAggrState().getOrderState().delete(cmd);
    }

    @CommandHandler
    public void on(PROppositeModifyOrderCmd cmd) {
        log.debug("接收到对手方修改命令:{}", cmd);
        getOrderAggrState().getOrderState().oppoModify(cmd);
    }

    /**
     * 发送委托
     *
     * @param cmd
     */
    @CommandHandler
    public void handle(PRPlaceOrderCmd cmd) {
        log.debug("接收到发送委托命令:{}", cmd);
        getOrderAggrState().getOrderState().placing(cmd, this);
    }


    /**
     * 修改被拒绝，修改为原状态
     *
     * @param cmd
     */
    @CommandHandler
    public void handle(BackPlacedOrderCmd cmd) {
        log.debug("接收到委托拒绝命令:{}", cmd);
        getOrderAggrState().getOrderState().backPlaced(cmd);
    }


    /*---------------------------------------------*/


    @EventSourcingHandler
    public void on(PROrderModifyingEvt evt) {
        log.debug("委托发送中，开始处理发送中事件: {}", evt);
        this.originPledgeRepoOrderAggregate = new PledgeRepoOrderAggregate();
        //设置旧的委托状态值
//        setChEntrustStatus(CEntrustStatusEnums.MODIFYING.getSkId());
        BeanUtils.copyProperties(this, this.originPledgeRepoOrderAggregate);
        BeanUtils.copyPropertiesIgnoreNullAndId(evt, this);
    }


    /**
     * 委托发送中事件
     *
     * @param evt
     */
    @EventSourcingHandler
    public void on(OrderBackPlacedEvt evt) {
        log.debug("委托回退中，开始处理回退事件: {}", evt);
        BeanUtils.copyProperties(this.originPledgeRepoOrderAggregate, this);
        setId(evt.getId());
        getOrderAggrState().setOrderState(new PlacedOrderState());
    }


    /**
     * 手工匹配失败回滚事件
     *
     * @param evt
     */
    @EventSourcingHandler
    public void on(PRMatchRollbackEvt evt) {
        log.debug("手工匹配失败回滚事件: {}", evt);
        setOrderAggrState(new UnmatchOrderStateImpl(getOrderAggrState().getOrderState()));
    }


    /**
     * 手工取消匹配失败回滚事件
     *
     * @param evt
     */
    @EventSourcingHandler
    public void on(PRCancelMatchRollbackEvt evt) {
        log.debug("手工取消匹配失败回滚事件: {}", evt);
        setOrderAggrState(new ManualMatchedOrderStateImpl(getOrderAggrState().getOrderState()));
    }


    /**
     * 委托发送中事件
     *
     * @param evt
     */
    @EventSourcingHandler
    public void on(PRChangeOrderPlacingEvt evt) {
        log.debug("委托发送中，开始处理发送中事件: {}", evt);
//        this.originPledgeRepoOrderAggregate = new PledgeRepoOrderAggregate();
//        //设置旧的委托状态值
//        BeanUtils.copyProperties(this, this.originPledgeRepoOrderAggregate);
//        BeanUtils.copyPropertiesIgnoreNullAndId(evt, this);
        //设置新的委托状态值
        setChEntrustStatus(CEntrustStatusEnums.REPORTING.getSkId());
        setChOperateType(COperateTypeEnums.MODIFY.getSkId());
        //聚合根id为原委托id
        getOrderAggrState().setOrderState(new PRPlacingOrderState());
    }

    /**
     * 委托发送中事件
     *
     * @param evt
     */
    @EventSourcingHandler
    public void on(PROrderMatchingEvt evt) {
        log.debug("手动匹配中: {}", evt);
        setOrderAggrState(new MatchingOrderStateImpl(getOrderAggrState().getOrderState()));
    }

    /**
     * 委托发送中事件
     *
     * @param evt
     */
    @EventSourcingHandler
    public void on(PROrderUnmatchingEvt evt) {
        log.debug("取消匹配中: {}", evt);
        setOrderAggrState(new UnmatchingOrderStateImpl(getOrderAggrState().getOrderState()));
    }


    /**
     * 委托发送中事件
     *
     * @param evt
     */
    @EventSourcingHandler
    public void on(PROrderPlacingEvt evt) {
        log.debug("委托发送中，开始处理发送中事件: {}", evt);
        setChEntrustStatus(CEntrustStatusEnums.REPORTING.getSkId());
        setChOfferStatus(COfferStatusEnums.NORMAL.getSkId());
        getOrderAggrState().setOrderState(new PRPlacingOrderState());
    }

    /**
     * 未报委托删除事件
     *
     * @param evt
     */
    @EventSourcingHandler
    public void on(OrderDeleteEvt evt) {
        log.debug("委托删除，开始处理删除事件: {}", evt);
        getOrderAggrState().setOrderState(new DeletedOrderState());
    }

    /**
     * 已确认委托修改事件
     *
     * @param evt
     */
    @EventSourcingHandler
    public void on(PROrderUpdatedEvt evt) {
        log.debug("委托修改，开始处理修改事件: {}", evt);
        BeanUtils.copyProperties(evt, this);
    }

    /**
     * 已报委托修改事件
     *
     * @param evt
     */
    @EventSourcingHandler
    public void on(PROrderModifiedEvt evt) {
        log.debug("委托修改，开始处理修改事件: {}", evt);
        //TODO 调用外部接口
        BeanUtils.copyPropertiesIgnoreNull(evt, this);
        setOrderAggrState(new NoNeedMatchOrderStateImpl(getOrderAggrState().getOrderState()));
    }

    @EventSourcingHandler
    public void on(PRNewRecOrderCreatedEvt evt) {
        log.debug("对手方报价委托开始创建，开始处理对手方报价创建事件: {}", evt);
        //匹配状态
        setChMatchFlag(evt.getChMatchFlag());
        //委托状态
        setChEntrustStatus(evt.getChEntrustStatus());

        if (Objects.equals("0", evt.getIsMatch())) {
            setOrderAggrState(new UnmatchOrderStateImpl(new PRPlacedNewRecOrderStateImpl()));
        } else {
            setOrderAggrState(new AutoMatchedOrderStateImpl(new PRPlacedNewRecOrderStateImpl()));
        }
        this.setOrderSelfData(evt);
        this.setPledgeOrderData(evt);
    }

    @EventSourcingHandler
    public void on(PROrderCreatedEvt evt) {
        log.debug("委托开始创建，开始处理创建事件: {}", evt);
        //匹配状态
        setChMatchFlag(evt.getChMatchFlag());
        //委托状态
        setChEntrustStatus(evt.getChEntrustStatus());
        //首先默认匹配状态设置为无需匹配
        setOrderAggrState(new NoNeedMatchOrderStateImpl(new PRCreatedOrderState()));

        this.setOrderSelfData(evt);
        this.setPledgeOrderData(evt);
    }

    private void setPledgeOrderData(PROrderCreatedEvt evt) {
        this.setFtRepDays(evt.getFtRepDays());
        this.setFtFirstSettleAmt(evt.getFtFirstSettleAmt());
        this.setFtSecondSettleAmt(evt.getFtSecondSettleAmt());
        this.setFtFirstInterest(evt.getFtFirstInterest());
        this.setFtSecondInterest(evt.getFtSecondInterest());

        this.setPledgeBondList(evt.getPledgeBondList());
    }

    private void setOrderSelfData(PROrderCreatedEvt evt) {
        setId(evt.getId());
        setSkId(evt.getSkId());
        setSkInstr(evt.getSkInstr());
        setDtCurrentDate(evt.getDtCurrentDate());
        setSkId(evt.getSkId());
        setSkProduct(evt.getSkProduct());
        setSkCombi(evt.getSkCombi());
        setChInvestType(evt.getChInvestType());
        setChStockholderCode(evt.getChStockholderCode());
        setSkSeat(evt.getSkSeat());
        setSkSecurity(evt.getSkSecurity());
        setSkMarket(evt.getSkMarket());
        setSkMarktExe(evt.getSkMarktExe());
        setSkCurrOri(evt.getSkCurrOri());
        setSkTradeType(evt.getSkTradeType());
        setSkSecurityReport(evt.getSkSecurityReport());
        setChReportNo(evt.getChReportNo());
        setChCancelNo(evt.getChCancelNo());
        setChConfirmNo(evt.getChConfirmNo());
        setChCancelFlag(evt.getChCancelFlag());
        setChCashDirection(evt.getChCashDirection());
        setChSecuDirection(evt.getChSecuDirection());
        setChReportDirection(evt.getChReportDirection());
        setFtEntrustPrice(evt.getFtEntrustPrice());
        setFtFactPrice(evt.getFtFactPrice());
        setFtEntrustQtty(evt.getFtEntrustQtty());
        setFtEntrustAmt(evt.getFtEntrustAmt());
        setDtEntrustDate(evt.getDtEntrustDate());
        setChEntrustTime(evt.getChEntrustTime());
        setChReportTime(evt.getChReportTime());
        setChFirstTradeTime(evt.getChFirstTradeTime());
        setFtTradeQtty(evt.getFtTradeQtty());
        setFtTradeAmt(evt.getFtTradeAmt());
        setFtCancelQtty(evt.getFtCancelQtty());
        setChTradeTimes(evt.getChTradeTimes());
        setSkTradeUserId(evt.getSkTradeUserId());
        setChCancelReason(evt.getChCancelReason());

        setChFrozenType(evt.getChFrozenType());
        setChEntrustType(evt.getChEntrustType());
        setChEntrustSource(evt.getChEntrustSource());
        setChOfferStatus(evt.getChOfferStatus());
        setChOperateType(evt.getChOperateType());
        setChSettleDays(evt.getChSettleDays());
        setChApplyCompleteFlag(evt.getChApplyCompleteFlag());
        setChFixsendFlag(evt.getChFixsendFlag());
        setChInstrSource(evt.getChInstrSource());
        setChSourceKey(evt.getChSourceKey());
        setChSourceNo(evt.getChSourceNo());
        setChInstrSourceTwo(evt.getChInstrSourceTwo());
        setChSourceKeyTwo(evt.getChSourceKeyTwo());
        setChSourceNoTwo(evt.getChSourceNoTwo());
        setSkInst(evt.getSkInst());
        setChTraderId(evt.getChTraderId());
        setTsTimestamp(evt.getTsTimestamp());
        setChMemo(evt.getChMemo());
        setSkKeyId(evt.getSkKeyId());
        setChDelete(evt.getChDelete());
        setChCreateId(evt.getChCreateId());
        setTsCreateTime(evt.getTsCreateTime());
        setChLastModifiedId(evt.getChLastModifiedId());
        setTsLastModifiedTime(evt.getTsLastModifiedTime());
        setChDataSource(evt.getChDataSource());
        setChAuditorId(evt.getChAuditorId());
        setChAuditStatus(evt.getChAuditStatus());
        setTsAuditTime(evt.getTsAuditTime());
        setChBankQuoteType(evt.getChBankQuoteType());
        setChClearType(evt.getChClearType());
        setDtBeginDate(evt.getDtBeginDate());
        setChBeginTime(evt.getChBeginTime());
        setDtInstrEndDate(evt.getDtInstrEndDate());
        setChEndTime(evt.getChEndTime());
    }

    @EventSourcingHandler
    public void on(OrderCancelledEvt evt) {
        log.debug("委托确认撤销，开始处理确认撤销事件: {}", evt);
        setChEntrustStatus(evt.getChEntrustStatus());
        getOrderAggrState().setOrderState(new CancelledOrderState());
    }

    @EventSourcingHandler
    public void on(OrderFilledEvt evt) {
        log.debug("委托成交，开始处理成交事件: {}", evt);
        completedIfSatisfy(evt);
    }

    private void completedIfSatisfy(OrderFilledEvt evt) {
        OrderCompletedEvt toEvt = new OrderCompletedEvt(
                evt.getId(),
                evt.getTradeType(),
                evt.getInstructionId(),
                evt.getUserId(),
                evt.getSkId(),
                evt.getChLastModifiedId(),
                evt.getTsLastModifiedTime()
        );
        toEvt.setChEntrustStatus(evt.getChEntrustStatus());
        if (Objects.nonNull(evt.getChEntrustStatus())) {
            setChEntrustStatus(evt.getChEntrustStatus());
        } else {
            setChEntrustStatus(CEntrustStatusEnums.DEALED.getSkId());
        }
        AggregateLifecycle.apply(toEvt);
        log.debug("发送委托全部成交完成事件:{}", evt);
    }

    /**
     * 委托创建确认事件
     *
     * @param evt
     */
    @EventSourcingHandler
    public void on(OrderConfirmedEvt evt) {
        log.debug("委托已确认创建，开始处理创建确认事件: {}", evt);
        getOrderAggrState().setOrderState(new PRConfirmedOrderState());
    }

    /**
     * 委托已报事件
     *
     * @param evt
     */
    @EventSourcingHandler
    public void on(OrderPlacedEvt evt) {
        log.debug("委托已报，开始处理已报事件: {}", evt);
        setChEntrustStatus(evt.getChEntrustStatus());
        setChLastModifiedId(evt.getChLastModifiedId());
        setTsLastModifiedTime(evt.getTsLastModifiedTime());
        getOrderAggrState().setOrderState(new PRPlacedOrderState());
    }

    /**
     * 对手方修改事件
     *
     * @param evt
     */
    @EventSourcingHandler
    public void on(PROppositeOrderModifiedEvt evt) {
        log.debug("对手方修改事件，开始处理对手方修改事件: {}", evt);

        this.originPledgeRepoOrderAggregate = new PledgeRepoOrderAggregate();
        //设置旧的委托状态值
        BeanUtils.copyProperties(this, this.originPledgeRepoOrderAggregate);
        BeanUtils.copyPropertiesIgnoreNullAndId(evt, this);

    }

    @EventSourcingHandler
    public void on(PROrderDraModifyingEvt evt) {
        log.debug("草稿修改: {}", evt);
        BeanUtils.copyPropertiesIgnoreNullAndId(evt, this);
    }

    /**
     * 接收mq同步消息
     *
     * @param cmd
     */
    @CommandHandler
    public void handle(PRDataRecPlacingOrderCmd cmd) {
        log.debug("接收到委托数据同步命令:{}", cmd);
        if (this.getTsAuditTime() != null) {
            //判断修改时间是否大于上次修改时间
            if (!(cmd.getTsAuditTime().getTime() - this.getTsAuditTime().getTime() > 0)) {
                //修改时间判定不通过则不修改数据
                return;
            }
        }
        this.setTsAuditTime(cmd.getTsAuditTime());

        OrderDataHolder<PRDataRecOrderEvt> orderDataHolder = new OrderDataHolder<>(PRDataRecOrderEvt::new);

//        PledgeOrderDataRecAggrDto.compareDataAndAggregate(cmd,
//                this,
//                orderDataHolder,
//                this::doEntrustMatch,
//                this::doEntrustOffer,
//                this::doEntrustStatus);

//        PledgeOrderDataRecAggrDto.comparePledgeDataAndAggregate(cmd, this, orderDataHolder);
//        //全部数据同步功能
//        if (orderDataHolder.getOrderEvt() != null) {
//            //有数据需要修改，发送数据同步evt
//            PRDataRecOrderEvt orderEvt = orderDataHolder.getOrderEvt();
//
//            orderEvt.setId(this.getId());
//            orderEvt.setSkId(this.getSkId());
////            orderEvt.setSkInstr(cmd.getSkInstr());
//            AggregateLifecycle.apply(orderEvt);
//            log.debug("发送数据同步事件:{}", orderEvt);
//        } else {
//            log.debug("数据没有变动：{}", cmd);
//        }
    }

//    @EventSourcingHandler
//    public void on(PRDataRecOrderEvt evt) {
//        log.debug("开始处理数据同步事件: {}", evt);
//        PledgeOrderDataRecAggrDto pledgeOrderDataRecAggrDto = new PledgeOrderDataRecAggrDto(this);
//        OrderDataHolder<PledgeOrderDataRecAggrDto> orderDataHolder = new OrderDataHolder<>(() -> pledgeOrderDataRecAggrDto);
//
//        PledgeOrderDataRecAggrDto.compareDataAndAggregate(evt,
//                this,
//                orderDataHolder,
//                (orderCmd, orderAggregate) -> {
//                },
//                (orderCmd, orderAggregate) -> {
//                },
//                (orderCmd, orderAggregate) -> {
//                });
//
////        PledgeOrderDataRecAggrDto.comparePledgeDataAndAggregate(evt, this, orderDataHolder);
//    }


    private void doEntrustStatus(PRDataRecPlacingOrderCmd orderCmd, PledgeRepoOrderAggregate orderAggregate) {
        String chEntrustStatus = orderCmd.getChEntrustStatus();
        String oldStatus = orderAggregate.getChEntrustStatus();
        if (Objects.equals(CEntrustStatusEnums.DEALED.getSkId(), chEntrustStatus)) {
            if (Objects.equals(CEntrustStatusEnums.CONFIRMING.getSkId(), oldStatus)) {
                ConfirmedFillCmd cmd = new ConfirmedFillCmd();

                cmd.setUserId(orderCmd.getUserId());
                cmd.setChLastModifiedId(orderCmd.getChLastModifiedId());
                cmd.setTsLastModifiedTime(new Date());

                cmd.setInstructionId(orderCmd.getInstrSkInstr());
                cmd.setSkId(orderCmd.getSkId());
                cmd.setId(orderAggregate.getId());

                orderAggregate.getOrderAggrState().getOrderState().fillConfirm(cmd);
                log.debug("收到已成事件，发出成交确认命令：{}", cmd);
            } else {
                FillOrderCmd fillOrderCmd = new FillOrderCmd();

                fillOrderCmd.setSkInstr(orderCmd.getSkInstr());
                fillOrderCmd.setChLastModifiedId(orderCmd.getChLastModifiedId());
                fillOrderCmd.setTsLastModifiedTime(orderCmd.getTsLastModifiedTime());

                fillOrderCmd.setId(orderAggregate.getId());
                fillOrderCmd.setSkId(orderCmd.getSkId());
                fillOrderCmd.setInstructionId(orderCmd.getInstrSkInstr());
                fillOrderCmd.setChEntrustStatus(orderCmd.getChEntrustStatus());

                orderAggregate.getOrderAggrState().getOrderState().fill(fillOrderCmd);
                log.debug("收到已成事件，发出成交命令：{}", fillOrderCmd);
            }
        } else if (Objects.equals(CEntrustStatusEnums.REPORTED.getSkId(), chEntrustStatus)) {//已报
            PlaceConfirmOrderCmd placeConfirmOrderCmd = new PlaceConfirmOrderCmd();
            BeanUtils.copyProperties(orderCmd, placeConfirmOrderCmd);
            placeConfirmOrderCmd.setId(orderAggregate.getId());
            //发送成交状态
            getOrderAggrState().getOrderState().placed(placeConfirmOrderCmd);
            log.debug("收到已报事件，发出已报命令：{}", placeConfirmOrderCmd);
        } else if (Objects.equals(CEntrustStatusEnums.CANCELED.getSkId(), chEntrustStatus)) {
            if (!Objects.equals(CEntrustStatusEnums.WAITING_CANCEL.getSkId(), oldStatus)) {
                getOrderAggrState().setOrderState(new CancellingOrderState());
            }
            CancelConfirmOrderCmd cancelConfirmOrderCmd = new CancelConfirmOrderCmd();
            cancelConfirmOrderCmd.setId(orderAggregate.getId());
            cancelConfirmOrderCmd.setSkId(orderCmd.getSkId());
            cancelConfirmOrderCmd.setInstructionId(orderCmd.getInstrSkInstr());

            cancelConfirmOrderCmd.setUserId(orderCmd.getUserId());
            cancelConfirmOrderCmd.setChLastModifiedId(orderCmd.getChLastModifiedId());
            cancelConfirmOrderCmd.setTsLastModifiedTime(orderCmd.getTsLastModifiedTime());
            cancelConfirmOrderCmd.setChEntrustStatus(orderCmd.getChEntrustStatus());

            getOrderAggrState().getOrderState().cancelConfirm(cancelConfirmOrderCmd);
            log.debug("收到撤销报单事件，发出撤销报单命令：{}", cancelConfirmOrderCmd);
        }

        //对手方修改报价
        if (Objects.equals(CEntrustStatusEnums.NEW_RECEIVE.getSkId(), orderAggregate.getChEntrustStatus())
                && (Objects.equals(CEntrustStatusEnums.MODIFIED.getSkId(), chEntrustStatus)
                || Objects.equals(CEntrustStatusEnums.WASTE.getSkId(), chEntrustStatus))) {
            PROppositeModifyOrderCmd cmd = new PROppositeModifyOrderCmd();
            BeanUtils.copyProperties(orderCmd, cmd);
            cmd.setId(orderAggregate.getId());
            cmd.setSkId(orderAggregate.getSkId());
            getOrderAggrState().getOrderState().oppoModify(cmd);
        }

        if (Objects.equals(CEntrustStatusEnums.MODIFIED.getSkId(), chEntrustStatus)) {
            // 正常修改报价成功
            ChangeModifiedOrderCmd changeModifiedOrderCmd = new ChangeModifiedOrderCmd();
            BeanUtils.copyProperties(orderCmd, changeModifiedOrderCmd);
            changeModifiedOrderCmd.setId(orderAggregate.getId());
            orderAggregate.getOrderAggrState().getOrderState().modified(changeModifiedOrderCmd);
            log.debug("发出同步回来的报价修改事件：{}", changeModifiedOrderCmd);
        }

        if (Objects.equals(CEntrustStatusEnums.MODIFYING.getSkId(), oldStatus)
                && Objects.equals(COfferStatusEnums.NORMAL.getSkId(), orderAggregate.getChOfferStatus())) {
            if (Objects.equals(CEntrustStatusEnums.REPORTED.getSkId(), chEntrustStatus)) {
                BackPlacedOrderCmd cmd = new BackPlacedOrderCmd();
                BeanUtils.copyProperties(orderCmd, cmd);
                cmd.setId(orderAggregate.getId());
                orderAggregate.getOrderAggrState().getOrderState().backPlaced(cmd);
                log.debug("收到报价修改被拒绝事件，发出回退报价命令：{}", cmd);
            }
        }

        //修改报价被拒绝 || 报价被拒绝
        if (Objects.equals(CEntrustStatusEnums.REPORTING.getSkId(), oldStatus)
                && Objects.equals(COfferStatusEnums.NORMAL.getSkId(), getChOfferStatus())) {
            if (Objects.equals(CEntrustStatusEnums.WASTE.getSkId(), chEntrustStatus)
                    && Objects.equals(COfferStatusEnums.BANK_REPORT_REFUSE.getSkId(), orderCmd.getChOfferStatus())) {
                //外汇交易中心拒绝报价
                ChangeRefusedOrderCmd cmd = new ChangeRefusedOrderCmd();
                BeanUtils.copyProperties(orderCmd, cmd);
                cmd.setId(orderAggregate.getId());
                orderAggregate.getOrderAggrState().getOrderState().refuse(cmd);
            }
        }
        setChEntrustStatus(chEntrustStatus);
    }

    private void doEntrustOffer(PRDataRecPlacingOrderCmd orderCmd, PledgeRepoOrderAggregate orderAggregate) {
        String chOfferStatus = orderCmd.getChOfferStatus();
        if (Objects.equals(COfferStatusEnums.REJECT.getSkId(), chOfferStatus)) {
            if (!Objects.equals(CEntrustStatusEnums.REJECTING.getSkId(), orderAggregate.getChEntrustStatus())) {
                getOrderAggrState().setOrderState(new OrderRejectConfirmingState());
            }
            ConfirmRejectOrderCmd confirmRejectOrderCmd = new ConfirmRejectOrderCmd();
            confirmRejectOrderCmd.setId(orderAggregate.getId());
            confirmRejectOrderCmd.setSkId(orderCmd.getSkId());
            confirmRejectOrderCmd.setChLastModifiedId(orderCmd.getChLastModifiedId());
            confirmRejectOrderCmd.setTsLastModifiedTime(orderCmd.getTsLastModifiedTime());

            orderAggregate.getOrderAggrState().getOrderState().rejectOrderConfirm(confirmRejectOrderCmd);
            log.debug("收到拒绝确认事件，发出拒绝确认命令：{}", confirmRejectOrderCmd);
        }

        setChOfferStatus(chOfferStatus);
    }

    private void doEntrustMatch(PRDataRecPlacingOrderCmd orderCmd, PledgeRepoOrderAggregate orderAggregate) {
        String chMatchFlag = orderCmd.getChMatchFlag();
        if (Objects.equals(CMatchFlagEnums.MATCH_FLAG_ENUMS_AUTO.getSkId(), chMatchFlag)) {
            AutoMatchOrderCmd cmd = new AutoMatchOrderCmd();

            cmd.setSkId(orderCmd.getSkId());
            cmd.setSkInstr(orderCmd.getSkInstr());
            cmd.setSkCombi(orderCmd.getSkCombi());
            cmd.setChLastModifiedId(orderCmd.getChLastModifiedId());
            cmd.setTsLastModifiedTime(new Date());
            cmd.setUserId(orderCmd.getSkTradeUserId());

            cmd.setChMatchFlag(chMatchFlag);

            cmd.setId(orderAggregate.getId());
            orderAggregate.getOrderAggrState().autoMatch(cmd);
            log.debug("收到自动匹配事件，发出自动匹配命令：{}", cmd);
        } else if (Objects.equals(CMatchFlagEnums.MATCH_FLAG_ENUMS_MANUL.getSkId(), chMatchFlag)) {
            //TODO 匹配状态数据同步
        } else if (Objects.equals(CMatchFlagEnums.MATCH_FLAG_ENUMS_CANCEL.getSkId(), chMatchFlag)) {

        } else if (Objects.equals(CMatchFlagEnums.MATCH_FLAG_ENUMS_NO.getSkId(), chMatchFlag)) {

        } else if (Objects.equals(CMatchFlagEnums.MATCH_FLAG_ENUMS_DONOT.getSkId(), chMatchFlag)) {
            AutoCancelMatchOrderCmd cmd = new AutoCancelMatchOrderCmd();
            cmd.setId(orderAggregate.getId());
            cmd.setChLastModifiedId(orderCmd.getChLastModifiedId());
            cmd.setTsLastModifiedTime(new Date());
            cmd.setInstrSkId(orderCmd.getSkInstr());
            cmd.setSkId(orderCmd.getSkId());
            cmd.setUserId(orderCmd.getSkTradeUserId());
            cmd.setChMatchFlag(chMatchFlag);
            orderAggregate.getOrderAggrState().autoCancel(cmd);
            log.debug("收到取消匹配事件，发出取消匹配命令：{}", cmd);

        }

        setChMatchFlag(chMatchFlag);
    }


}
