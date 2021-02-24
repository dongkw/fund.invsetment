package fund.investment.basic.common.valueobject;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 描述:TRADE_BANK_PLEDGE_BOND表的实体类
 */
@Data
public class InterbankPledgeBond {

    /**
     * 质押债券表代理键
     */
    private String skPledge;

    /**
     * 关联表代理键
     */
    private String skHgInstr;

    /**
     * 关联表名
     */
    private String skRelationTname;

    /**
     * 交易日期
     */
    private Date dtTradeDate;

    /**
     * 证券代理键
     */
    private String skSecurity;

    /**
     * 质押券类别
     */
    private String chMortagageType;

    /**
     * 票面金额
     */
    private BigDecimal ftPledgeParvalue;

    /**
     * 折算比例
     */
    private BigDecimal ftDiscountRatio;

    /**
     * 折算面额
     */
    private BigDecimal ftDiscountParvalue;

    /**
     * 折算数量
     */
    private BigDecimal ftDiscountQtty;

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

}