//package fund.investment.app.pledge.repo.server.utils.datarec;
//
//
//import fund.investment.app.pledge.repo.server.aggregate.instruction.PledgeRepoIstrAggr;
//import fund.investment.basic.common.enums.dict.instruction.InsStatusEnums;
//import fund.investment.basic.common.valueobject.InterbankPledgeBond;
//import fund.investment.basic.instruction.server.aggregate.InstructionAggregate;
//
//import java.math.BigDecimal;
//import java.util.Date;
//import java.util.List;
//
//public class PledgeInvestDataRecAggrDto {
//
//    public static <T extends CommonInstrBean, D extends CommonInstrBean, V extends InstructionAggregate> void compareInvestDataAndAggregate(T cmd,
//                                                                                                                                            V istrAggr,
//                                                                                                                                            InvestDataHolder<D> dataHolder) {
//
//        if (StringUtils.isNotEmpty(cmd.getChTraderUserId())
//                && !StringUtils.equals(cmd.getChTraderUserId(), istrAggr.getChTraderUserId())) {
//            getInstrEvt(dataHolder).setChTraderUserId(cmd.getChTraderUserId());
//        }
//        if (PledgeInvestDataRecAggrDto.checkStatusModify(cmd, istrAggr)) {
//            getInstrEvt(dataHolder).setChInstrStatus(cmd.getChInstrStatus());
//        }
//        if (StringUtils.isNotEmpty(cmd.getChInstrSourceTwo())
//                && !StringUtils.equals(cmd.getChInstrSourceTwo(), istrAggr.getChInstrSourceTwo())) {
//            getInstrEvt(dataHolder).setChInstrSourceTwo(cmd.getChInstrSourceTwo());
//        }
//        if (StringUtils.isNotEmpty(cmd.getChSourceKeyTwo())
//                && !StringUtils.equals(cmd.getChSourceKeyTwo(), istrAggr.getChSourceKeyTwo())) {
//            getInstrEvt(dataHolder).setChSourceKeyTwo(cmd.getChSourceKeyTwo());
//        }
//    }
//
//    private static <D extends CommonInstrBean> D getInstrEvt(InvestDataHolder<D> dataHolder) {
//        return dataHolder.getOrInitOrderEvt();
//    }
//
//    private static <T extends CommonInstrBean, V extends InstructionAggregate> boolean checkStatusModify(T investRequest, V tradeInvest) {
//        if (StringUtils.isNotEmpty(investRequest.getChInstrStatus())
//                && !StringUtils.equals(investRequest.getChInstrStatus(), tradeInvest.getChInstrStatus())) {
//            //判断指令是否退回
//            if (StringUtils.equals(InsStatusEnums.NEW.getSkId(), investRequest.getChInstrStatus())
//                    && StringUtils.equals(InsStatusEnums.BACK.getSkId(), tradeInvest.getChInstrStatus())) {
//                return false;
//            }
//            return true;
//        }
//        return true;
//    }
//
//
//    private final PledgeRepoIstrAggr repoIstrAggr;
//
//    public PledgeInvestDataRecAggrDto(PledgeRepoIstrAggr repoIstrAggr) {
//        this.repoIstrAggr = repoIstrAggr;
//    }
//
//    @Override
//    public List<InterbankPledgeBond> getPledgeBondList() {
//        return repoIstrAggr.getPledgeBondList();
//    }
//
//    @Override
//    public void setPledgeBondList(List<InterbankPledgeBond> pledgeBondList) {
//        repoIstrAggr.setPledgeBondList(pledgeBondList);
//    }
//
//    @Override
//    public String getUserId() {
//        //无需此字段
//        return null;
//    }
//
//    @Override
//    public void setUserId(String userId) {
//        //无需此字段
//    }
//
//    @Override
//    public String getSkId() {
//        return repoIstrAggr.getSkId();
//    }
//
//    @Override
//    public void setSkId(String skId) {
//        repoIstrAggr.setSkId(skId);
//    }
//
//    @Override
//    public String getSkInstr() {
//        return repoIstrAggr.getSkInstr();
//    }
//
//    @Override
//    public void setSkInstr(String skInstr) {
//        repoIstrAggr.setSkInstr(skInstr);
//    }
//
//    @Override
//    public String getInquiryResultId() {
//        return repoIstrAggr.getInquiryResultId();
//    }
//
//    @Override
//    public void setInquiryResultId(String inquiryResultId) {
//        repoIstrAggr.setInquiryResultId(inquiryResultId);
//    }
//
//    @Override
//    public String getChInstrNo() {
//        return repoIstrAggr.getChInstrNo();
//    }
//
//    @Override
//    public void setChInstrNo(String cInstrNo) {
//        repoIstrAggr.setChInstrNo(cInstrNo);
//    }
//
//    @Override
//    public String getChInstrModify() {
//        return repoIstrAggr.getChInstrModify();
//    }
//
//    @Override
//    public void setChInstrModify(String cInstrModify) {
//        repoIstrAggr.setChInstrModify(cInstrModify);
//    }
//
//    @Override
//    public String getChStockNo() {
//        return repoIstrAggr.getChStockNo();
//    }
//
//    @Override
//    public void setChStockNo(String cStockNo) {
//        repoIstrAggr.setChStockNo(cStockNo);
//    }
//
//    @Override
//    public Date getDtCurrentDate() {
//        return repoIstrAggr.getDtCurrentDate();
//    }
//
//    @Override
//    public void setDtCurrentDate(Date dtCurrentDate) {
//        repoIstrAggr.setDtCurrentDate(dtCurrentDate);
//    }
//
//    @Override
//    public Date getDtTradeDate() {
//        return repoIstrAggr.getDtTradeDate();
//    }
//
//    @Override
//    public void setDtTradeDate(Date dTradeDate) {
//        repoIstrAggr.setDtTradeDate(dTradeDate);
//    }
//
//    @Override
//    public String getSkProduct() {
//        return repoIstrAggr.getSkProduct();
//    }
//
//    @Override
//    public void setSkProduct(String skProduct) {
//        repoIstrAggr.setSkProduct(skProduct);
//    }
//
//    @Override
//    public String getSkAsset() {
//        return repoIstrAggr.getSkAsset();
//    }
//
//    @Override
//    public void setSkAsset(String skAsset) {
//        repoIstrAggr.setSkAsset(skAsset);
//    }
//
//    @Override
//    public String getSkCombi() {
//        return repoIstrAggr.getSkCombi();
//    }
//
//    @Override
//    public void setSkCombi(String skCombi) {
//        repoIstrAggr.setSkCombi(skCombi);
//    }
//
//    @Override
//    public String getChInstrStatus() {
//        return repoIstrAggr.getChInstrStatus();
//    }
//
//    @Override
//    public void setChInstrStatus(String cInstrStatus) {
//        repoIstrAggr.setChInstrStatus(cInstrStatus);
//    }
//
//    @Override
//    public Date getDtDirectDate() {
//        return repoIstrAggr.getDtDirectDate();
//    }
//
//    @Override
//    public void setDtDirectDate(Date dDirectDate) {
//        repoIstrAggr.setDtDirectDate(dDirectDate);
//    }
//
//    @Override
//    public String getChDirectTime() {
//        return repoIstrAggr.getChDirectTime();
//    }
//
//    @Override
//    public void setChDirectTime(String cDirectTime) {
//        repoIstrAggr.setChDirectTime(cDirectTime);
//    }
//
//    @Override
//    public Date getTsModifyDate() {
//        return repoIstrAggr.getTsModifyDate();
//    }
//
//    @Override
//    public void setTsModifyDate(Date tModifyDate) {
//        repoIstrAggr.setTsModifyDate(tModifyDate);
//    }
//
//    @Override
//    public String getChInvestType() {
//        return repoIstrAggr.getChInvestType();
//    }
//
//    @Override
//    public void setChInvestType(String chInvestType) {
//        repoIstrAggr.setChInvestType(chInvestType);
//    }
//
//    @Override
//    public BigDecimal getFtInstrQtty() {
//        return repoIstrAggr.getFtInstrQtty();
//    }
//
//    @Override
//    public void setFtInstrQtty(BigDecimal fInstrQtty) {
//        repoIstrAggr.setFtInstrQtty(fInstrQtty);
//    }
//
//    @Override
//    public BigDecimal getFtInstrAmt() {
//        return repoIstrAggr.getFtInstrAmt();
//    }
//
//    @Override
//    public void setFtInstrAmt(BigDecimal fInstrAmt) {
//        repoIstrAggr.setFtInstrAmt(fInstrAmt);
//    }
//
//    @Override
//    public BigDecimal getFtInstrPrice() {
//        return repoIstrAggr.getFtInstrPrice();
//    }
//
//    @Override
//    public void setFtInstrPrice(BigDecimal fInstrPrice) {
//        repoIstrAggr.setFtInstrPrice(fInstrPrice);
//    }
//
//
//    @Override
//    public String getSkSecurity() {
//        return repoIstrAggr.getSkSecurity();
//    }
//
//    @Override
//    public void setSkSecurity(String skSecurity) {
//        repoIstrAggr.setSkSecurity(skSecurity);
//    }
//
//
//    @Override
//    public String getSkMarktExe() {
//        return repoIstrAggr.getSkMarktExe();
//    }
//
//    @Override
//    public void setSkMarktExe(String skMarktExe) {
//        repoIstrAggr.setSkMarktExe(skMarktExe);
//    }
//
//
//    @Override
//    public String getSkTradeType() {
//        return repoIstrAggr.getSkTradeType();
//    }
//
//    @Override
//    public void setSkTradeType(String skTradeType) {
//        repoIstrAggr.setSkTradeType(skTradeType);
//    }
//
//
//    @Override
//    public String getChSettleDays() {
//        return repoIstrAggr.getChSettleDays();
//    }
//
//    @Override
//    public void setChSettleDays(String chSettleDays) {
//        repoIstrAggr.setChSettleDays(chSettleDays);
//    }
//
//    @Override
//    public String getChTraderUserId() {
//        return repoIstrAggr.getChTraderUserId();
//    }
//
//    @Override
//    public void setChTraderUserId(String chTraderUserId) {
//        repoIstrAggr.setChTraderUserId(chTraderUserId);
//    }
//
//    @Override
//    public String getSkHgInstr() {
//        return repoIstrAggr.getSkHgInstr();
//    }
//
//    @Override
//    public void setSkHgInstr(String skHgInstr) {
//        repoIstrAggr.setSkHgInstr(skHgInstr);
//    }
//
//    @Override
//    public Long getFtRepDays() {
//        return repoIstrAggr.getFtRepDays();
//    }
//
//    @Override
//    public void setFtRepDays(Long ftRepDays) {
//        repoIstrAggr.setFtRepDays(ftRepDays);
//    }
//
//    @Override
//    public Date getDtFirstSettleDate() {
//        return repoIstrAggr.getDtFirstSettleDate();
//    }
//
//    @Override
//    public void setDtFirstSettleDate(Date dFirstSettleDate) {
//        repoIstrAggr.setDtFirstSettleDate(dFirstSettleDate);
//    }
//
//    @Override
//    public Date getDtSecondSettleDate() {
//        return repoIstrAggr.getDtSecondSettleDate();
//    }
//
//    @Override
//    public void setDtSecondSettleDate(Date dSecondSettleDate) {
//        repoIstrAggr.setDtSecondSettleDate(dSecondSettleDate);
//    }
//
//    @Override
//    public String getChFirstSettleType() {
//        return repoIstrAggr.getChFirstSettleType();
//    }
//
//    @Override
//    public void setChFirstSettleType(String cFirstSettleType) {
//        repoIstrAggr.setChFirstSettleType(cFirstSettleType);
//    }
//
//    @Override
//    public String getChSecondSettleType() {
//        return repoIstrAggr.getChSecondSettleType();
//    }
//
//    @Override
//    public void setChSecondSettleType(String cSecondSettleType) {
//        repoIstrAggr.setChSecondSettleType(cSecondSettleType);
//    }
//
//    @Override
//    public String getChRequireOrigin() {
//        return repoIstrAggr.getChRequireOrigin();
//    }
//
//    @Override
//    public void setChRequireOrigin(String cRequireOrigin) {
//        repoIstrAggr.setChRequireOrigin(cRequireOrigin);
//    }
//
//    @Override
//    public String getChPurposeType() {
//        return repoIstrAggr.getChPurposeType();
//    }
//
//    @Override
//    public void setChPurposeType(String cPurposeType) {
//        repoIstrAggr.setChPurposeType(cPurposeType);
//    }
//
//    @Override
//    public String getChQuoteSide() {
//        return repoIstrAggr.getChQuoteSide();
//    }
//
//    @Override
//    public void setChQuoteSide(String cQuoteSide) {
//        repoIstrAggr.setChQuoteSide(cQuoteSide);
//    }
//
//    @Override
//    public Long getFtUseDays() {
//        return repoIstrAggr.getFtUseDays();
//    }
//
//    @Override
//    public void setFtUseDays(Long fUseDays) {
//        repoIstrAggr.setFtUseDays(fUseDays);
//    }
//
//
//    @Override
//    public BigDecimal getFtSecondSettleAmt() {
//        return repoIstrAggr.getFtSecondSettleAmt();
//    }
//
//    @Override
//    public void setFtSecondSettleAmt(BigDecimal ftSecondSettleAmt) {
//        repoIstrAggr.setFtSecondSettleAmt(ftSecondSettleAmt);
//    }
//
//    @Override
//    public String getChInstrSource() {
//        return repoIstrAggr.getChInstrSource();
//    }
//
//    @Override
//    public void setChInstrSource(String chInstrSource) {
//        repoIstrAggr.setChInstrSource(chInstrSource);
//    }
//
//    @Override
//    public String getChSourceKey() {
//        return repoIstrAggr.getChSourceKey();
//    }
//
//    @Override
//    public void setChSourceKey(String chSourceKey) {
//        repoIstrAggr.setChSourceKey(chSourceKey);
//    }
//
//    @Override
//    public String getChSourceNo() {
//        return repoIstrAggr.getChSourceNo();
//    }
//
//    @Override
//    public void setChSourceNo(String chSourceNo) {
//        repoIstrAggr.setChSourceNo(chSourceNo);
//    }
//
//    @Override
//    public String getChInstrSourceTwo() {
//        return repoIstrAggr.getChInstrSourceTwo();
//    }
//
//    @Override
//    public void setChInstrSourceTwo(String chInstrSourceTwo) {
//        repoIstrAggr.setChInstrSourceTwo(chInstrSourceTwo);
//    }
//
//    @Override
//    public String getChSourceKeyTwo() {
//        return repoIstrAggr.getChSourceKeyTwo();
//    }
//
//    @Override
//    public void setChSourceKeyTwo(String chSourceKeyTwo) {
//        repoIstrAggr.setChSourceKeyTwo(chSourceKeyTwo);
//    }
//
//    @Override
//    public String getChSourceNoTwo() {
//        return repoIstrAggr.getChSourceNoTwo();
//    }
//
//    @Override
//    public void setChSourceNoTwo(String chSourceNoTwo) {
//        repoIstrAggr.setChSourceNoTwo(chSourceNoTwo);
//    }
//
//    @Override
//    public String getSkInst() {
//        return repoIstrAggr.getSkInst();
//    }
//
//    @Override
//    public void setSkInst(String skInst) {
//        repoIstrAggr.setSkInst(skInst);
//    }
//
//    @Override
//    public String getChTraderId() {
//        return repoIstrAggr.getChTraderId();
//    }
//
//    @Override
//    public void setChTraderId(String chTraderId) {
//        repoIstrAggr.setChTraderId(chTraderId);
//    }
//
//    @Override
//    public Date getTsTimestamp() {
//        return repoIstrAggr.getTsTimestamp();
//    }
//
//    @Override
//    public void setTsTimestamp(Date tsTimestamp) {
//        repoIstrAggr.setTsTimestamp(tsTimestamp);
//    }
//
//    @Override
//    public String getChMemo() {
//        return repoIstrAggr.getChMemo();
//    }
//
//    @Override
//    public void setChMemo(String chMemo) {
//        repoIstrAggr.setChMemo(chMemo);
//    }
//
//
//    @Override
//    public String getChDelete() {
//        return repoIstrAggr.getChDelete();
//    }
//
//    @Override
//    public void setChDelete(String chDelete) {
//        repoIstrAggr.setChDelete(chDelete);
//    }
//
//    @Override
//    public String getChCreateId() {
//        return repoIstrAggr.getChCreateId();
//    }
//
//    @Override
//    public void setChCreateId(String chCreateId) {
//        repoIstrAggr.setChCreateId(chCreateId);
//    }
//
//    @Override
//    public Date getTsCreateTime() {
//        return repoIstrAggr.getTsCreateTime();
//    }
//
//    @Override
//    public void setTsCreateTime(Date tsCreateTime) {
//        repoIstrAggr.setTsCreateTime(tsCreateTime);
//    }
//
//    @Override
//    public String getChLastModifiedId() {
//        return repoIstrAggr.getChLastModifiedId();
//    }
//
//    @Override
//    public void setChLastModifiedId(String chLastModifiedId) {
//        repoIstrAggr.setChLastModifiedId(chLastModifiedId);
//    }
//
//    @Override
//    public Date getTsLastModifiedTime() {
//        return repoIstrAggr.getTsLastModifiedTime();
//    }
//
//    @Override
//    public void setTsLastModifiedTime(Date tsLastModifiedTime) {
//        repoIstrAggr.setTsLastModifiedTime(tsLastModifiedTime);
//    }
//
//    @Override
//    public String getChDataSource() {
//        return repoIstrAggr.getChDataSource();
//    }
//
//    @Override
//    public void setChDataSource(String chDataSource) {
//        repoIstrAggr.setChDataSource(chDataSource);
//    }
//
//    @Override
//    public String getChAuditorId() {
//        return repoIstrAggr.getChAuditorId();
//    }
//
//    @Override
//    public void setChAuditorId(String chAuditorId) {
//        repoIstrAggr.setChAuditorId(chAuditorId);
//    }
//
//    @Override
//    public String getChAuditStatus() {
//        return repoIstrAggr.getChAuditStatus();
//    }
//
//    @Override
//    public void setChAuditStatus(String chAuditStatus) {
//        repoIstrAggr.setChAuditStatus(chAuditStatus);
//    }
//
//    @Override
//    public Date getTsAuditTime() {
//        return repoIstrAggr.getTsAuditTime();
//    }
//
//    @Override
//    public void setTsAuditTime(Date tsAuditTime) {
//        repoIstrAggr.setTsAuditTime(tsAuditTime);
//    }
//
//    @Override
//    public String getChIsLock() {
//        return repoIstrAggr.getChIsLock();
//    }
//
//    @Override
//    public void setChIsLock(String cIsLock) {
//        repoIstrAggr.setChIsLock(cIsLock);
//    }
//
//    @Override
//    public String getChInstrIssuedSource() {
//        return repoIstrAggr.getChInstrIssuedSource();
//    }
//
//    @Override
//    public void setChInstrIssuedSource(String cInstrIssuedSource) {
//        repoIstrAggr.setChInstrIssuedSource(cInstrIssuedSource);
//    }
//
//
//    @Override
//    public Date getDtBeginDate() {
//        return repoIstrAggr.getDtBeginDate();
//    }
//
//    @Override
//    public void setDtBeginDate(Date dtBeginDate) {
//        repoIstrAggr.setDtBeginDate(dtBeginDate);
//    }
//
//    @Override
//    public String getChBeginTime() {
//        return repoIstrAggr.getChBeginTime();
//    }
//
//    @Override
//    public void setChBeginTime(String chBeginTime) {
//        repoIstrAggr.setChBeginTime(chBeginTime);
//    }
//
//    @Override
//    public Date getDtInstrEndDate() {
//        return repoIstrAggr.getDtInstrEndDate();
//    }
//
//    @Override
//    public void setDtInstrEndDate(Date dtInstrEndDate) {
//        repoIstrAggr.setDtInstrEndDate(dtInstrEndDate);
//    }
//
//    @Override
//    public String getChEndTime() {
//        return repoIstrAggr.getChEndTime();
//    }
//
//    @Override
//    public void setChEndTime(String chEndTime) {
//        repoIstrAggr.setChEndTime(chEndTime);
//    }
//
//    @Override
//    public String getChPreInstrDesc() {
//        return repoIstrAggr.getChPreInstrDesc();
//    }
//
//    @Override
//    public void setChPreInstrDesc(String cPreInstrDesc) {
//        repoIstrAggr.setChPreInstrDesc(cPreInstrDesc);
//    }
//
//    @Override
//    public String getChPreIndexModify() {
//        return repoIstrAggr.getChPreIndexModify();
//    }
//
//    @Override
//    public void setChPreIndexModify(String cPreIndexModify) {
//        repoIstrAggr.setChPreIndexModify(cPreIndexModify);
//    }
//}
