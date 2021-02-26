package fund.investment.basic.common.enums.dict.instruction;

import fund.investment.basic.common.enums.TradeDictTypeCodeEnums;

/**
 * @author yufengbao
 * @date 2020/8/28 14:13
 */
public enum InstrIssudeSourceEnums{
    pledge("71970000000001", "1", "银行间质押式回购"),
    newbond("71970000000002", "2", "可转债"),
    bondresale("71970000000003", "3", "债券回售"),
    savings("71970000000004", "4", "存款"),
    deposit("71970000000005", "5", "存单"),
    bankbond("71970000000006", "6", "现券"),
    distribution("71970000000007", "7", "一级分销"),
    foreign("71970000000008", "8", "外汇额度管控"),
    ;

    private String skId;
    private String code;
    private String name;

    private InstrIssudeSourceEnums(String skId, String code, String name) {
        this.skId = skId;
        this.code = code;
        this.name = name;
    }

    public static InstrIssudeSourceEnums getEnumByCode(String code) {
        InstrIssudeSourceEnums[] var1 = values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            InstrIssudeSourceEnums enums = var1[var3];
            if (enums.code.equalsIgnoreCase(code)) {
                return enums;
            }
        }

        return null;
    }

    public static String getDictTypeCode() {
        return TradeDictTypeCodeEnums.TRADE_INSTR_ISSUED_SOURCE.getCode();
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
