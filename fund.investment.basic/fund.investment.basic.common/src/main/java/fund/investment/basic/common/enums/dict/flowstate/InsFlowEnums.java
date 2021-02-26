package fund.investment.basic.common.enums.dict.flowstate;

import fund.investment.basic.common.enums.TradeDictTypeCodeEnums;

/**
 * 流程操作类型
 */
public enum InsFlowEnums{
    //skId=70440000000001
    INPUT("70440000000001", "0", "指令下达"),
    //skId=70440000000002
    SPLIT("70440000000002", "1", "指令分发"),
    //skId=70440000000003
    MODIFY("70440000000003", "2", "指令修改"),
    //skId=70440000000004
    INSAUDIT("70440000000004", "3", "指令审批"),
    //skId=70440000000005
    INSREAUDIT("70440000000005", "4", "指令复核"),
    //skId=70440000000006
    CANCEL("70440000000006", "5", "指令撤销"),
    //skId=70440000000007
    STOP("70440000000007", "6", "指令暂停"),
    //skId=70440000000008
    MAIL("70440000000008", "7", "邮件通知"),
    //skId=70440000000009
    MATCH("70440000000009", "8", "自动匹配"),
    //skId=70440000000010
    HANDMACTH("70440000000010", "9", "手工匹配"),
    //skId=70440000000011
    PROXY("70440000000011", "10", "指令代下"),
    //skId=70440000000012
    EXE("70440000000012", "11", "指令成交执行"),
    //skId=70440000000013
    ORDERED("70440000000013", "12", "已缴纳定金"),
    //skId=70440000000014
    REGISTER("70440000000014", "13", "登记确认"),
    //skId=70440000000015
    DEPOSIT("70440000000015", "14", "保证金划款确认"),
    //skId=70440000000016
    TRANSFER("70440000000016", "15", "补缴款划款确认"),
    //skId=70440000000017
    FIXSTATUS("70440000000017", "16", "FIX指令对接状态"),
    //skId=70440000000018
    BACK("70440000000018", "17", "指令退回"),
    //skId=70440000000052
    SAVE("70440000000052", "52", "保存流程"),
    //skId=70440000000053
    START("70440000000053", "53", "发起流程"),
    //skId=70440000000054
    RESTART("70440000000054", "54", "重推流程"),
    //skId=70440000000055
    ADUIT("70440000000055", "55", "审批"),
    ;

    private String skId;
    private String code;
    private String name;

    private InsFlowEnums(String skId, String code, String name) {
        this.skId = skId;
        this.code = code;
        this.name = name;
    }

    public static InsFlowEnums getEnumByCode(String code) {
        InsFlowEnums[] var1 = values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            InsFlowEnums enums = var1[var3];
            if (enums.code.equalsIgnoreCase(code)) {
                return enums;
            }
        }

        return null;
    }

    public static String getDictTypeCode() {
        return TradeDictTypeCodeEnums.TRADE_INS_FLOW.getCode();
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
