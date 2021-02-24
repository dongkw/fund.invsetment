package fund.investment.basic.common.enums.dict.entrust;

import fund.investment.common.bean.dict.TradeDictBaseEnums;
import fund.investment.common.enums.TradeDictTypeCodeEnums;

/**
 * 委托来源
 */
public enum TradeEntrustSourceEnums implements TradeDictBaseEnums {
    //skId=71750000000000
    O32("71750000000000", "0", "O32"),
    //skId=71750000000001
    REMOTE_INS("71750000000001", "1", "远程客户端指令"),
    //skId=71750000000002
    REMOTE_ENTRUST("71750000000002", "2", "远程客户端委托"),
    //skId=71750000000003
    THR_INTER("71750000000003", "3", "第三方接口"),
    //skId=71750000000004
    FIX_INS("71750000000004", "4", "FIX指令"),
    //skId=71750000000005
    OUT_INTER("71750000000005", "5", "外围分仓接口"),
    ;

    private String skId;
    private String code;
    private String name;

    private TradeEntrustSourceEnums(String skId, String code, String name) {
        this.skId = skId;
        this.code = code;
        this.name = name;
    }

    public static TradeEntrustSourceEnums getEnumByCode(String code) {
        TradeEntrustSourceEnums[] var1 = values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            TradeEntrustSourceEnums enums = var1[var3];
            if (enums.code.equalsIgnoreCase(code)) {
                return enums;
            }
        }

        return null;
    }

    public static String getDictTypeCode() {
        return TradeDictTypeCodeEnums.TRADE_ENTRUST_SOURCE.getCode();
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
