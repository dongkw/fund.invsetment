package fund.investment.basic.common.enums.dict.entrust;

import fund.investment.basic.common.enums.TradeDictTypeCodeEnums;

/**
 * 撤单标志
 */
public enum CCancelFlagEnums {
    //skId=71700000000000
    UNCANCEL("71700000000000", "0", "否"),
    //skId=71700000000001
    ENUM_1("71700000000001", "1", "正常撤单"),
    //skId=71700000000002
    ENUM_2("71700000000002", "2", "内部撤单"),
    //skId=71700000000003
    ENUM_3("71700000000003", "3", "异常撤单"),
    ;

    private String skId;
    private String code;
    private String name;

    private CCancelFlagEnums(String skId, String code, String name) {
        this.skId = skId;
        this.code = code;
        this.name = name;
    }

    public static CCancelFlagEnums getEnumByCode(String code) {
        CCancelFlagEnums[] var1 = values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            CCancelFlagEnums enums = var1[var3];
            if (enums.code.equalsIgnoreCase(code)) {
                return enums;
            }
        }

        return null;
    }

    public static String getDictTypeCode() {
        return TradeDictTypeCodeEnums.TRADE_CANCEL_FLAG.getCode();
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
