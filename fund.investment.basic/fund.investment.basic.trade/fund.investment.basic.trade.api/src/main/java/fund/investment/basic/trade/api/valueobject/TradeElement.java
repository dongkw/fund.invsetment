package fund.investment.basic.trade.api.valueobject;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author dongkw
 * @Date 2021/3/3、9:11 上午
 **/
@Data
public class TradeElement {


    private String tradeId;

    private String instructionId;

    private String skAsset;

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


}
