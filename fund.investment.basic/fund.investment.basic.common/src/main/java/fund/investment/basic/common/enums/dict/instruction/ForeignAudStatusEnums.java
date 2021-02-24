package fund.investment.basic.common.enums.dict.instruction;

import fund.investment.common.bean.dict.TradeDictBaseEnums;
import fund.investment.common.enums.TradeDictTypeCodeEnums;

/**
 * @author yufengbao
 * @date 2020/9/17 15:16
 */
public enum ForeignAudStatusEnums implements TradeDictBaseEnums {
    NO("90130000000001", "0", "审核不通过"),
    YES("90130000000002", "1", "审核通过"),
    UNAUD("90130000000000", "2", "未审核"),
    ;

    private String skId;
    private String code;
    private String name;

    private ForeignAudStatusEnums(String skId, String code, String name) {
        this.skId = skId;
        this.code = code;
        this.name = name;
    }

    public static ForeignAudStatusEnums getEnumByCode(String code) {
        ForeignAudStatusEnums[] var1 = values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            ForeignAudStatusEnums enums = var1[var3];
            if (enums.code.equalsIgnoreCase(code)) {
                return enums;
            }
        }

        return null;
    }

    public static String getDictTypeCode() {
        return TradeDictTypeCodeEnums.FOREIGN_AUDITSTATUS.getCode();
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
