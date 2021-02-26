package fund.investment.basic.trade.api.event;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class OrderModifiedEvt extends OrderEvent {

    private String userId;

    private String skInstr;

    /**
     * 原委托id（修改用）
     */
    private String skOriginId;

    /**
     * 唯一主键:uuid
     */
    private String skId;

    /**
     * 当前日期:当前系统工作日
     */
    private Date dtCurrentDate;

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
     * 指令来源1:增加数据字典7043：1、系统指令 2、总行指令 3、O32指令 4、方达指令
     */
    private String chInstrSource;

    /**
     * 指令来源主键ID:存对应系统的唯一指令的主键
     */
    private String chSourceKey;

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
     * 修改人
     */
    private String chLastModifiedId;

    /**
     * 修改时间
     */
    private Date tsLastModifiedTime;

}
