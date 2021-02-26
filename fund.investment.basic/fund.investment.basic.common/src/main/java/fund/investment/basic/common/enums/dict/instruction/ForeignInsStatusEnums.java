package fund.investment.basic.common.enums.dict.instruction;

import fund.investment.basic.common.enums.TradeDictTypeCodeEnums;

/**
 * @author yufengbao
 * @date 2020/9/17 15:16
 */
public enum ForeignInsStatusEnums {
    NEW("90160000000001", "1", "指令下达"),
    INQUIRY("90160000000002", "2", "汇率询价,复核交易"),
    CANCELED("90160000000003", "9", "指令撤销"),
    TRANSFER("90160000000004", "3", "划款指令"),
    DEAL("90160000000005", "4", "成交回报"),
    SETTLE("90160000000006", "5", "结算回报"),
    REFUSE("90250000000007", "0", "已拒绝"),
    FAIL("90250000000008", "10", "划款失败"),
    SUPPLE("90250000000009", "11", "指令补入"),
    ;

    private String skId;
    private String code;
    private String name;

    private ForeignInsStatusEnums(String skId, String code, String name) {
        this.skId = skId;
        this.code = code;
        this.name = name;
    }

    public static ForeignInsStatusEnums getEnumByCode(String code) {
        ForeignInsStatusEnums[] var1 = values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            ForeignInsStatusEnums enums = var1[var3];
            if (enums.code.equalsIgnoreCase(code)) {
                return enums;
            }
        }

        return null;
    }

    public static String getDictTypeCode() {
        return TradeDictTypeCodeEnums.FOREIGN_INSTRUCTSTATUS.getCode();
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
