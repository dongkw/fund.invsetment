package fund.investment.basic.common.enums.dict.instruction;


import fund.investment.common.bean.dict.TradeDictBaseEnums;

//投资分类/会计准则
public enum InvestTypeEnums implements TradeDictBaseEnums {
    TRADED("00120010000000", "1", "交易性");

    private String skId;
    private String code;
    private String name;


    private InvestTypeEnums(String skId, String code, String name) {
        this.skId = skId;
        this.code = code;
        this.name = name;
    }

    public static InvestTypeEnums getEnumByCode(String code) {
        InvestTypeEnums[] var1 = values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            InvestTypeEnums enums = var1[var3];
            if (enums.code.equalsIgnoreCase(code)) {
                return enums;
            }
        }

        return null;
    }

    public static String getDictTypeCode() {
        return "BASE_ACCOUNTING";
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
