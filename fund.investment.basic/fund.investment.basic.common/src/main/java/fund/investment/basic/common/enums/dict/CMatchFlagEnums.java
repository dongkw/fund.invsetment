package fund.investment.basic.common.enums.dict;

import fund.investment.common.bean.dict.TradeDictBaseEnums;
import fund.investment.common.enums.TradeDictTypeCodeEnums;

/**
 * 配对状态
 */
public enum CMatchFlagEnums implements TradeDictBaseEnums {
    //skId=70460000000001
    MATCH_FLAG_ENUMS_DONOT("70460000000001", "0", "未配对"),
    //skId=70460000000002
    MATCH_FLAG_ENUMS_SUCESS("70460000000002", "1", "配对成功"),
    //skId=70460000000003
    MATCH_FLAG_ENUMS_FAIL("70460000000003", "2", "配对失败"),
    //skId=70460000000004
    MATCH_FLAG_ENUMS_NO("70460000000004", "3", "无需匹配"),
    //skId=70460000000005
    MATCH_FLAG_ENUMS_AUTO("70460000000005", "4", "自动匹配"),
    //skId=70460000000006
    MATCH_FLAG_ENUMS_MANUL("70460000000006", "5", "手工匹配"),
    //skId=70460000000007
    MATCH_FLAG_ENUMS_CANCEL("70460000000007", "6", "撤销匹配"),
    ;

    private String skId;
    private String code;
    private String name;

    private CMatchFlagEnums(String skId, String code, String name) {
        this.skId = skId;
        this.code = code;
        this.name = name;
    }

    public static CMatchFlagEnums getEnumByCode(String code) {
        CMatchFlagEnums[] var1 = values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            CMatchFlagEnums enums = var1[var3];
            if (enums.code.equalsIgnoreCase(code)) {
                return enums;
            }
        }

        return null;
    }

    public static String getDictTypeCode() {
        return TradeDictTypeCodeEnums.TRADE_INS_MATCH.getCode();
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
