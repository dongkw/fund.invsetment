package fund.investment.basic.common.enums.dict.entrust;

import fund.investment.basic.common.enums.TradeDictTypeCodeEnums;

/**
 * 委托冻结方式
 */
public enum TradeEntrustFrozenTypeEnums{
    //skId=71730000000000
    ENUM_0("71730000000000", "0", "T日临时冻结"),
    //skId=71730000000001
    ENUM_1("71730000000001", "1", "T+1临时冻结"),
    //skId=71730000000002
    ENUM_N("71730000000002", "N", "未冻结"),
    ;

    private String skId;
    private String code;
    private String name;

    private TradeEntrustFrozenTypeEnums(String skId, String code, String name) {
        this.skId = skId;
        this.code = code;
        this.name = name;
    }

    public static TradeEntrustFrozenTypeEnums getEnumByCode(String code) {
        TradeEntrustFrozenTypeEnums[] var1 = values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            TradeEntrustFrozenTypeEnums enums = var1[var3];
            if (enums.code.equalsIgnoreCase(code)) {
                return enums;
            }
        }

        return null;
    }

    public static String getDictTypeCode() {
        return TradeDictTypeCodeEnums.TRADE_ENTRUST_FROZEN_TYPE.getCode();
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
