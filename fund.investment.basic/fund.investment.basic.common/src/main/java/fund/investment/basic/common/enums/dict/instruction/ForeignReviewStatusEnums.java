package fund.investment.basic.common.enums.dict.instruction;

import fund.investment.common.bean.dict.TradeDictBaseEnums;
import fund.investment.common.enums.TradeDictTypeCodeEnums;

/**
 * @author yufengbao
 * @date 2020/9/17 15:16
 */
public enum ForeignReviewStatusEnums implements TradeDictBaseEnums {
    YES("90200000000001", "1", "已复核"),
    NO("90200000000002", "O", "未复核"),
    ;

    private String skId;
    private String code;
    private String name;

    private ForeignReviewStatusEnums(String skId, String code, String name) {
        this.skId = skId;
        this.code = code;
        this.name = name;
    }

    public static ForeignReviewStatusEnums getEnumByCode(String code) {
        ForeignReviewStatusEnums[] var1 = values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            ForeignReviewStatusEnums enums = var1[var3];
            if (enums.code.equalsIgnoreCase(code)) {
                return enums;
            }
        }

        return null;
    }

    public static String getDictTypeCode() {
        return TradeDictTypeCodeEnums.FOREIGN_REVIEWSTATUS.getCode();
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
