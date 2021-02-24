package fund.investment.basic.common.enums.dict.instruction;

import fund.investment.common.bean.dict.TradeDictBaseEnums;
import fund.investment.common.enums.TradeDictTypeCodeEnums;

/**
 * 结算方式
 */
public enum CSettleTypeEnums implements TradeDictBaseEnums {
    //skId=70500000000001
    DVP("70500000000001", "1", "券款对付"),
    //skId=70500000000002
    ENUM_2("70500000000002", "2", "见券付款"),
    //skId=70500000000003
    ENUM_3("70500000000003", "3", "见款付券"),
    //skId=70500000000004
    ENUM_4("70500000000004", "4", "纯券过户"),
    //skId=70500000000006
    ENUM_6("70500000000006", "6", "券券对付"),
    //skId=70500000000008
    ENUM_8("70500000000008", "8", "返券付费解券"),
    //skId=70500000000009
    ENUM_9("70500000000009", "9", "券费对付"),
    ;

    private String skId;
    private String code;
    private String name;

    private CSettleTypeEnums(String skId, String code, String name) {
        this.skId = skId;
        this.code = code;
        this.name = name;
    }

    public static CSettleTypeEnums getEnumByCode(String code) {
        CSettleTypeEnums[] var1 = values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            CSettleTypeEnums enums = var1[var3];
            if (enums.code.equalsIgnoreCase(code)) {
                return enums;
            }
        }

        return null;
    }

    public static String getDictTypeCode() {
        return TradeDictTypeCodeEnums.TRADE_BALANCE_TYPE.getCode();
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
