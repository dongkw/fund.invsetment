package fund.investment.basic.common.enums.dict.entrust;

import fund.investment.basic.common.enums.TradeDictTypeCodeEnums;

/**
 * 委托方式
 */
public enum TradeEntrustTypeEnums {
    //skId=71740000000001
    ENUM_1("71740000000001", "1", "普通委托"),
    //skId=71740000000002
    ENUM_2("71740000000002", "2", "自动交易"),
    //skId=71740000000003
    ENUM_3("71740000000003", "3", "公平委托"),
    //skId=71740000000004
    ENUM_4("71740000000004", "4", "批量委托"),
    //skId=71740000000005
    ENUM_5("71740000000005", "5", "见谁灭谁"),
    //skId=71740000000006
    ENUM_6("71740000000006", "6", "快速委托"),
    //skId=71740000000007
    ENUM_7("71740000000007", "7", "境内算法交易"),
    //skId=71740000000008
    ENUM_8("71740000000008", "8", "对方发起"),
    //skId=71740000000009
    ENUM_9("71740000000009", "9", "补单"),
    //skId=71740000000010
    ENUM_A("71740000000010", "A", "组合交易"),
    //skId=71740000000011
    ENUM_B("71740000000011", "B", "境外算法交易"),
    //skId=71740000000012
    ENUM_C("71740000000012", "C", "被动买卖证券"),
    //skId=71740000000013
    ENUM_D("71740000000013", "D", "套利交易"),
    //skId=71740000000014
    ENUM_E("71740000000014", "E", "套保交易"),
    //skId=71740000000015
    ENUM_F("71740000000015", "F", "投机交易"),
    //skId=71740000000016
    ENUM_G("71740000000016", "G", "三方交易接口"),
    //skId=71740000000017
    ENUM_H("71740000000017", "H", "意向申报"),
    //skId=71740000000018
    ENUM_I("71740000000018", "I", "定价申报"),
    //skId=71740000000019
    ENUM_J("71740000000019", "J", "成交申报"),
    //skId=71740000000020
    ENUM_K("71740000000020", "K", "自动平仓"),
    //skId=71740000000021
    ENUM_L("71740000000021", "L", "止盈止损"),
    //skId=71740000000022
    ENUM_M("71740000000022", "M", "极速交易"),
    //skId=71740000000023
    ENUM_N("71740000000023", "N", "远程客户端"),
    //skId=71740000000024
    ENUM_O("71740000000024", "O", "算法交易母单"),
    //skId=71740000000025
    ENUM_P("71740000000025", "P", "收盘价定价申报"),
    //skId=71740000000026
    ENUM_Q("71740000000026", "Q", "日均价定价申报"),
    //skId=71740000000027
    ENUM_R("71740000000027", "R", "股指期权做市申报"),
    //skId=71740000000028
    ENUM_S("71740000000028", "S", "股票期权套利"),
    //skId=71740000000029
    ENUM_T("71740000000029", "T", "期权闪电下单"),
    ;

    private String skId;
    private String code;
    private String name;

    private TradeEntrustTypeEnums(String skId, String code, String name) {
        this.skId = skId;
        this.code = code;
        this.name = name;
    }

    public static TradeEntrustTypeEnums getEnumByCode(String code) {
        TradeEntrustTypeEnums[] var1 = values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            TradeEntrustTypeEnums enums = var1[var3];
            if (enums.code.equalsIgnoreCase(code)) {
                return enums;
            }
        }

        return null;
    }

    public static String getDictTypeCode() {
        return TradeDictTypeCodeEnums.TRADE_ENTRUST_TYPE.getCode();
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
