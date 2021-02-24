package fund.investment.basic.common.enums.dict.instruction;

import fund.investment.common.bean.dict.TradeDictBaseEnums;
import fund.investment.common.enums.TradeDictTypeCodeEnums;

/**
 * @author yufengbao
 * @date 2020/9/17 15:16
 */
public enum ForeignArrStatusEnums implements TradeDictBaseEnums {
    YES("90170000000001", "1", "已到账"),
    NO("90170000000002", "0", "未到账"),
    ;

    private String skId;
    private String code;
    private String name;

    private ForeignArrStatusEnums(String skId, String code, String name) {
        this.skId = skId;
        this.code = code;
        this.name = name;
    }

    public static ForeignArrStatusEnums getEnumByCode(String code) {
        ForeignArrStatusEnums[] var1 = values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            ForeignArrStatusEnums enums = var1[var3];
            if (enums.code.equalsIgnoreCase(code)) {
                return enums;
            }
        }

        return null;
    }

    public static String getDictTypeCode() {
        return TradeDictTypeCodeEnums.FOREIGN_ISARRIVE.getCode();
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
