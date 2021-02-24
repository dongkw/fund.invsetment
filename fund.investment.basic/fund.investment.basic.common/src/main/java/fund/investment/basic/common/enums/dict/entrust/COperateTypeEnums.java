package fund.investment.basic.common.enums.dict.entrust;

import fund.investment.common.bean.dict.TradeDictBaseEnums;
import fund.investment.common.enums.TradeDictTypeCodeEnums;

/**
 * 操作类别
 */
public enum COperateTypeEnums implements TradeDictBaseEnums {
    //skId=71880000000001
    NEW("71880000000001", "1", "新增"),
    //skId=71880000000002
    MODIFY("71880000000002", "2", "修改"),
    //skId=71880000000003
    DEAL("71880000000003", "3", "成交确认"),
    //skId=71880000000004
    REJECT("71880000000004", "4", "报价拒绝"),
    //skId=71880000000005
    CONVERSATION("71880000000005", "5", "交谈"),
    ;

    private String skId;
    private String code;
    private String name;

    private COperateTypeEnums(String skId, String code, String name) {
        this.skId = skId;
        this.code = code;
        this.name = name;
    }

    public static COperateTypeEnums getEnumByCode(String code) {
        COperateTypeEnums[] var1 = values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            COperateTypeEnums enums = var1[var3];
            if (enums.code.equalsIgnoreCase(code)) {
                return enums;
            }
        }

        return null;
    }

    public static String getDictTypeCode() {
        return TradeDictTypeCodeEnums.TRADE_OPERATE_TYPE.getCode();
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
