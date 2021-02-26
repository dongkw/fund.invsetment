package fund.investment.basic.common.enums.dict.instruction;


import fund.investment.basic.common.enums.TradeDictTypeCodeEnums;

/**
 * @author yufengbao
 * @date 2020/9/17 15:16
 */
public enum ForeignActionStatusEnums {
    TRANSFER("90120000000001", "1", "已划款"),
    ARR("90120000000002", "2", "已到账"),
    FAIL("90120000000003", "3", "划款失败"),
    PRINT("90120000000004", "4", "打印"),
    FAX("90120000000005", "5", "传真"),
    ;

    private String skId;
    private String code;
    private String name;

    private ForeignActionStatusEnums(String skId, String code, String name) {
        this.skId = skId;
        this.code = code;
        this.name = name;
    }

    public static ForeignActionStatusEnums getEnumByCode(String code) {
        ForeignActionStatusEnums[] var1 = values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            ForeignActionStatusEnums enums = var1[var3];
            if (enums.code.equalsIgnoreCase(code)) {
                return enums;
            }
        }

        return null;
    }

    public static String getDictTypeCode() {
        return TradeDictTypeCodeEnums.FOREIGN_ACTIONTYPE.getCode();
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
