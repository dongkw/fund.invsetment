package fund.investment.basic.common.enums.dict.instruction;

import fund.investment.common.bean.dict.TradeDictBaseEnums;
import fund.investment.common.enums.TradeDictTypeCodeEnums;

/**
 * 清算速度
 */
public enum CSettleDaysEnums implements TradeDictBaseEnums {
    T0("70420000000001", "0", "T+0"),
    T1("70420000000002", "1", "T+1"),
    ;

    private String skId;
    private String code;
    private String name;

    private CSettleDaysEnums(String skId, String code, String name) {
        this.skId = skId;
        this.code = code;
        this.name = name;
    }

    public static CSettleDaysEnums getEnumByCode(String code) {
        CSettleDaysEnums[] var1 = values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            CSettleDaysEnums enums = var1[var3];
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

    @Override
    public String getDictSkId() {
        return this.skId;
    }
}
