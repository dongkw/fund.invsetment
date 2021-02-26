package fund.investment.basic.common.enums.dict.instruction;

import fund.investment.basic.common.enums.TradeDictTypeCodeEnums;

/**
 * 手续费处理方式
 */
public enum CFeeDealTypeEnums {
    FEEDEAL_NO_BACK("22020000000001", "1", "手续费不另返"),
    FEEDEAL_BACK("22020000000002", "2", "手续费另返"),
    NO_FEEDEAL("22020000000003", "3", "不计手续费"),
    ;

    private String skId;
    private String code;
    private String name;

    private CFeeDealTypeEnums(String skId, String code, String name) {
        this.skId = skId;
        this.code = code;
        this.name = name;
    }

    public static CFeeDealTypeEnums getEnumByCode(String code) {
        CFeeDealTypeEnums[] var1 = values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            CFeeDealTypeEnums enums = var1[var3];
            if (enums.code.equalsIgnoreCase(code)) {
                return enums;
            }
        }

        return null;
    }

    public static String getDictTypeCode() {
        return TradeDictTypeCodeEnums.TRADE_CLEAR_SPEED.getCode();
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
