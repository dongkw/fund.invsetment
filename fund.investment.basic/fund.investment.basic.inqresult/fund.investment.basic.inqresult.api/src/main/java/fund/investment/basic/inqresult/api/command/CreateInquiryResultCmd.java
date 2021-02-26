package fund.investment.basic.inqresult.api.command;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CreateInquiryResultCmd extends InquiryResultCommand {

    /**
     * 交易日期
     */
    @ApiModelProperty(hidden = true)
    private Date dtTradeDate;

    /**
     * 产品代理键
     */
    @ApiModelProperty(value = "产品代理键", example = "", notes = "下达使用", required = true)
    private String skProduct;

    /**
     * 资产单元代理键
     */
    @ApiModelProperty(value = "资产单元代理键", example = "", notes = "下达使用", required = true)
    private String skAsset;

    /**
     * 投资组合代理键
     */
    @ApiModelProperty(value = "投资组合代理键", example = "", notes = "下达使用", required = true)
    private String skCombi;

    /**
     * 投资分类:数据字典：0012，默认交易性
     */
    @ApiModelProperty(hidden = true)
    private String chInvestType;

    /**
     * 是否预控额度:数据字典：7022
     */
    @ApiModelProperty(hidden = true)
    private String chIsPreLimit;

    /**
     * 证券代理键
     */
    @ApiModelProperty(hidden = true)
    private String skSecurity;

    /**
     * 交易类型代理键:交易类型表：base_trade_type
     */
    @ApiModelProperty(value = "交易类型代理键", example = "", notes = "下达使用", required = true)
    private String skTradeType;

    /**
     * 询价价格:存交易价格或者利率
     */
    @ApiModelProperty(value = "询价价格", example = "", notes = "下达/修改使用", required = true)
    private BigDecimal ftInquirePrice;

    /**
     * 询价数量
     */
    @ApiModelProperty(hidden = true)
    private BigDecimal ftInquireQtty;

    /**
     * 询价金额
     */
    @ApiModelProperty(value = "询价金额（分销中签金额）", example = "", notes = "下达/修改使用", required = true)
    private BigDecimal ftInquireAmt;

    /**
     * 清算速度:数据字典7042：0:T+0，1：T+1
     */
    @ApiModelProperty(value = "清算速度", example = "", notes = "下达/修改使用，存款不传", required = true)
    private String chSettleDays;

    /**
     * 期限单位:数据字典2103：1-天；2-周；3-月；4-季；5-年
     */
    @ApiModelProperty(value = "期限单位", notes = "存款业务需要")
    private String chTermUnit;

    /**
     * 回购天数/存款期限:用于回购和存款业务，其他业务填空或0
     */
    @ApiModelProperty(value = "回购天数/存款期限", example = "", notes = "下达/修改使用", required = true)
    private Long ftInquireDays;

    /**
     * 实际占款天数:用于银行间回购和债券借贷业务
     */
    @ApiModelProperty(value = "实际占款天数", example = "", notes = "下达/修改使用", required = false)
    private Long ftUseDays;

    /**
     * 发行日期:用于存单业务
     */
    @ApiModelProperty(hidden = true)
    private Date dtIssuendDate;

    /**
     * 起息日期:用于存款业务
     */
    @ApiModelProperty(value = "起息日期", notes = "存款业务必传")
    private Date dtValueDate;

    /**
     * 到期日期:用于存款业务
     */
    @ApiModelProperty(value = "到期日期", notes = "存款业务必传")
    private Date dtMatureDate;

    /**
     * 支取日期:用于存款业务
     */
    @ApiModelProperty(value = "支取日期", notes = "用于存款业务")
    private Date dtDrawDate;

    /**
     * 缴款日期:用于存款业务
     */
    @ApiModelProperty(value = "缴款日期", notes = "用于存款业务")
    private Date dtPaymentDate;

    /**
     * 到期类型:用于存款业务，数据字典：7178
     */
    @ApiModelProperty(value = "到期类型", notes = "用于存款业务")
    private String chEndType;

    /**
     * 计息方式:用于存款业务，数据字典：0019
     */
    @ApiModelProperty(value = "计息方式", notes = "用于存款业务")
    private String chIntModeCode;

    /**
     * 付息频率:用于存款业务，数据字典：7179
     */
    @ApiModelProperty(value = "付息频率", notes = "用于存款业务")
    private String chAccrualFreq;

    /**
     * 提前支取限制:用于存款业务，数据字典：7180
     */
    @ApiModelProperty(value = "提前支取限制", notes = "用于存款业务")
    private String chAdvanceLimitFlag;

    /**
     * 首期结算方式:数据字典7050：对银行间有效，券款交付；数据字典：1、券款对付 2、见券付款 3、见款付券 4：纯券过户
     */
    @ApiModelProperty(hidden = true)
    private String chFirstSettleType;

    /**
     * 到期结算方式:数据字典7050：对银行间有效，券款交付；数据字典：1、券款对付 2、见券付款 3、见款付券 4：纯券过户
     */
    @ApiModelProperty(hidden = true)
    private String chSecondSettleType;

    /**
     * 首期结算日期
     */
    @ApiModelProperty(hidden = true)
    private Date dtFirstSettleDate;

    /**
     * 到期结算日期
     */
    @ApiModelProperty(hidden = true)
    private Date dtSecondSettleDate;

    /**
     * 询价结果状态:数据字典7186：1：有效 2：无效 3：草稿 4：已回溯
     */
    @ApiModelProperty(hidden = true)
    private String chInquireStatus;

    /**
     * 指令来源1:增加数据字典7043：1、系统指令 2、总行指令 3、O32指令 4、方达指令
     */
    @ApiModelProperty(hidden = true)
    private String chInstrSource;

    /**
     * 指令来源主键ID:存对应系统的唯一指令的主键
     */
    @ApiModelProperty(hidden = true)
    private String chSourceKey;

    /**
     * 指令来源编号1:存对应系统的指令序号
     */
    @ApiModelProperty(hidden = true)
    private String chSourceNo;

    /**
     * 指令来源2:增加数据字典7043：1、系统指令 2、总行指令 3、O32指令 4、方达指令
     */
    @ApiModelProperty(hidden = true)
    private String chInstrSourceTwo;

    /**
     * 指令来源主键ID2:存对应系统的唯一指令的主键
     */
    @ApiModelProperty(hidden = true)
    private String chSourceKeyTwo;

    /**
     * 指令来源编号2:存对应系统的指令序号
     */
    @ApiModelProperty(hidden = true)
    private String chSourceNoTwo;

    /**
     * 正式指令下达日期:网下申购询价用到，根据询价下达指令后，回填该正式指令的下达日期
     */
    @ApiModelProperty(hidden = true)
    private Date dtDirectDate;

    /**
     * 指令表主键:投资指令表中的唯一主键SK_ID
     */
    @ApiModelProperty(hidden = true)
    private String skInstr;

    /**
     * 申报席位
     */
    @ApiModelProperty(hidden = true)
    private String chReportSeat;

    /**
     * 对手方交易员ID:交易对手交易员ID
     */
    @ApiModelProperty(value = "对手方交易员ID", example = "", notes = "下达/修改使用", required = false)
    private String chTraderId;

    /**
     * 交易对手代理键:对于存款存单业务，对应的是银行总行，该字段都是从机构信息表关联查询
     */
    @ApiModelProperty(value = "交易对手代理键", example = "", notes = "下达/修改使用", required = true)
    private String skInst;

    /**
     * 存款分行:用于存款业务
     */
    @ApiModelProperty(value = "存款分行:用于存款业务", notes = "对应存款支行")
    private String skInstBranch;

    /**
     * 首期净价价格:交易所和成交价格一样；银行间债券和买断式回购为首次交易净价价格，质押式回购为回购利率；场外债券分销和基金为价格，存款为取在途存款表的存款利率
     */
    @ApiModelProperty(hidden = true)
    private BigDecimal ftFirstNetPrice;

    /**
     * 首期全价价格:全价价格，银行间债券和买断式回购为首次交易全价价格
     */
    @ApiModelProperty(hidden = true)
    private BigDecimal ftFirstFullPrice;

    /**
     * 首期结算金额
     */
    @ApiModelProperty(hidden = true)
    private BigDecimal ftFirstSettleAmt;

    /**
     * 首期结算收益率:用于银行间买断式回购业务
     */
    @ApiModelProperty(hidden = true)
    private BigDecimal ftFirstProfitRatio;

    /**
     * 首期应计利息:债券业务和买断式回购存债券百元利率，债券借贷业务存利息总额
     */
    @ApiModelProperty(hidden = true)
    private BigDecimal ftFirstInterest;

    /**
     * 到期净价价格:用于银行间买断式回购业务
     */
    @ApiModelProperty(hidden = true)
    private BigDecimal ftSecondNetPrice;

    /**
     * 到期全价价格:用于银行间买断式回购业务 = 到期净价价格 + 到期应计利息
     */
    @ApiModelProperty(hidden = true)
    private BigDecimal ftSecondFullPrice;

    /**
     * 到期结算金额:用于银行间回购
     */
    @ApiModelProperty(hidden = true)
    private BigDecimal ftSecondSettleAmt;

    /**
     * 到期结算收益率:用于银行间买断式回购
     */
    @ApiModelProperty(hidden = true)
    private BigDecimal ftSecondProfitRatio;

    /**
     * 到期应计利息:用于银行间买断式回购业务
     */
    @ApiModelProperty(hidden = true)
    private BigDecimal ftSecondInterest;

    /**
     * 价格控制类型:数据字典7181：1-按价格控；2-按收益率控
     */
    @ApiModelProperty(hidden = true)
    private String chPriceControlType;

    /**
     * 备注
     */
    @ApiModelProperty(hidden = true)
    private String chMemo;

    /**
     * 是否删除
     */
    @ApiModelProperty(hidden = true)
    private String chDelete;

    /**
     * 创建人
     */
    @ApiModelProperty(hidden = true)
    private String chCreateId;

    /**
     * 创建时间
     */
    @ApiModelProperty(hidden = true)
    private Date tsCreateTime;

    /**
     * 数据来源
     */
    @ApiModelProperty(hidden = true)
    private String chDataSource;

    /**
     * 审核人
     */
    @ApiModelProperty(hidden = true)
    private String chAuditorId;

    /**
     * 审核状态
     */
    @ApiModelProperty(hidden = true)
    private String chAuditStatus;

    /**
     * 审核时间
     */
    @ApiModelProperty(hidden = true)
    private Date tsAuditTime;

    /**
     * 证券代码
     */
    @ApiModelProperty(hidden = true)
    private String chSecuCode;

    /**
     * 证券名称
     */
    @ApiModelProperty(hidden = true)
    private String chSecuName;

    /**
     * 发行人代理键
     */
    @ApiModelProperty(hidden = true)
    private String skIssuer;

    /**
     * 交易时间
     */
    @ApiModelProperty(hidden = true)
    private String chTradeTime;

    /**
     * 提前支取日期
     */
    @ApiModelProperty(value = "提前支取日期", notes = "用于存款业务,对应界面上的提前支取时间")
    private Date chAdvanceLimitDate;
    /**
     * 数据来源
     */
    @ApiModelProperty(value = "指令来源", notes = "指令来源", required = false)
    private String chInstrIssuedSource;


}
