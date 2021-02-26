package fund.investment.basic.common.enums.dict.entrust;


/**
 * 报价状态状态
 */
public enum TradeQuotaTypeEnums {
    TALK("71900000000001", "1", "对话报价"),
    REQUEST("71900000000002", "2", "请求报价");

    private String skId;
    private String code;
    private String name;

    private TradeQuotaTypeEnums(String skId, String code, String name) {
        this.skId = skId;
        this.code = code;
        this.name = name;
    }

    public static TradeQuotaTypeEnums getEnumByCode(String code) {
        TradeQuotaTypeEnums[] var1 = values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            TradeQuotaTypeEnums enums = var1[var3];
            if (enums.code.equalsIgnoreCase(code)) {
                return enums;
            }
        }

        return null;
    }

    public static String getDictTypeCode() {
        return "TRADE_BANK_QUOTE_TYPE";
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

    public String getDictSkId() {
        return this.skId;
    }
}
