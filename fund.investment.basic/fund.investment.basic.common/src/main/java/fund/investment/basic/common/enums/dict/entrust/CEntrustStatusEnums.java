package fund.investment.basic.common.enums.dict.entrust;

import fund.investment.common.bean.dict.TradeDictBaseEnums;
import fund.investment.common.enums.TradeDictTypeCodeEnums;

/**
 * 委托状态
 */
public enum CEntrustStatusEnums implements TradeDictBaseEnums {
    //skId=71720000000001
    UNREPORT("71720000000001", "1", "未报"),
    //skId=71720000000002
    ENUM_2("71720000000002", "2", "待报，转换机已经读取记录"),
    //skId=71720000000003
    REPORTING("71720000000003", "3", "正报，已写到接口库"),
    //skId=71720000000004
    REPORTED("71720000000004", "4", "已报"),
    //skId=71720000000005
    WASTE("71720000000005", "5", "废单"),
    //skId=71720000000006
    PART_DEAL("71720000000006", "6", "部成"),
    //skId=71720000000007
    DEALED("71720000000007", "7", "已成"),
    //skId=71720000000008
    PART_CANCEL("71720000000008", "8", "部撤"),
    //skId=71720000000009
    CANCELED("71720000000009", "9", "已撤，是被撤的委托"),
    //skId=71720000000010
    WAITING_CANCEL("71720000000010", "a", "待撤，是被撤的委托"),
    //skId=71720000000011
    ENUM_A("71720000000011", "A", "未撤，是撤单委托的"),
    //skId=71720000000012
    CANCEL_WAITING_CANCEL("71720000000012", "B", "待撤，是撤单委托的"),
    //skId=71720000000013
    ENUM_C("71720000000013", "C", "正撤，是撤单委托的"),
    //skId=71720000000014
    ENUM_D("71720000000014", "D", "撤认，是撤单委托的"),
    //skId=71720000000015
    CANCEL_WASTE_CANCEL("71720000000015", "E", "撤废，是撤单委托的"),
    //skId=71720000000016
    CANCEL_CANCELED("71720000000016", "F", "已撤，是撤单委托的"),
    //skId=71720000000017
    ENUM_b("71720000000017", "b", "未审批,被撤委托和撤单委托都有"),
    //skId=71720000000018
    ENUM_c("71720000000018", "c", "审批拒绝，被撤委托和撤单委托都有"),
    //skId=71720000000019
    ENUM_d("71720000000019", "d", "未审批即撤销，是被撤销委托的"),
    //skId=71720000000020
    CONFIRMING("71720000000020", "e", "成交确认中"),
    //skId=71720000000021
    REJECTING("71720000000021", "f", "拒绝确认中"),
    //skId=71720000000022
    NEW_RECEIVE("71720000000022", "g", "新接收"),
    //skId=71720000000023
    MODIFYING("71720000000023", "h", "待修改"),
    //skId=71720000000024
    MODIFIED("71720000000024", "i", "已修改"),
    //skId=71720000000025
    DRAFT("71720000000025", "j", "草稿委托状态"),
    ;

    private String skId;
    private String code;
    private String name;

    private CEntrustStatusEnums(String skId, String code, String name) {
        this.skId = skId;
        this.code = code;
        this.name = name;
    }

    public static CEntrustStatusEnums getEnumByCode(String code) {
        CEntrustStatusEnums[] var1 = values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            CEntrustStatusEnums enums = var1[var3];
            if (enums.code.equalsIgnoreCase(code)) {
                return enums;
            }
        }

        return null;
    }

    public static String getDictTypeCode() {
        return TradeDictTypeCodeEnums.TRADE_ENTRUST_STATUS.getCode();
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
