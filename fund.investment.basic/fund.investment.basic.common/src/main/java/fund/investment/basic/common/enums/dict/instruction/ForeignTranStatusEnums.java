package fund.investment.basic.common.enums.dict.instruction;

import fund.investment.basic.common.enums.TradeDictTypeCodeEnums;

/**
 * @author yufengbao
 * @date 2020/9/17 15:16
 */
public enum ForeignTranStatusEnums {
    SEND("90220000000001", "1", "已发送"),
    UNSEND("90220000000002", "0", "未发送"),
    SENDFAIL("90220000000003", "9", "发送失败"),
    ;

    private String skId;
    private String code;
    private String name;

    private ForeignTranStatusEnums(String skId, String code, String name) {
        this.skId = skId;
        this.code = code;
        this.name = name;
    }

    public static ForeignTranStatusEnums getEnumByCode(String code) {
        ForeignTranStatusEnums[] var1 = values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            ForeignTranStatusEnums enums = var1[var3];
            if (enums.code.equalsIgnoreCase(code)) {
                return enums;
            }
        }

        return null;
    }

    public static String getDictTypeCode() {
        return TradeDictTypeCodeEnums.FOREIGN_TRANSFERSTATUS.getCode();
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
