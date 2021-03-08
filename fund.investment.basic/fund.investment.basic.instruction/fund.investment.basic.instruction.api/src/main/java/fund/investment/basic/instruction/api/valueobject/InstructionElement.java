package fund.investment.basic.instruction.api.valueobject;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author dongkw
 * @Date 2021/2/26、3:35 下午
 **/
@Data
public class InstructionElement {

    private Long instructionId;

    private String unitId;

    private String inquiryResultId;

    /**
     * 当前日期,当前系统工作日
     */
    private Date dtCurrentDate;

    /**
     * 交易日期
     */
    private Date dtTradeDate;

    /**
     * 产品代理键
     */
    private String skProduct;

    /**
     * 资产单元代理键
     */
    private String skAsset;

    /**
     * 投资组合代理键
     */
    private String skCombi;

    /**
     * 指令状态,数据字典7041: 1、有效 2、已修改 3、已撤销 4、已暂停 5、审批拒绝 6、分发拒绝 7、指令录入 8、分仓失败 9、草稿指令 10、临时下达 11、临时修改 12 不参与
     */
    private String chInstrStatus;

    /**
     * 指令下达日期,YYYY-MM-DD
     */
    private Date dtDirectDate;

    /**
     * 指令下达时间,HH:MM:SS
     */
    private String chDirectTime;

    /**
     * 指令最后修改时间,YYYY-MM-DD HH:MM:SS
     */
    private Date tsModifyDate;

    /**
     * 指令有效开始日期,YYYY-MM-DD
     */
    private Date dtBeginDate;

    /**
     * 指令有效开始时间,HH:MM:SS
     */
    private String chBeginTime;

    /**
     * 指令有效结束日期,YYYY-MM-DD
     */
    private Date dtInstrEndDate;

    /**
     * 指令有效结束时间,HH:MM:SS
     */
    private String chEndTime;

    /**
     * 预设指令序号,用于和预置指令表关联，对于非预置指令，填0
     */
    private String chPreInstrDesc;

    /**
     * 预置指令修改次序,用于和预置指令表关联，指令修改，只影响指令修改次序，不影响预置指令修改次序。
     */
    private String chPreIndexModify;

    /**
     * 执行市场代理键,关联执行市场表
     */
    private String skMarktExe;

    /**
     * 委托方向代理键,交易类型代理键
     */
    private String skTradeType;

    /**
     * 证券代理键
     */
    private String skSecurity;

    /**
     * 投资分类,数据字典：0012
     */
    private String chInvestType;

    /**
     * 指令数量
     */
    private BigDecimal ftInstrQtty;

    /**
     * 指令金额
     */
    private BigDecimal ftInstrAmt;

    /**
     * 指令价格
     */
    private BigDecimal ftInstrPrice;

    /**
     * 清算速度,数据字典7042： 0：T+0，1：T+1
     */
    private String chSettleDays;

    /**
     * 交易员ID
     */
    private String chTraderUserId;

    /**
     * 交易对手代理键,从机构表中获取交易对手方信息，并且后续会判断是否交易对手方入池；用于银行间业务和非标业务
     */
    private String skInst;

    /**
     * 对手方交易员ID,交易对手交易员ID
     */
    private String chTraderId;

    /**
     * 备注
     */
    private String chMemo;

}
