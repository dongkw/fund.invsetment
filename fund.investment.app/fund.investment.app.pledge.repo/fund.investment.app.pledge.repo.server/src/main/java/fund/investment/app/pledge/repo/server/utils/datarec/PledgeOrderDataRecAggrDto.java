//package fund.investment.app.pledge.repo.server.utils.datarec;
//
//import fund.investment.app.pledge.repo.server.aggregate.trade.PledgeRepoOrderAggregate;
//import fund.investment.basic.common.enums.dict.CMatchFlagEnums;
//import fund.investment.basic.common.valueobject.InterbankPledgeBond;
//import fund.investment.basic.trade.api.dto.OrderDataHolder;
//import fund.investment.basic.trade.server.aggregate.OrderAggregate;
//import fund.investment.gateway.api.common.NumberValidUtils;
//
//import java.math.BigDecimal;
//import java.util.Date;
//import java.util.List;
//import java.util.Objects;
//import java.util.function.BiConsumer;
//
//public class PledgeOrderDataRecAggrDto {
//
//    public static <T , D , V extends OrderAggregate> void compareDataAndAggregate(T placingOrderCmd,
//                                                                                                                                V orderAggregate,
//                                                                                                                                OrderDataHolder<D> orderDataHolder,
//                                                                                                                                BiConsumer<T, V> matchConsumer,
//                                                                                                                                BiConsumer<T, V> offerConsumer,
//                                                                                                                                BiConsumer<T, V> entrustConsumer) {
//        if (Objects.nonNull(placingOrderCmd.getSkInstr())
//                && !Objects.equals(placingOrderCmd.getSkInstr(), orderAggregate.getSkInstr())) {
//            getOrderEvt(orderDataHolder).setSkInstr(placingOrderCmd.getSkInstr());
//
//        }
//
//        //匹配标志
//        if (!Objects.equals(CMatchFlagEnums.MATCH_FLAG_ENUMS_AUTO.getSkId(), orderAggregate.getChMatchFlag())
//                && Objects.nonNull(placingOrderCmd.getChMatchFlag())
//                && !Objects.equals(placingOrderCmd.getChMatchFlag(), orderAggregate.getChMatchFlag())) {
//            matchConsumer.accept(placingOrderCmd, orderAggregate);
//        }
//        //报价方向
//        if (Objects.nonNull(placingOrderCmd.getChReportDirection())
//                && !Objects.equals(placingOrderCmd.getChReportDirection(), orderAggregate.getChReportDirection())) {
//            getOrderEvt(orderDataHolder).setChReportDirection(placingOrderCmd.getChReportDirection());
//
//        }
//        //委托状态
//        if (Objects.nonNull(placingOrderCmd.getChEntrustStatus())
//                && !Objects.equals(placingOrderCmd.getChEntrustStatus(), orderAggregate.getChEntrustStatus())) {
//            entrustConsumer.accept(placingOrderCmd, orderAggregate);
//        }
//
//        //报价状态,(!!!报价状态必须在委托状态之后修改，否则会导致判断出错)
//        if (Objects.nonNull(placingOrderCmd.getChOfferStatus())
//                && !Objects.equals(placingOrderCmd.getChOfferStatus(), orderAggregate.getChOfferStatus())) {
//            offerConsumer.accept(placingOrderCmd, orderAggregate);
//        }
//
//        //撤单标志
//        if (Objects.nonNull(placingOrderCmd.getChCancelFlag())
//                && !Objects.equals(placingOrderCmd.getChCancelFlag(), orderAggregate.getChCancelFlag())) {
//            getOrderEvt(orderDataHolder).setChCancelFlag(placingOrderCmd.getChCancelFlag());
//
//        }
//        //撤单原因
//        if (Objects.nonNull(placingOrderCmd.getChCancelReason())
//                && !Objects.equals(placingOrderCmd.getChCancelReason(), orderAggregate.getChCancelReason())) {
//            getOrderEvt(orderDataHolder).setChCancelReason(placingOrderCmd.getChCancelReason());
//
//        }
//        //报价编号
//        if (Objects.nonNull(placingOrderCmd.getChReportNo())
//                && !Objects.equals(placingOrderCmd.getChReportNo(), orderAggregate.getChReportNo())) {
//            getOrderEvt(orderDataHolder).setChReportNo(placingOrderCmd.getChReportNo());
//
//        }
//        //操作类型
//        if (Objects.nonNull(placingOrderCmd.getChOperateType())
//                && !Objects.equals(placingOrderCmd.getChOperateType(), orderAggregate.getChOperateType())) {
//            getOrderEvt(orderDataHolder).setChOperateType(placingOrderCmd.getChOperateType());
//
//        }
//        //数据字段开始
//        //委托金额
//        if (placingOrderCmd.getFtEntrustAmt() != null
//                && !NumberValidUtils.equalDecimal(placingOrderCmd.getFtEntrustAmt(), orderAggregate.getFtEntrustAmt())) {
//            getOrderEvt(orderDataHolder).setFtEntrustAmt(placingOrderCmd.getFtEntrustAmt());
//
//        }
//        //委托数量
//        if (placingOrderCmd.getFtEntrustQtty() != null
//                && !NumberValidUtils.equalDecimal(placingOrderCmd.getFtEntrustQtty(), orderAggregate.getFtEntrustQtty())) {
//            getOrderEvt(orderDataHolder).setFtEntrustQtty(placingOrderCmd.getFtEntrustQtty());
//
//        }
//        //产品
//        if (Objects.nonNull(placingOrderCmd.getSkProduct())
//                && !Objects.equals(placingOrderCmd.getSkProduct(), orderAggregate.getSkProduct())) {
//            getOrderEvt(orderDataHolder).setSkProduct(placingOrderCmd.getSkProduct());
//
//        }
//        //组合
//        if (Objects.nonNull(placingOrderCmd.getSkCombi())
//                && !Objects.equals(placingOrderCmd.getSkCombi(), orderAggregate.getSkCombi())) {
//            getOrderEvt(orderDataHolder).setSkCombi(placingOrderCmd.getSkCombi());
//
//        }
//        //证券
//        if (Objects.nonNull(placingOrderCmd.getSkSecurity())
//                && !Objects.equals(placingOrderCmd.getSkSecurity(), orderAggregate.getSkSecurity())) {
//            getOrderEvt(orderDataHolder).setSkSecurity(placingOrderCmd.getSkSecurity());
//
//        }
//        //投资类型
//        if (Objects.nonNull(placingOrderCmd.getChInvestType())
//                && !Objects.equals(placingOrderCmd.getChInvestType(), orderAggregate.getChInvestType())) {
//            getOrderEvt(orderDataHolder).setChInvestType(placingOrderCmd.getChInvestType());
//
//        }
//
//
//        //回购利率
//        if (placingOrderCmd.getFtEntrustPrice() != null
//                && !NumberValidUtils.equalDecimal(placingOrderCmd.getFtEntrustPrice(), orderAggregate.getFtEntrustPrice())) {
//            getOrderEvt(orderDataHolder).setFtEntrustPrice(placingOrderCmd.getFtEntrustPrice());
//
//        }
//        //回购方向(存单和现券交易方向一致，不再匹配)
////        if (this.checkAndValidSkTradeType(placingOrderCmd.getSkTradeType(), orderAggregate.getSkTradeType())) {
////             getOrderEvt(orderEvt, initEvt).setSkTradeType(placingOrderCmd.getSkTradeType());
////
////        }
//        if (Objects.nonNull(placingOrderCmd.getSkTradeType())
//                && !Objects.equals(placingOrderCmd.getSkTradeType(), orderAggregate.getSkTradeType())) {
//            getOrderEvt(orderDataHolder).setSkTradeType(placingOrderCmd.getSkTradeType());
//
//        }
//        //清算速度
//        if (Objects.nonNull(placingOrderCmd.getChSettleDays())
//                && !Objects.equals(placingOrderCmd.getChSettleDays(), orderAggregate.getChSettleDays())) {
//            getOrderEvt(orderDataHolder).setChSettleDays(placingOrderCmd.getChSettleDays());
//
//        }
//        //交易对手
//        if (Objects.nonNull(placingOrderCmd.getSkInst())
//                && !Objects.equals(placingOrderCmd.getSkInst(), orderAggregate.getSkInst())) {
//            getOrderEvt(orderDataHolder).setSkInst(placingOrderCmd.getSkInst());
//
//        }
//        //交易对手交易员
//        if (Objects.nonNull(placingOrderCmd.getChTraderId())
//                && !Objects.equals(placingOrderCmd.getChTraderId(), orderAggregate.getChTraderId())) {
//            getOrderEvt(orderDataHolder).setChTraderId(placingOrderCmd.getChTraderId());
//
//        }
//        //执行交易员
//        //报价匹配上的时候,统一使用指令交易员信息
//        if (Objects.nonNull(placingOrderCmd.getSkTradeUserId())) {
//            if (!Objects.equals(placingOrderCmd.getSkTradeUserId(), orderAggregate.getSkTradeUserId())) {
//                getOrderEvt(orderDataHolder).setSkTradeUserId(placingOrderCmd.getSkTradeUserId());
//
//            }
//        }
//
//        //创建交易员
//        if (Objects.nonNull(placingOrderCmd.getChCreateId())
//                && !Objects.equals(placingOrderCmd.getChCreateId(), orderAggregate.getChCreateId())) {
//            getOrderEvt(orderDataHolder).setChCreateId(placingOrderCmd.getChCreateId());
//
//        }
//        //委托有效日期
//        if (placingOrderCmd.getDtInstrEndDate() != null) {
//            if (orderAggregate.getDtInstrEndDate() != null) {
//                if (placingOrderCmd.getDtInstrEndDate().getTime() - orderAggregate.getDtInstrEndDate().getTime() != 0) {
//                    getOrderEvt(orderDataHolder).setDtInstrEndDate(placingOrderCmd.getDtInstrEndDate());
//
//                }
//            } else {
//                getOrderEvt(orderDataHolder).setDtInstrEndDate(placingOrderCmd.getDtInstrEndDate());
//
//            }
//        }
//        //委托有效时间
//        if (Objects.nonNull(placingOrderCmd.getChEndTime())
//                && !Objects.equals(placingOrderCmd.getChEndTime(), orderAggregate.getChEndTime())) {
//            getOrderEvt(orderDataHolder).setChEndTime(placingOrderCmd.getChEndTime());
//
//        }
//
//        //委托业务发生时间
//        if (placingOrderCmd.getTsTimestamp() != null) {
//            if (orderAggregate.getTsTimestamp() != null) {
//                if (placingOrderCmd.getTsTimestamp().getTime() - orderAggregate.getTsTimestamp().getTime() != 0) {
//                    getOrderEvt(orderDataHolder).setTsTimestamp(placingOrderCmd.getTsTimestamp());
//
//                }
//            } else {
//                getOrderEvt(orderDataHolder).setTsTimestamp(placingOrderCmd.getTsTimestamp());
//
//            }
//        }
//
//        if (Objects.nonNull(placingOrderCmd.getChBankQuoteType())
//                && !Objects.equals(placingOrderCmd.getChBankQuoteType(), orderAggregate.getChBankQuoteType())) {
//            getOrderEvt(orderDataHolder).setChBankQuoteType(placingOrderCmd.getChBankQuoteType());
//
//        }
//
//    }
//
//    protected static <D extends CommonOrderBean> D getOrderEvt(OrderDataHolder<D> orderDataHolder) {
//        return orderDataHolder.getOrInitOrderEvt();
//    }
//
//    public static <T extends PledgeOrderBean, D extends PledgeOrderBean> void comparePledgeDataAndAggregate(T cmd, PledgeRepoOrderAggregate orderAggregate, OrderDataHolder<D> orderDataHolder) {
//        //回购天数
//        if (cmd.getFtRepDays() != null
//                && !NumberValidUtils.equalLong(cmd.getFtRepDays(), orderAggregate.getFtRepDays())) {
//            getOrderEvt(orderDataHolder).setFtRepDays(cmd.getFtRepDays());
//        }
//        //首次结算金额
//        if (cmd.getFtFirstSettleAmt() != null
//                && !NumberValidUtils.equalDecimal(cmd.getFtFirstSettleAmt(), orderAggregate.getFtFirstSettleAmt())) {
//            getOrderEvt(orderDataHolder).setFtFirstSettleAmt(cmd.getFtFirstSettleAmt());
//        }
//        //到期结算金额
//        if (cmd.getFtSecondSettleAmt() != null
//                && !NumberValidUtils.equalDecimal(cmd.getFtSecondSettleAmt(), orderAggregate.getFtSecondSettleAmt())) {
//            getOrderEvt(orderDataHolder).setFtSecondSettleAmt(cmd.getFtSecondSettleAmt());
//        }
//    }
//
//    private final PledgeRepoOrderAggregate orderAggregate;
//
//    public PledgeOrderDataRecAggrDto(PledgeRepoOrderAggregate orderAggregate) {
//        this.orderAggregate = orderAggregate;
//    }
//
//    @Override
//    public List<InterbankPledgeBond> getPledgeBondList() {
//        return orderAggregate.getPledgeBondList();
//    }
//
//    @Override
//    public void setPledgeBondList(List<InterbankPledgeBond> pledgeBondList) {
//        orderAggregate.setPledgeBondList(pledgeBondList);
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
//
//    @Override
//    public String getSkOriginId() {
//        //无需此字段
//        return null;
//    }
//
//    @Override
//    public void setSkOriginId(String skOriginId) {
//        //无需此字段
//    }
//
//
//    @Override
//    public String getSkId() {
//        return orderAggregate.getSkId();
//    }
//
//    @Override
//    public void setSkId(String skId) {
//        orderAggregate.setSkId(skId);
//    }
//
//    @Override
//    public String getSkInstr() {
//        return orderAggregate.getSkInstr();
//    }
//
//    @Override
//    public void setSkInstr(String skInstr) {
//        orderAggregate.setSkInstr(skInstr);
//    }
//
//    @Override
//    public Date getDtCurrentDate() {
//        return orderAggregate.getDtCurrentDate();
//    }
//
//    @Override
//    public void setDtCurrentDate(Date dtCurrentDate) {
//        orderAggregate.setDtCurrentDate(dtCurrentDate);
//    }
//
//    @Override
//    public String getSkProduct() {
//        return orderAggregate.getSkProduct();
//    }
//
//    @Override
//    public void setSkProduct(String skProduct) {
//        orderAggregate.setSkProduct(skProduct);
//    }
//
//    @Override
//    public String getSkCombi() {
//        return orderAggregate.getSkCombi();
//    }
//
//    @Override
//    public void setSkCombi(String skCombi) {
//        orderAggregate.setSkCombi(skCombi);
//    }
//
//    @Override
//    public String getChInvestType() {
//        return orderAggregate.getChInvestType();
//    }
//
//    @Override
//    public void setChInvestType(String chInvestType) {
//        orderAggregate.setChInvestType(chInvestType);
//    }
//
//    @Override
//    public String getChStockholderCode() {
//        return orderAggregate.getChStockholderCode();
//    }
//
//    @Override
//    public void setChStockholderCode(String chStockholderCode) {
//        orderAggregate.setChStockholderCode(chStockholderCode);
//    }
//
//    @Override
//    public String getSkSeat() {
//        return orderAggregate.getSkSeat();
//    }
//
//    @Override
//    public void setSkSeat(String skSeat) {
//        orderAggregate.setSkSeat(skSeat);
//    }
//
//    @Override
//    public String getSkSecurity() {
//        return orderAggregate.getSkSecurity();
//    }
//
//    @Override
//    public void setSkSecurity(String skSecurity) {
//        orderAggregate.setSkSecurity(skSecurity);
//    }
//
//    @Override
//    public String getSkMarket() {
//        return orderAggregate.getSkMarket();
//    }
//
//    @Override
//    public void setSkMarket(String skMarket) {
//        orderAggregate.setSkMarket(skMarket);
//    }
//
//    @Override
//    public String getSkMarktExe() {
//        return orderAggregate.getSkMarktExe();
//    }
//
//    @Override
//    public void setSkMarktExe(String skMarktExe) {
//        orderAggregate.setSkMarktExe(skMarktExe);
//    }
//
//    @Override
//    public String getSkCurrOri() {
//        return orderAggregate.getSkCurrOri();
//    }
//
//    @Override
//    public void setSkCurrOri(String skCurrOri) {
//        orderAggregate.setSkCurrOri(skCurrOri);
//    }
//
//    @Override
//    public String getSkTradeType() {
//        return orderAggregate.getSkTradeType();
//    }
//
//    @Override
//    public void setSkTradeType(String skTradeType) {
//        orderAggregate.setSkTradeType(skTradeType);
//    }
//
//    @Override
//    public String getSkSecurityReport() {
//        return orderAggregate.getSkSecurityReport();
//    }
//
//    @Override
//    public void setSkSecurityReport(String skSecurityReport) {
//        orderAggregate.setSkSecurityReport(skSecurityReport);
//    }
//
//    @Override
//    public String getChReportNo() {
//        return orderAggregate.getChReportNo();
//    }
//
//    @Override
//    public void setChReportNo(String chReportNo) {
//        orderAggregate.setChReportNo(chReportNo);
//    }
//
//    @Override
//    public String getChCancelNo() {
//        return orderAggregate.getChCancelNo();
//    }
//
//    @Override
//    public void setChCancelNo(String chCancelNo) {
//        orderAggregate.setChCancelNo(chCancelNo);
//    }
//
//    @Override
//    public String getChConfirmNo() {
//        return orderAggregate.getChConfirmNo();
//    }
//
//    @Override
//    public void setChConfirmNo(String chConfirmNo) {
//        orderAggregate.setChConfirmNo(chConfirmNo);
//    }
//
//    @Override
//    public String getChCancelFlag() {
//        return orderAggregate.getChCancelFlag();
//    }
//
//    @Override
//    public void setChCancelFlag(String chCancelFlag) {
//        orderAggregate.setChCancelFlag(chCancelFlag);
//    }
//
//    @Override
//    public String getChCashDirection() {
//        return orderAggregate.getChCashDirection();
//    }
//
//    @Override
//    public void setChCashDirection(String chCashDirection) {
//        orderAggregate.setChCashDirection(chCashDirection);
//    }
//
//    @Override
//    public String getChSecuDirection() {
//        return orderAggregate.getChSecuDirection();
//    }
//
//    @Override
//    public void setChSecuDirection(String chSecuDirection) {
//        orderAggregate.setChSecuDirection(chSecuDirection);
//    }
//
//    @Override
//    public String getChReportDirection() {
//        return orderAggregate.getChReportDirection();
//    }
//
//    @Override
//    public void setChReportDirection(String chReportDirection) {
//        orderAggregate.setChReportDirection(chReportDirection);
//    }
//
//    @Override
//    public BigDecimal getFtEntrustPrice() {
//        return orderAggregate.getFtEntrustPrice();
//    }
//
//    @Override
//    public void setFtEntrustPrice(BigDecimal ftEntrustPrice) {
//        orderAggregate.setFtEntrustPrice(ftEntrustPrice);
//    }
//
//    @Override
//    public BigDecimal getFtFactPrice() {
//        return orderAggregate.getFtFactPrice();
//    }
//
//    @Override
//    public void setFtFactPrice(BigDecimal ftFactPrice) {
//        orderAggregate.setFtFactPrice(ftFactPrice);
//    }
//
//    @Override
//    public BigDecimal getFtEntrustQtty() {
//        return orderAggregate.getFtEntrustQtty();
//    }
//
//    @Override
//    public void setFtEntrustQtty(BigDecimal ftEntrustQtty) {
//        orderAggregate.setFtEntrustQtty(ftEntrustQtty);
//    }
//
//    @Override
//    public BigDecimal getFtEntrustAmt() {
//        return orderAggregate.getFtEntrustAmt();
//    }
//
//    @Override
//    public void setFtEntrustAmt(BigDecimal ftEntrustAmt) {
//        orderAggregate.setFtEntrustAmt(ftEntrustAmt);
//    }
//
//    @Override
//    public Date getDtEntrustDate() {
//        return orderAggregate.getDtEntrustDate();
//    }
//
//    @Override
//    public void setDtEntrustDate(Date dtEntrustDate) {
//        orderAggregate.setDtEntrustDate(dtEntrustDate);
//    }
//
//    @Override
//    public String getChEntrustTime() {
//        return orderAggregate.getChEntrustTime();
//    }
//
//    @Override
//    public void setChEntrustTime(String chEntrustTime) {
//        orderAggregate.setChEntrustTime(chEntrustTime);
//    }
//
//    @Override
//    public String getChReportTime() {
//        return orderAggregate.getChReportTime();
//    }
//
//    @Override
//    public void setChReportTime(String chReportTime) {
//        orderAggregate.setChReportTime(chReportTime);
//    }
//
//    @Override
//    public String getChFirstTradeTime() {
//        return orderAggregate.getChFirstTradeTime();
//    }
//
//    @Override
//    public void setChFirstTradeTime(String chFirstTradeTime) {
//        orderAggregate.setChFirstTradeTime(chFirstTradeTime);
//    }
//
//    @Override
//    public BigDecimal getFtTradeQtty() {
//        return orderAggregate.getFtTradeQtty();
//    }
//
//    @Override
//    public void setFtTradeQtty(BigDecimal ftTradeQtty) {
//        orderAggregate.setFtTradeQtty(ftTradeQtty);
//    }
//
//    @Override
//    public BigDecimal getFtTradeAmt() {
//        return orderAggregate.getFtTradeAmt();
//    }
//
//    @Override
//    public void setFtTradeAmt(BigDecimal ftTradeAmt) {
//        orderAggregate.setFtTradeAmt(ftTradeAmt);
//    }
//
//    @Override
//    public BigDecimal getFtCancelQtty() {
//        return orderAggregate.getFtCancelQtty();
//    }
//
//    @Override
//    public void setFtCancelQtty(BigDecimal ftCancelQtty) {
//        orderAggregate.setFtCancelQtty(ftCancelQtty);
//    }
//
//    @Override
//    public String getChTradeTimes() {
//        return orderAggregate.getChTradeTimes();
//    }
//
//    @Override
//    public void setChTradeTimes(String chTradeTimes) {
//        orderAggregate.setChTradeTimes(chTradeTimes);
//    }
//
//    @Override
//    public String getSkTradeUserId() {
//        return orderAggregate.getSkTradeUserId();
//    }
//
//    @Override
//    public void setSkTradeUserId(String skTradeUserId) {
//        orderAggregate.setSkTradeUserId(skTradeUserId);
//    }
//
//    @Override
//    public String getChCancelReason() {
//        return orderAggregate.getChCancelReason();
//    }
//
//    @Override
//    public void setChCancelReason(String chCancelReason) {
//        orderAggregate.setChCancelReason(chCancelReason);
//    }
//
//    @Override
//    public String getChEntrustStatus() {
//        return orderAggregate.getChEntrustStatus();
//    }
//
//    @Override
//    public void setChEntrustStatus(String chEntrustStatus) {
//        orderAggregate.setChEntrustStatus(chEntrustStatus);
//    }
//
//    @Override
//    public String getChFrozenType() {
//        return orderAggregate.getChFrozenType();
//    }
//
//    @Override
//    public void setChFrozenType(String chFrozenType) {
//        orderAggregate.setChFrozenType(chFrozenType);
//    }
//
//    @Override
//    public String getChEntrustType() {
//        return orderAggregate.getChEntrustType();
//    }
//
//    @Override
//    public void setChEntrustType(String chEntrustType) {
//        orderAggregate.setChEntrustType(chEntrustType);
//    }
//
//    @Override
//    public String getChEntrustSource() {
//        return orderAggregate.getChEntrustSource();
//    }
//
//    @Override
//    public void setChEntrustSource(String chEntrustSource) {
//        orderAggregate.setChEntrustSource(chEntrustSource);
//    }
//
//    @Override
//    public String getChOfferStatus() {
//        return orderAggregate.getChOfferStatus();
//    }
//
//    @Override
//    public void setChOfferStatus(String chOfferStatus) {
//        orderAggregate.setChOfferStatus(chOfferStatus);
//    }
//
//    @Override
//    public String getChOperateType() {
//        return orderAggregate.getChOperateType();
//    }
//
//    @Override
//    public void setChOperateType(String chOperateType) {
//        orderAggregate.setChOperateType(chOperateType);
//    }
//
//    @Override
//    public String getChMatchFlag() {
//        return orderAggregate.getChMatchFlag();
//    }
//
//    @Override
//    public void setChMatchFlag(String chMatchFlag) {
//        orderAggregate.setChMatchFlag(chMatchFlag);
//    }
//
//    @Override
//    public String getChSettleDays() {
//        return orderAggregate.getChSettleDays();
//    }
//
//    @Override
//    public void setChSettleDays(String chSettleDays) {
//        orderAggregate.setChSettleDays(chSettleDays);
//    }
//
//    @Override
//    public Long getFtRepDays() {
//        return orderAggregate.getFtRepDays();
//    }
//
//    @Override
//    public void setFtRepDays(Long ftRepDays) {
//        orderAggregate.setFtRepDays(ftRepDays);
//    }
//
//    @Override
//    public BigDecimal getFtFirstSettleAmt() {
//        return orderAggregate.getFtFirstSettleAmt();
//    }
//
//    @Override
//    public void setFtFirstSettleAmt(BigDecimal ftFirstSettleAmt) {
//        orderAggregate.setFtFirstSettleAmt(ftFirstSettleAmt);
//    }
//
//    @Override
//    public BigDecimal getFtSecondSettleAmt() {
//        return orderAggregate.getFtSecondSettleAmt();
//    }
//
//    @Override
//    public void setFtSecondSettleAmt(BigDecimal ftSecondSettleAmt) {
//        orderAggregate.setFtSecondSettleAmt(ftSecondSettleAmt);
//    }
//
//    @Override
//    public BigDecimal getFtFirstInterest() {
//        return orderAggregate.getFtFirstInterest();
//    }
//
//    @Override
//    public void setFtFirstInterest(BigDecimal ftFirstInterest) {
//        orderAggregate.setFtFirstInterest(ftFirstInterest);
//    }
//
//    @Override
//    public BigDecimal getFtSecondInterest() {
//        return orderAggregate.getFtSecondInterest();
//    }
//
//    @Override
//    public void setFtSecondInterest(BigDecimal ftSecondInterest) {
//        orderAggregate.setFtSecondInterest(ftSecondInterest);
//    }
//
//    @Override
//    public String getChApplyCompleteFlag() {
//        return orderAggregate.getChApplyCompleteFlag();
//    }
//
//    @Override
//    public void setChApplyCompleteFlag(String chApplyCompleteFlag) {
//        orderAggregate.setChApplyCompleteFlag(chApplyCompleteFlag);
//    }
//
//    @Override
//    public String getChFixsendFlag() {
//        return orderAggregate.getChFixsendFlag();
//    }
//
//    @Override
//    public void setChFixsendFlag(String chFixsendFlag) {
//        orderAggregate.setChFixsendFlag(chFixsendFlag);
//    }
//
//    @Override
//    public String getChInstrSource() {
//        return orderAggregate.getChInstrSource();
//    }
//
//    @Override
//    public void setChInstrSource(String chInstrSource) {
//        orderAggregate.setChInstrSource(chInstrSource);
//    }
//
//    @Override
//    public String getChSourceKey() {
//        return orderAggregate.getChSourceKey();
//    }
//
//    @Override
//    public void setChSourceKey(String chSourceKey) {
//        orderAggregate.setChSourceKey(chSourceKey);
//    }
//
//    @Override
//    public String getChSourceNo() {
//        return orderAggregate.getChSourceNo();
//    }
//
//    @Override
//    public void setChSourceNo(String chSourceNo) {
//        orderAggregate.setChSourceNo(chSourceNo);
//    }
//
//    @Override
//    public String getChInstrSourceTwo() {
//        return orderAggregate.getChInstrSourceTwo();
//    }
//
//    @Override
//    public void setChInstrSourceTwo(String chInstrSourceTwo) {
//        orderAggregate.setChInstrSourceTwo(chInstrSourceTwo);
//    }
//
//    @Override
//    public String getChSourceKeyTwo() {
//        return orderAggregate.getChSourceKeyTwo();
//    }
//
//    @Override
//    public void setChSourceKeyTwo(String chSourceKeyTwo) {
//        orderAggregate.setChSourceKeyTwo(chSourceKeyTwo);
//    }
//
//    @Override
//    public String getChSourceNoTwo() {
//        return orderAggregate.getChSourceNoTwo();
//    }
//
//    @Override
//    public void setChSourceNoTwo(String chSourceNoTwo) {
//        orderAggregate.setChSourceNoTwo(chSourceNoTwo);
//    }
//
//    @Override
//    public String getSkInst() {
//        return orderAggregate.getSkInst();
//    }
//
//    @Override
//    public void setSkInst(String skInst) {
//        orderAggregate.setSkInst(skInst);
//    }
//
//    @Override
//    public String getChTraderId() {
//        return orderAggregate.getChTraderId();
//    }
//
//    @Override
//    public void setChTraderId(String chTraderId) {
//        orderAggregate.setChTraderId(chTraderId);
//    }
//
//    @Override
//    public Date getTsTimestamp() {
//        return orderAggregate.getTsTimestamp();
//    }
//
//    @Override
//    public void setTsTimestamp(Date tsTimestamp) {
//        orderAggregate.setTsTimestamp(tsTimestamp);
//    }
//
//    @Override
//    public String getChMemo() {
//        return orderAggregate.getChMemo();
//    }
//
//    @Override
//    public void setChMemo(String chMemo) {
//        orderAggregate.setChMemo(chMemo);
//    }
//
//    @Override
//    public String getSkKeyId() {
//        return orderAggregate.getSkKeyId();
//    }
//
//    @Override
//    public void setSkKeyId(String skKeyId) {
//        orderAggregate.setSkKeyId(skKeyId);
//    }
//
//    @Override
//    public String getChDelete() {
//        return orderAggregate.getChDelete();
//    }
//
//    @Override
//    public void setChDelete(String chDelete) {
//        orderAggregate.setChDelete(chDelete);
//    }
//
//    @Override
//    public String getChCreateId() {
//        return orderAggregate.getChCreateId();
//    }
//
//    @Override
//    public void setChCreateId(String chCreateId) {
//        orderAggregate.setChCreateId(chCreateId);
//    }
//
//    @Override
//    public Date getTsCreateTime() {
//        return orderAggregate.getTsCreateTime();
//    }
//
//    @Override
//    public void setTsCreateTime(Date tsCreateTime) {
//        orderAggregate.setTsCreateTime(tsCreateTime);
//    }
//
//    @Override
//    public String getChLastModifiedId() {
//        return orderAggregate.getChLastModifiedId();
//    }
//
//    @Override
//    public void setChLastModifiedId(String chLastModifiedId) {
//        orderAggregate.setChLastModifiedId(chLastModifiedId);
//    }
//
//    @Override
//    public Date getTsLastModifiedTime() {
//        return orderAggregate.getTsLastModifiedTime();
//    }
//
//    @Override
//    public void setTsLastModifiedTime(Date tsLastModifiedTime) {
//        orderAggregate.setTsLastModifiedTime(tsLastModifiedTime);
//    }
//
//    @Override
//    public String getChDataSource() {
//        return orderAggregate.getChDataSource();
//    }
//
//    @Override
//    public void setChDataSource(String chDataSource) {
//        orderAggregate.setChDataSource(chDataSource);
//    }
//
//    @Override
//    public String getChAuditorId() {
//        return orderAggregate.getChAuditorId();
//    }
//
//    @Override
//    public void setChAuditorId(String chAuditorId) {
//        orderAggregate.setChAuditorId(chAuditorId);
//    }
//
//    @Override
//    public String getChAuditStatus() {
//        return orderAggregate.getChAuditStatus();
//    }
//
//    @Override
//    public void setChAuditStatus(String chAuditStatus) {
//        orderAggregate.setChAuditStatus(chAuditStatus);
//    }
//
//    @Override
//    public Date getTsAuditTime() {
//        return orderAggregate.getTsAuditTime();
//    }
//
//    @Override
//    public void setTsAuditTime(Date tsAuditTime) {
//        orderAggregate.setTsAuditTime(tsAuditTime);
//    }
//
//    @Override
//    public String getChBankQuoteType() {
//        return orderAggregate.getChBankQuoteType();
//    }
//
//    @Override
//    public void setChBankQuoteType(String chBankQuoteType) {
//        orderAggregate.setChBankQuoteType(chBankQuoteType);
//    }
//
//    @Override
//    public String getChClearType() {
//        return orderAggregate.getChClearType();
//    }
//
//    @Override
//    public void setChClearType(String chClearType) {
//        orderAggregate.setChClearType(chClearType);
//    }
//
//    @Override
//    public Date getDtBeginDate() {
//        return orderAggregate.getDtBeginDate();
//    }
//
//    @Override
//    public void setDtBeginDate(Date dtBeginDate) {
//        orderAggregate.setDtBeginDate(dtBeginDate);
//    }
//
//    @Override
//    public String getChBeginTime() {
//        return orderAggregate.getChBeginTime();
//    }
//
//    @Override
//    public void setChBeginTime(String chBeginTime) {
//        orderAggregate.setChBeginTime(chBeginTime);
//    }
//
//    @Override
//    public Date getDtInstrEndDate() {
//        return orderAggregate.getDtInstrEndDate();
//    }
//
//    @Override
//    public void setDtInstrEndDate(Date dtInstrEndDate) {
//        orderAggregate.setDtInstrEndDate(dtInstrEndDate);
//    }
//
//    @Override
//    public String getChEndTime() {
//        return orderAggregate.getChEndTime();
//    }
//
//    @Override
//    public void setChEndTime(String chEndTime) {
//        orderAggregate.setChEndTime(chEndTime);
//    }
//}
