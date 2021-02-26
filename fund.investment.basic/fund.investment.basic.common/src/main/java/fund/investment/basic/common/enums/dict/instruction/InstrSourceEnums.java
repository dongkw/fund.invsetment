package fund.investment.basic.common.enums.dict.instruction;


import fund.investment.basic.common.enums.TradeDictTypeCodeEnums;

//指令来源1、系统指令，2、总行指令，3、O32指令，4、方达指令
public enum InstrSourceEnums {
    SYS("70430000000001", "1", "系统指令"),
    BANK("70430000000002", "2", "总行指令"),
    O32("70430000000003", "3", "O32指令"),
    FD("70430000000004", "4", "方达指令");

    private String skId;
    private String code;
    private String name;

    private InstrSourceEnums(String skId, String code, String name) {
        this.skId = skId;
        this.code = code;
        this.name = name;
    }

    public static InstrSourceEnums getEnumByCode(String code) {
        InstrSourceEnums[] var1 = values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            InstrSourceEnums enums = var1[var3];
            if (enums.code.equalsIgnoreCase(code)) {
                return enums;
            }
        }

        return null;
    }

    public static String getDictTypeCode() {
        return TradeDictTypeCodeEnums.TRADE_INSTR_SOURCE.getCode();
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
