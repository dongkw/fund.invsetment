package fund.investment.basic.trade.server.aggregate;

import fund.investment.basic.common.DomainAggregate;
import fund.investment.basic.common.enums.dict.entrust.CEntrustStatusEnums;
import fund.investment.basic.trade.api.command.*;
import fund.investment.basic.trade.api.event.*;
import fund.investment.basic.trade.server.aggregate.state.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateLifecycle;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class OrderAggregate extends DomainAggregate {

    private OrderAggrState orderAggrState;


    private String insSourceKey;

    private String insSourceNo;

    private String instrSkInstr;

    private String skAsset;

    /**
     * 唯一主键:uuid
     */
    private String skId;

    /**
     * 指令表主键:投资指令表中的唯一主键SK_ID
     */
    private String skInstr;

    /**
     * 当前日期:当前系统工作日
     */
    private Date dtCurrentDate;

    /**
     * 产品代理键
     */
    @ApiModelProperty(value = "产品代理键", example = "", notes = "产品代理键", required = true)
    private String skProduct;

    /**
     * 投资组合代理键
     */
    @ApiModelProperty(value = "投资组合代理键", example = "", notes = "投资组合代理键", required = true)
    private String skCombi;

    /**
     * 投资分类:数据字典：0012
     */
    private String chInvestType;

    /**
     * 股东代码:关联产品账户子表-交易所证券账户
     */
    private String chStockholderCode;

    /**
     * 席位代理键:关联席位信息表
     */
    private String skSeat;

    /**
     * 证券代理键:保存委托的上市证券代码
     */
    @ApiModelProperty(value = "证券代理键", example = "", notes = "证券代理键", required = true)
    private String skSecurity;

    /**
     * 交易市场代理键
     */
    @ApiModelProperty(value = "交易市场代理键", example = "", notes = "交易市场代理键", required = true)
    private String skMarket;

    /**
     * 执行市场代理键
     */
    private String skMarktExe;

    /**
     * 原币币种代理键
     */
    private String skCurrOri;

    /**
     * 交易类型代理键
     */
    @ApiModelProperty(value = "交易类型代理键", example = "", notes = "交易类型代理键", required = true)
    private String skTradeType;

    /**
     * 申报证券代理键:有些业务申报时的证券代码和上市代码不一致
     */
    private String skSecurityReport;

    /**
     * 申报编号
     */
    private String chReportNo;

    /**
     * 被撤单委托序号
     */
    private String chCancelNo;

    /**
     * 委托确认号
     */
    private String chConfirmNo;

    /**
     * 撤单标志:数据字典：7170；0：否；1：正常撤单；2：内部撤单；3：异常撤单；
     */
    private String chCancelFlag;

    /**
     * 资金方向:数据字典：7171；0：不变；1：增加；2：减少
     */
    private String chCashDirection;

    /**
     * 证券方向:数据字典：7171；0：不变；1：增加；2：减少
     */
    private String chSecuDirection;

    /**
     * 申报方向:数据字典：7187
     */
    private String chReportDirection;

    /**
     * 委托价格:回购，存款业务保存利率
     */
    @ApiModelProperty(value = "委托价格", example = "", notes = "委托价格", required = true)
    private BigDecimal ftEntrustPrice;

    /**
     * 实际价格:回购，存款业务保存利率
     */
    private BigDecimal ftFactPrice;

    /**
     * 委托数量
     */
    private BigDecimal ftEntrustQtty;

    /**
     * 委托金额
     */
    private BigDecimal ftEntrustAmt;

    /**
     * 委托日期
     */
    private Date dtEntrustDate;

    /**
     * 委托时间:HH:MM:SS
     */
    private String chEntrustTime;

    /**
     * 申报时间:HH:MM:SS
     */
    private String chReportTime;

    /**
     * 首成交时间:HH:MM:SS
     */
    private String chFirstTradeTime;

    /**
     * 当天成交数量
     */
    private BigDecimal ftTradeQtty;

    /**
     * 当天成交金额
     */
    private BigDecimal ftTradeAmt;

    /**
     * 撤单数量
     */
    private BigDecimal ftCancelQtty;

    /**
     * 分笔成交次数:每成交一次,次数+1,对于固定收益等有回滚的业务,回溯一次成交,本字段-1
     */
    private String chTradeTimes;

    /**
     * 交易员代理键
     */
    private String skTradeUserId;

    /**
     * 撤单原因
     */
    private String chCancelReason;

    /**
     * 委托状态:数据字典：7172
     */
    private String chEntrustStatus;

    /**
     * 委托冻结方式:数据字典：7173
     */
    private String chFrozenType;

    /**
     * 委托方式:数据字典：7174
     */
    private String chEntrustType;

    /**
     * 委托来源:数据字典：7175
     */
    private String chEntrustSource;

    /**
     * 报价状态:数据字典：7184
     */
    private String chOfferStatus;

    /**
     * 操作类别:数据字典：7188
     */
    private String chOperateType;

    /**
     * 配对标志:数据字典7046：0：未配对1：配对成功2：配对失败
     */
    private String chMatchFlag;

    /**
     * 清算速度:数据字典7042： 0：T+0，1：T+1
     */
    private String chSettleDays;

    /**
     * 申购完成标志
     */
    private String chApplyCompleteFlag;

    /**
     * fix发送标志
     */
    private String chFixsendFlag;

    /**
     * 指令来源1:增加数据字典7043：1、系统指令 2、总行指令 3、O32指令 4、方达指令
     */
    private String chInstrSource;

    /**
     * 指令来源主键ID:存对应系统的唯一指令的主键
     */
    private String chSourceKey;

    /**
     * 指令来源编号1:存对应系统的指令序号
     */
    private String chSourceNo;

    /**
     * 指令来源2:增加数据字典7043：1、系统指令 2、总行指令 3、O32指令 4、方达指令
     */
    private String chInstrSourceTwo;

    /**
     * 指令来源主键ID2:存对应系统的唯一指令的主键
     */
    private String chSourceKeyTwo;

    /**
     * 指令来源编号2:存对应系统的指令序号
     */
    private String chSourceNoTwo;

    /**
     * 交易对手代理键:对于存款存单业务，对应的是银行总行，该字段都是从机构信息表关联查询
     */
    @ApiModelProperty(value = "交易对手代理键", example = "", notes = "交易对手代理键", required = true)
    private String skInst;

    /**
     * 对手方交易员ID:交易对手交易员ID
     */
    @ApiModelProperty(value = "对手方交易员ID", example = "", notes = "对手方交易员ID", required = true)
    private String chTraderId;

    /**
     * 时间戳:缺省值为 数据库机器时间 sysdate
     */
    private Date tsTimestamp;

    /**
     * 备注
     */
    private String chMemo;

    /**
     * 场景ID:模拟试算时使用该字段切片数据
     */
    private String skKeyId;

    /**
     * 是否删除
     */
    private String chDelete;

    /**
     * 创建人
     */
    private String chCreateId;

    /**
     * 创建时间
     */
    private Date tsCreateTime;

    /**
     * 修改人
     */
    private String chLastModifiedId;

    /**
     * 修改时间
     */
    private Date tsLastModifiedTime;

    /**
     * 数据来源
     */
    private String chDataSource;

    /**
     * 审核人
     */
    private String chAuditorId;

    /**
     * 审核状态
     */
    private String chAuditStatus;

    /**
     * 审核时间
     */
    private Date tsAuditTime;

    /**
     * 银行间报价类型
     */
    private String chBankQuoteType;

    /**
     * 清算类型:数据字典：7189
     */
    private String chClearType;

    /**
     * 委托有效开始日期,YYYY-MM-DD
     */
    private Date dtBeginDate;

    /**
     * 委托有效开始时间,HH:MM:SS
     */
    private String chBeginTime;

    /**
     * 委托有效结束日期,YYYY-MM-DD
     */
    private Date dtInstrEndDate;

    /**
     * 委托有效结束时间,HH:MM:SS
     */
    private String chEndTime;


    /**
     * 保存委托
     *
     * @param cmd
     */
    @CommandHandler
    public void handle(ConfirmOrderCmd cmd) {
        log.debug("接收到委托保存确认命令:{}", cmd);
        getOrderAggrState().getOrderState().createConfirm(cmd);
    }


    /**
     * 委托确认
     *
     * @param cmd
     */
    @CommandHandler
    public void handle(PlaceConfirmOrderCmd cmd) {
        log.debug("接收到发送委托确认命令:{}", cmd);
        getOrderAggrState().getOrderState().placed(cmd);
    }

    /**
     * 撤销委托
     *
     * @param cmd
     */
    @CommandHandler
    public void handle(PlaceCancelOrderCmd cmd) {
        log.debug("接收到撤销委托命令:{}", cmd);
        getOrderAggrState().getOrderState().cancelling(cmd);
    }

    /**
     * 撤销委托确认
     *
     * @param cmd
     */
    @CommandHandler
    public void handle(CancelConfirmOrderCmd cmd) {
        log.debug("接收到撤销委托确认命令:{}", cmd);
        getOrderAggrState().getOrderState().cancelConfirm(cmd);
    }

    /**
     * 委托失败
     *
     * @param cmd
     */
    @CommandHandler
    public void handle(FailOrderCmd cmd) {
        log.debug("接收到委托失败废单命令:{}", cmd);
        getOrderAggrState().getOrderState().fail(cmd);
    }


    /**
     * 指令委托匹配
     *
     * @param cmd
     */
    @CommandHandler
    public void on(PRMatchOrderFailCmd cmd) {
        log.debug("接收到指令委托匹配失败命令:{}", cmd);
        OrderMatchFailEvt evt = new OrderMatchFailEvt(cmd.getId(),
                cmd.getInstructionId(),
                cmd.getTradeType(),
                cmd.getUserId(),
                cmd.getSkId(),
                cmd.getChLastModifiedId(),
                cmd.getTsLastModifiedTime(),
                cmd.getInstrSkId(),
                cmd.getInstrSkInstr(),
                cmd.getSkCombi());
        evt.setErrorMsg(((PRMatchOrderFailCmd) cmd).getErrorMsg());
        evt.setRequestId(cmd.getRequestId());
        AggregateLifecycle.apply(evt);
        log.debug("发送委托匹配失败事件:{}", evt);
    }

    /**
     * 指令委托取消匹配
     *
     * @param cmd
     */
    @CommandHandler
    public void on(PRCancelMatchOrderSuccessCmd cmd) {
        log.debug("接收到指令委托取消匹配成功命令:{}", cmd);
        getOrderAggrState().cancelMatch(cmd);
    }

    /**
     * 指令委托取消匹配
     *
     * @param cmd
     */
    @CommandHandler
    public void on(PRCancelMatchOrderFailCmd cmd) {
        log.debug("接收到指令委托取消匹配成功命令:{}", cmd);
        OrderCancelMatchFailEvt evt = new OrderCancelMatchFailEvt();
        evt.setErrorMsg(((PRCancelMatchOrderFailCmd) cmd).getErrorMsg());
        evt.setRequestId(cmd.getRequestId());
        AggregateLifecycle.apply(evt);
        log.debug("发送委托取消匹配失败事件:{}", evt);
    }


    /**
     * 委托自动匹配
     *
     * @param cmd
     */
    @CommandHandler
    public void on(AutoMatchOrderCmd cmd) {
        log.debug("接收到指令委托匹配命令:{}", cmd);
        getOrderAggrState().autoMatch(cmd);
    }

    /**
     * 撤销委托匹配
     *
     * @param cmd
     */
    @CommandHandler
    public void on(CancelMatchOrderCmd cmd) {
        log.debug("接收到撤销委托匹配命令:{}", cmd);
        getOrderAggrState().cancelMatch(cmd);
    }


    /**
     * 确认成交命令
     *
     * @param cmd
     */
    @CommandHandler
    public void on(ConfirmingFillCmd cmd) {
        log.debug("接收到成交确认中命令:{}", cmd);
        getOrderAggrState().fillConfirming(cmd);
    }

    /**
     * 成交命令
     *
     * @param cmd
     */
    @CommandHandler
    public void on(FillOrderCmd cmd) {
        log.debug("接收到委托成交命令:{}", cmd);
        getOrderAggrState().getOrderState().fill(cmd);
    }

    /**
     * 成交确认
     *
     * @param cmd
     */
    @CommandHandler
    public void on(ConfirmedFillCmd cmd) {
        log.debug("接收到成交确认命令:{}", cmd);
        getOrderAggrState().getOrderState().fillConfirm(cmd);
    }

    /**
     * 拒绝报价
     *
     * @param cmd
     */
    @CommandHandler
    public void on(RejectOrderCmd cmd) {
        log.debug("接收到拒绝委托命令:{}", cmd);
        setChEntrustStatus(CEntrustStatusEnums.REJECTING.getSkId());
        getOrderAggrState().getOrderState().rejectOrder(cmd);
    }

    /**
     * 拒绝确认
     *
     * @param cmd
     */
    @CommandHandler
    public void on(ConfirmRejectOrderCmd cmd) {
        log.debug("接收到拒绝确认命令:{}", cmd);
        getOrderAggrState().getOrderState().rejectOrderConfirm(cmd);
    }


    /**
     * 修改委托为发送中状态
     *
     * @param cmd
     */
    @CommandHandler
    public void handle(ChangeOrderPlacingCmd cmd) {
        log.debug("接收到发送委托命令:{}", cmd);
        getOrderAggrState().getOrderState().changePlacing(cmd);
    }

    /**
     * 修改委托为发送中状态
     *
     * @param cmd
     */
    @CommandHandler
    public void handle(ChangeModifiedOrderCmd cmd) {
        log.debug("接收到委托修改命令:{}", cmd);
        getOrderAggrState().getOrderState().modified(cmd);
    }

    /**
     * 修改委托为发送中状态
     *
     * @param cmd
     */
    @CommandHandler
    public void handle(ChangeRefusedOrderCmd cmd) {
        log.debug("接收到委托拒绝命令:{}", cmd);
        getOrderAggrState().getOrderState().refuse(cmd);
    }

    /**
     * 自动取消匹配命令
     *
     * @param cmd
     */
    @CommandHandler
    public void handle(AutoCancelMatchOrderCmd cmd) {
        log.debug("接收到委托拒绝命令:{}", cmd);
        getOrderAggrState().autoCancel(cmd);
    }


    /**
     * 成交确认中命令
     *
     * @param cmd
     */
    @CommandHandler
    public void on(ConfirmingFillSuccessCmd cmd) {
        log.debug("接收到成交确认中命令: {}", cmd);
        getOrderAggrState().getOrderState().confirmingSuccess(cmd);
    }


    /*-----------------------------------------------*/


    /**
     * 委托拒绝确认
     *
     * @param evt
     */
    @EventSourcingHandler
    public void on(OrderAutoCancelMatchedEvt evt) {
        log.debug("自动取消匹配事件: {}", evt);
        setSkTradeUserId(evt.getUserId());
        setChLastModifiedId(evt.getChLastModifiedId());
        setTsLastModifiedTime(evt.getTsLastModifiedTime());
        setOrderAggrState(new UnmatchOrderStateImpl(getOrderAggrState().getOrderState()));
    }


    /**
     * 委托拒绝确认
     *
     * @param evt
     */
    @EventSourcingHandler
    public void on(OrderRejectConfirmedEvt evt) {
        log.debug("拒绝确认，开始处理拒绝确认事件: {}", evt);
        getOrderAggrState().setOrderState(new FailedOrderState());
    }

    /**
     * 委托拒绝事件
     *
     * @param evt
     */
    @EventSourcingHandler
    public void on(OrderRejectedEvt evt) {
        log.debug("报价拒绝，开始处理报价拒绝事件: {}", evt);
        getOrderAggrState().setOrderState(new OrderRejectConfirmingState());
    }

//    /**
//     * 成交确认中事件
//     *
//     * @param evt
//     */
//    @EventSourcingHandler
//    public void on(FillConfirmingEvt evt) {
//        log.debug("成交确认中，开始处理成交确认中事件: {}", evt);
//        getOrderAggrState().setOrderState(new FillConfirmingState());
//    }

    /**
     * 成交确认中事件
     *
     * @param evt
     */
    @EventSourcingHandler
    public void on(OrderChangeConfirmingEvt evt) {
        log.debug("成交确认中，开始处理成交确认中事件: {}", evt);
        getOrderAggrState().setOrderState(new FillConfirmingState());
        setChEntrustStatus(CEntrustStatusEnums.CONFIRMING.getSkId());
    }

    /**
     * 成交确认中事件
     *
     * @param evt
     */
    @EventSourcingHandler
    public void on(FillConfirmedEvt evt) {
        log.debug("开始处理成交确认事件，成交完成，: {}", evt);
        getOrderAggrState().setOrderState(new CompletedOrderState());
    }

    /**
     * 委托失败事件
     *
     * @param evt
     */
    @EventSourcingHandler
    public void on(OrderFailedEvt evt) {
        log.debug("委托失败废单，开始处理废单事件: {}", evt);

        getOrderAggrState().setOrderState(new FailedOrderState());
    }

    /**
     * 委托待撤销事件
     *
     * @param evt
     */
    @EventSourcingHandler
    public void on(OrderCancellingEvt evt) {
        log.debug("委托待撤销，开始处理待撤销事件: {}", evt);
        setChEntrustStatus(CEntrustStatusEnums.WAITING_CANCEL.getSkId());
        getOrderAggrState().setOrderState(new CancellingOrderState());
    }

    /**
     * 委托已部分撤销事件
     *
     * @param evt
     */
    @EventSourcingHandler
    public void on(OrderPartialFilledCancellingEvt evt) {
        log.debug("委托已部分撤销，开始处理部分撤销事件: {}", evt);

        getOrderAggrState().setOrderState(new PFCancellingOrderState());
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
        getOrderAggrState().setOrderState(new PlacedOrderState());
    }

    /**
     * 委托完成事件
     *
     * @param evt
     */
    @EventSourcingHandler
    public void on(OrderCompletedEvt evt) {
        log.debug("委托全部成交完成，开始处理全部成交完成事件: {}", evt);

        getOrderAggrState().setOrderState(new CompletedOrderState());
    }

    /**
     * 委托匹配事件
     *
     * @param evt
     */
    @EventSourcingHandler
    public void on(OrderMatchSuccessEvt evt) {
        log.debug("委托匹配，开始处理匹配事件: {}", evt);
        //匹配时增加指令id和组合信息
        setSkInstr(evt.getInstrSkId());
        setSkCombi(evt.getSkCombi());
        setOrderAggrState(new ManualMatchedOrderStateImpl(getOrderAggrState().getOrderState()));
    }

    /**
     * 委托自动匹配事件
     *
     * @param evt
     */
    @EventSourcingHandler
    public void on(OrderAutoMatchedEvt evt) {
        log.debug("委托匹配，开始处理匹配事件: {}", evt);
        //匹配时增加指令id和组合信息
        setSkInstr(evt.getSkInstr());
        setSkCombi(evt.getSkCombi());
        setSkTradeUserId(evt.getUserId());
        setOrderAggrState(new AutoMatchedOrderStateImpl(getOrderAggrState().getOrderState()));
    }

    /**
     * 委托撤销匹配事件
     *
     * @param evt
     */
    @EventSourcingHandler
    public void on(OrderCancelMatchSuccessEvt evt) {
        log.debug("委托撤销匹配，开始处理撤销匹配事件: {}", evt);

        //匹配时删除匹配的指令id和组合信息
        setSkInstr(null);
        setSkCombi(null);
        setOrderAggrState(new UnmatchOrderStateImpl(getOrderAggrState().getOrderState()));
    }


}
