package fund.investment.basic.common.enums.dict;

import fund.investment.common.bean.dict.TradeDictBaseEnums;

public enum SkTradeTypeEnums implements TradeDictBaseEnums {
    TRADE_TYPE_ZQPX("00060500000007","", "债券派息"),
    TRADE_TYPE_TQHB("","", "提前还本"),
    TRADE_TYPE_ZQDF("00060200000037","", "债券兑付"),
    TRADE_TYPE_FEE_PAY("00061300000001", "1300000001", " 费用支付"),
    TRADE_TYPE_FEE_GET("00061300000002", "1300000002", " 费用提取"),
    TRADE_TYPE_NHG("00060400000001", "0400000001", " 逆回购"),
    TRADE_TYPE_ZHG("00060400000002", "0400000002", " 正回购"),
    TRADE_TYPE_XY_NHG("00060400000003", "0400000003", " 逆回购(协议回购)"),
    TRADE_TYPE_XY_ZHG("00060400000004", "0400000004", " 正回购(协议回购)"),
    TRADE_TYPE_HQ_NHG("00060400000007", "0400000007", " 正回购换券"),
    TRADE_TYPE_HQ_ZHG("00060400000008", "0400000008", " 逆回购换券"),
    TRADE_TYPE_BHQ_NHG("00060400000009", "0400000009", " 正回购被换券"),
    TRADE_TYPE_BHQ_ZHG("00060400000010", "0400000010", " 逆回购被换券"),
    TRADE_TYPE_ZHGDQ("00060400000005", "0400000005", " 正回购到期"),
    TRADE_TYPE_NHGDQ("00060400000006", "0400000006", " 逆回购到期"),
    TRADE_TYPE_ZQMR("00060200000001", "0200000001", " 债券买入"),
    TRADE_TYPE_ZQMC("00060200000002", "0200000002", " 债券卖出"),
    TRADE_TYPE_CKSQ("00060800000001", "0800000001", " 存款首期"),
    TRADE_TYPE_CKDQ("00060800000002", "0800000002", " 存款到期"),
    TRADE_TYPE_CKTQZQ("00060800000003", "0800000003", " 存款提前支取"),
    TRADE_TYPE_TA_SG("00061400000001", "1400000001", "TA申购"),
    TRADE_TYPE_TA_SH("00061400000002", "1400000002", "TA赎回"),
    TRADE_TYPE_TA_ZR("00061400000003", "1400000003", "TA转入"),
    TRADE_TYPE_TA_ZC("00061400000004", "1400000004", "TA转出"),
    TRADE_TYPE_TA_XJFH("00061400000005", "1400000005", "TA现金分红"),
    TRADE_TYPE_TA_HLZT("00061400000006", "1400000006", "TA分红转投"),
    TRADE_TYPE_ZQSSLT("00060200000006", "0200000006", "债券上市流通"),
    TRADE_TYPE_ZQFXMR("00060200000007", "0200000007", "分销买入"),
    TRADE_TYPE_GPMR("00060100000001", "0100000001", "股票买入"),
    TRADE_TYPE_GPMC("00060100000002", "0100000002", "股票卖出"),
    TRADE_TYPE_ZQFXMR_O32("","K", "债券分销买入"),
    TRADE_TYPE_ZQHS("00060200000012", "0200000012", "债券回售"),
    TRADE_TYPE_XZZQ("00060200100003", "0200100003", "新债中签"),
    TRADE_TYPE_XZSG("00060200100001", "0200100001", "新债申购");

    private String skId;
    private String code;
    private String name;

    private SkTradeTypeEnums(String skId, String code, String name) {
        this.skId = skId;
        this.code = code;
        this.name = name;
    }


    public static SkTradeTypeEnums getEnumByCode(String code) {
        SkTradeTypeEnums[] var1 = values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            SkTradeTypeEnums enums = var1[var3];
            if (enums.code.equalsIgnoreCase(code)) {
                return enums;
            }
        }

        return null;
    }

    public static String getDictTypeCode() {
        return "TRADE_BASE_TRADE_TYPE";
    }

    public String getType() {
        return getDictTypeCode();
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String getDictSkId() {
        return this.skId;
    }
}
