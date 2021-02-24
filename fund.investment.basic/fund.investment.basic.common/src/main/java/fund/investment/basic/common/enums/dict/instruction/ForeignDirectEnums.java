package fund.investment.basic.common.enums.dict.instruction;

import fund.investment.common.bean.dict.TradeDictBaseEnums;
import fund.investment.common.enums.TradeDictTypeCodeEnums;

/**
 * @author yufengbao
 * @date 2020/9/17 15:16
 */
public enum ForeignDirectEnums implements TradeDictBaseEnums {
    INRMBTOINUSD("71960000000001", "1", "境内RMB-境内USD"),
    INUSDTOINRMB("71960000000002", "2", "境内USD-境内RMB"),
    INUSDTOOUTUSD("71960000000003", "3", "境内USD-境外USD"),
    OUTUSDTOINUSD("71960000000004", "4", "境外USD-境内USD"),
    INRMBTOOUTRMB("71960000000005", "5", "境内RMB-境外RMB"),
    OUTRMBTOINRMB("71960000000006", "6", "境外RMB-境内RMB"),
    ;

    private String skId;
    private String code;
    private String name;

    private ForeignDirectEnums(String skId, String code, String name) {
        this.skId = skId;
        this.code = code;
        this.name = name;
    }

    public static ForeignDirectEnums getEnumByCode(String code) {
        ForeignDirectEnums[] var1 = values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            ForeignDirectEnums enums = var1[var3];
            if (enums.code.equalsIgnoreCase(code)) {
                return enums;
            }
        }

        return null;
    }

    public static String getDictTypeCode() {
        return TradeDictTypeCodeEnums.TRADE_TRAN_DIRECT.getCode();
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
