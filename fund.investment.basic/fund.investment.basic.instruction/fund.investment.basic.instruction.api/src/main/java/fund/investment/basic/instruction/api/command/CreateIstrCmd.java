package fund.investment.basic.instruction.api.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateIstrCmd extends InstructionCommand {


    private String unitId;

    private String inquiryResultId;

    /**
     * 指令编号,从SeqDailyInstructionNo中取值。SeqDailyInstructionNo每天从1开始 同一个预置指令，每天触发生成指令的指令序号是相同。触发预置指令时，生成指令表数据，指令表的预置指令序号、预置指令修改序号、指令序号保留预置指令表的值，指令修改序号从1开始。 在生成指令时，如果从seq中取出的指令序号已经在预置指令表存在，则继续取下个seq的值，直到取出的数值不在预置指令表中。
     */
    private String chInstrNo;

    /**
     * 指令修改次序,指令新下达时，该字段的值为1，当指令有修改时，该字段的值依次递增。
     */
    private String chInstrModify;

    /**
     * 对于O32的数据，指令证券表的指令证券序号（L_STOCK_SERIAL_NO）是主键，需要同步，对于资管平台，指令证券序号从1开始，该字段的值依次递增。
     */
    private String chStockNo;

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
     * 指令来源1,增加数据字典7043：1、系统指令 2、总行指令 3、O32指令 4、方达指令
     */
    private String chInstrSource;

    /**
     * 指令来源主键ID,存对应系统的唯一指令的主键(这里保存方达指令)
     */
    private String chSourceKey;

    /**
     * 指令来源编号1,存对应系统的指令序号(这里保存方达指令)
     */
    private String chSourceNo;

    /**
     * 指令来源2,增加数据字典7043：1、系统指令 2、总行指令 3、O32指令 4、方达指令
     */
    private String chInstrSourceTwo;

    /**
     * 指令来源主键ID2,存对应系统的唯一指令的主键(这里保存O32指令)
     */
    private String chSourceKeyTwo;

    /**
     * 指令来源编号2,存对应系统的指令序号(这里保存O32指令)
     */
    private String chSourceNoTwo;

    /**
     * 时间戳,缺省值为 数据库机器时间 sysdate
     */
    private Date tsTimestamp;

    /**
     * 备注
     */
    private String chMemo;

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
     * 是否锁定，数据字典：7022
     */
    private String chIsLock;

    /**
     * 指令下达来源
     */
    private String chInstrIssuedSource;


    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getInquiryResultId() {
        return inquiryResultId;
    }

    public void setInquiryResultId(String inquiryResultId) {
        this.inquiryResultId = inquiryResultId;
    }

    public String getcInstrNo() {
        return chInstrNo;
    }

    public void setcInstrNo(String cInstrNo) {
        this.chInstrNo = cInstrNo;
    }

    public String getcInstrModify() {
        return chInstrModify;
    }

    public void setcInstrModify(String cInstrModify) {
        this.chInstrModify = cInstrModify;
    }

    public String getcStockNo() {
        return chStockNo;
    }

    public void setcStockNo(String cStockNo) {
        this.chStockNo = cStockNo;
    }

    public Date getdCurrentDate() {
        return dtCurrentDate;
    }

    public void setdCurrentDate(Date dCurrentDate) {
        this.dtCurrentDate = dCurrentDate;
    }

    public Date getdTradeDate() {
        return dtTradeDate;
    }

    public void setdTradeDate(Date dTradeDate) {
        this.dtTradeDate = dTradeDate;
    }

    public String getSkProduct() {
        return skProduct;
    }

    public void setSkProduct(String skProduct) {
        this.skProduct = skProduct;
    }

    public String getSkAsset() {
        return skAsset;
    }

    public void setSkAsset(String skAsset) {
        this.skAsset = skAsset;
    }

    public String getSkCombi() {
        return skCombi;
    }

    public void setSkCombi(String skCombi) {
        this.skCombi = skCombi;
    }

    public String getcInstrStatus() {
        return chInstrStatus;
    }

    public void setcInstrStatus(String cInstrStatus) {
        this.chInstrStatus = cInstrStatus;
    }

    public Date getdDirectDate() {
        return dtDirectDate;
    }

    public void setdDirectDate(Date dDirectDate) {
        this.dtDirectDate = dDirectDate;
    }

    public String getcDirectTime() {
        return chDirectTime;
    }

    public void setcDirectTime(String cDirectTime) {
        this.chDirectTime = cDirectTime;
    }

    public Date gettModifyDate() {
        return tsModifyDate;
    }

    public void settModifyDate(Date tModifyDate) {
        this.tsModifyDate = tModifyDate;
    }

    public Date getdBeginDate() {
        return dtBeginDate;
    }

    public void setdBeginDate(Date dBeginDate) {
        this.dtBeginDate = dBeginDate;
    }

    public String getcBeginTime() {
        return chBeginTime;
    }

    public void setcBeginTime(String cBeginTime) {
        this.chBeginTime = cBeginTime;
    }

    public Date getdInstrEndDate() {
        return dtInstrEndDate;
    }

    public void setdInstrEndDate(Date dInstrEndDate) {
        this.dtInstrEndDate = dInstrEndDate;
    }

    public String getcEndTime() {
        return chEndTime;
    }

    public void setcEndTime(String cEndTime) {
        this.chEndTime = cEndTime;
    }

    public String getcPreInstrDesc() {
        return chPreInstrDesc;
    }

    public void setcPreInstrDesc(String cPreInstrDesc) {
        this.chPreInstrDesc = cPreInstrDesc;
    }

    public String getcPreIndexModify() {
        return chPreIndexModify;
    }

    public void setcPreIndexModify(String cPreIndexModify) {
        this.chPreIndexModify = cPreIndexModify;
    }

    public String getSkMarktExe() {
        return skMarktExe;
    }

    public void setSkMarktExe(String skMarktExe) {
        this.skMarktExe = skMarktExe;
    }

    public String getSkTradeType() {
        return skTradeType;
    }

    public void setSkTradeType(String skTradeType) {
        this.skTradeType = skTradeType;
    }

    public String getSkSecurity() {
        return skSecurity;
    }

    public void setSkSecurity(String skSecurity) {
        this.skSecurity = skSecurity;
    }

    public String getcInvestType() {
        return chInvestType;
    }

    public void setcInvestType(String cInvestType) {
        this.chInvestType = cInvestType;
    }

    public BigDecimal getfInstrQtty() {
        return ftInstrQtty;
    }

    public void setfInstrQtty(BigDecimal fInstrQtty) {
        this.ftInstrQtty = fInstrQtty;
    }

    public BigDecimal getfInstrAmt() {
        return ftInstrAmt;
    }

    public void setfInstrAmt(BigDecimal fInstrAmt) {
        this.ftInstrAmt = fInstrAmt;
    }

    public BigDecimal getfInstrPrice() {
        return ftInstrPrice;
    }

    public void setfInstrPrice(BigDecimal fInstrPrice) {
        this.ftInstrPrice = fInstrPrice;
    }

    public String getcSettleDays() {
        return chSettleDays;
    }

    public void setcSettleDays(String cSettleDays) {
        this.chSettleDays = cSettleDays;
    }

    public String getcTraderUserId() {
        return chTraderUserId;
    }

    public void setcTraderUserId(String cTraderUserId) {
        this.chTraderUserId = cTraderUserId;
    }

    public String getSkInst() {
        return skInst;
    }

    public void setSkInst(String skInst) {
        this.skInst = skInst;
    }

    public String getcTraderId() {
        return chTraderId;
    }

    public void setcTraderId(String cTraderId) {
        this.chTraderId = cTraderId;
    }

    public String getcInstrSource() {
        return chInstrSource;
    }

    public void setcInstrSource(String cInstrSource) {
        this.chInstrSource = cInstrSource;
    }

    public String getcSourceKey() {
        return chSourceKey;
    }

    public void setcSourceKey(String cSourceKey) {
        this.chSourceKey = cSourceKey;
    }

    public String getcSourceNo() {
        return chSourceNo;
    }

    public void setcSourceNo(String cSourceNo) {
        this.chSourceNo = cSourceNo;
    }

    public String getcInstrSourceTwo() {
        return chInstrSourceTwo;
    }

    public void setcInstrSourceTwo(String cInstrSourceTwo) {
        this.chInstrSourceTwo = cInstrSourceTwo;
    }

    public String getcSourceKeyTwo() {
        return chSourceKeyTwo;
    }

    public void setcSourceKeyTwo(String cSourceKeyTwo) {
        this.chSourceKeyTwo = cSourceKeyTwo;
    }

    public String getcSourceNoTwo() {
        return chSourceNoTwo;
    }

    public void setcSourceNoTwo(String cSourceNoTwo) {
        this.chSourceNoTwo = cSourceNoTwo;
    }

    public Date gettTimestamp() {
        return tsTimestamp;
    }

    public void settTimestamp(Date tTimestamp) {
        this.tsTimestamp = tTimestamp;
    }

    public String getcMemo() {
        return chMemo;
    }

    public void setcMemo(String cMemo) {
        this.chMemo = cMemo;
    }

    public String getcDelete() {
        return chDelete;
    }

    public void setcDelete(String cDelete) {
        this.chDelete = cDelete;
    }

    public String getcCreateId() {
        return chCreateId;
    }

    public void setcCreateId(String cCreateId) {
        this.chCreateId = cCreateId;
    }

    public Date gettCreateTime() {
        return tsCreateTime;
    }

    public void settCreateTime(Date tCreateTime) {
        this.tsCreateTime = tCreateTime;
    }

    public String getcDataSource() {
        return chDataSource;
    }

    public void setcDataSource(String cDataSource) {
        this.chDataSource = cDataSource;
    }

    public String getcAuditorId() {
        return chAuditorId;
    }

    public void setcAuditorId(String cAuditorId) {
        this.chAuditorId = cAuditorId;
    }

    public String getcAuditStatus() {
        return chAuditStatus;
    }

    public void setcAuditStatus(String cAuditStatus) {
        this.chAuditStatus = cAuditStatus;
    }

    public Date gettAuditTime() {
        return tsAuditTime;
    }

    public void settAuditTime(Date tAuditTime) {
        this.tsAuditTime = tAuditTime;
    }

    public String getcIsLock() {
        return chIsLock;
    }

    public void setcIsLock(String cIsLock) {
        this.chIsLock = cIsLock;
    }

    public String getcInstrIssuedSource() {
        return chInstrIssuedSource;
    }

    public void setcInstrIssuedSource(String cInstrIssuedSource) {
        this.chInstrIssuedSource = cInstrIssuedSource;
    }
}
