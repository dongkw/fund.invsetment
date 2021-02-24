package fund.investment.basic.common.enums.dict.instruction;

import fund.investment.common.bean.dict.TradeDictBaseEnums;
import fund.investment.common.enums.TradeDictTypeCodeEnums;

/**
 * @author yufengbao
 * @date 2020/9/17 15:16
 */
public enum ForeignBusinessEnums implements TradeDictBaseEnums {
    SELL("90140000000001", "001", "001-购汇"),
    SETTLE("90140000000002", "002", "002-结汇"),
    EXCHANGE("90140000000003", "003", "003-调汇"),
    SELLANDEXCHANGE("90140000000004", "001,003", "004-购汇 & 调汇"),
    EXCHANGEANDSETTLE("90140000000005", "003,002", "005-调汇 & 结汇"),
    ;

    private String skId;
    private String code;
    private String name;

    private ForeignBusinessEnums(String skId, String code, String name) {
        this.skId = skId;
        this.code = code;
        this.name = name;
    }

    public static ForeignBusinessEnums getEnumByCode(String code) {
        ForeignBusinessEnums[] var1 = values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            ForeignBusinessEnums enums = var1[var3];
            if (enums.code.equalsIgnoreCase(code)) {
                return enums;
            }
        }

        return null;
    }

    public static String getDictTypeCode() {
        return TradeDictTypeCodeEnums.FOREIGN_BUSINESSCODE.getCode();
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
