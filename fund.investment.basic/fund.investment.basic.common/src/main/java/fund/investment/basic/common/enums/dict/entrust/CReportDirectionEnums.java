package fund.investment.basic.common.enums.dict.entrust;

import fund.investment.common.bean.dict.TradeDictBaseEnums;
import fund.investment.common.enums.TradeDictTypeCodeEnums;

/**
 * 报价方向
 */
public enum CReportDirectionEnums implements TradeDictBaseEnums {
    //skId=71870000000001
    OUR("71870000000001", "1", "本方"),
    //skId=71870000000002
    RIVAL("71870000000002", "2", "对手方"),
    //skId=71870000000003
    MARKET("71870000000003", "3", "做市方"),
    ;

    private String skId;
    private String code;
    private String name;

    private CReportDirectionEnums(String skId, String code, String name) {
        this.skId = skId;
        this.code = code;
        this.name = name;
    }

    public static CReportDirectionEnums getEnumByCode(String code) {
        CReportDirectionEnums[] var1 = values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            CReportDirectionEnums enums = var1[var3];
            if (enums.code.equalsIgnoreCase(code)) {
                return enums;
            }
        }

        return null;
    }

    public static String getDictTypeCode() {
        return TradeDictTypeCodeEnums.TRADE_OFFER_DIRECTION.getCode();
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
