package fund.investment.basic.common.http.enums;

/**
 * 交易表名
 */
public enum GatewayChannelCodeEnums {
    FD01("FD01", "方达"),
    YH01("ZY01", "赢和"),
    ;


    private String code;
    private String name;

    private GatewayChannelCodeEnums(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static GatewayChannelCodeEnums getEnumByCode(String code) {
        GatewayChannelCodeEnums[] var1 = values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            GatewayChannelCodeEnums enums = var1[var3];
            if (enums.code.equalsIgnoreCase(code)) {
                return enums;
            }
        }

        return null;
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }
}
