package fund.investment.basic.common.enums.dict.entrust;

import fund.investment.common.bean.dict.TradeDictBaseEnums;
import fund.investment.common.enums.TradeDictTypeCodeEnums;

/**
 * 报价状态
 */
public enum COfferStatusEnums implements TradeDictBaseEnums {
    //skId=71840000000005
    REJECT("71840000000005", "5", "拒绝"),
    //skId=71840000000016
    NORMAL("71840000000016", "16", "正常"),
    //skId=71840000000019
    CANCEL("71840000000019", "19", "撤销"),
    //skId=71840000000021
    ENUM_4("71840000000021", "21", "过期"),
    //skId=71840000000107
    All_DEAL("71840000000107", "107", "全部成交"),
    //skId=71840000000999
    BANK_REPORT_REFUSE("71840000000999", "999", "外汇交易中心拒绝"),
    ;

    private String skId;
    private String code;
    private String name;

    private COfferStatusEnums(String skId, String code, String name) {
        this.skId = skId;
        this.code = code;
        this.name = name;
    }

    public static COfferStatusEnums getEnumByCode(String code) {
        COfferStatusEnums[] var1 = values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            COfferStatusEnums enums = var1[var3];
            if (enums.code.equalsIgnoreCase(code)) {
                return enums;
            }
        }

        return null;
    }

    public static String getDictTypeCode() {
        return TradeDictTypeCodeEnums.TRADE_OFFER_STATIS.getCode();
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
