package fund.investment.basic.common.enums.dict.flowstate;

import fund.investment.common.bean.dict.TradeDictBaseEnums;
import fund.investment.common.enums.TradeDictTypeCodeEnums;

/**
 * 流程操作状态
 */
public enum InsFlowStatusEnums implements TradeDictBaseEnums {
    //skId=70450000000001
    INPUT("70450000000001", "0", "已参与"),
    //skId=70450000000002
    UNSPLIT("70450000000002", "1", "未分发"),
    //skId=70450000000003
    SPLITED("70450000000003", "2", "已分发"),
    //skId=70450000000004
    UNAUDIT("70450000000004", "3", "未审批"),
    //skId=70450000000005
    AUDITLESS("70450000000005", "4", "无需审批"),
    //skId=70450000000006
    AUDITPASS("70450000000006", "5", "审批通过"),
    //skId=70450000000007
    AUDITREFUSE("70450000000007", "6", "审批拒绝"),
    //skId=70450000000008
    MODIFYED("70450000000008", "7", "已修改"),
    //skId=70450000000009
    CANCELED("70450000000009", "8", "已撤销"),
    //skId=70450000000010
    STOPED("70450000000010", "9", "指令暂停"),
    //skId=70450000000011
    MSGED("70450000000011", "10", "已通知"),
    //skId=70450000000012
    AUTOMATCH("70450000000012", "11", "自动匹配"),
    //skId=70450000000013
    HANDMATCH("70450000000013", "12", "手工匹配"),
    //skId=70450000000014
    PROXYINPUT("70450000000014", "13", "指令代下"),
    //skId=70450000000015
    UNEXE("70450000000015", "14", "未执行"),
    //skId=70450000000016
    PARTEXE("70450000000016", "15", "部分执行"),
    //skId=70450000000017
    FINISHED("70450000000017", "16", "完成"),
    //skId=70450000000018
    SPLITREFUSE("70450000000018", "17", "分发拒绝"),
    //skId=70450000000019
    PAYMENT("70450000000019", "18", "申购-缴纳定金"),
    //skId=70450000000020
    UNMOFIY("70450000000020", "19", "未修改"),
    //skId=70450000000021
    MODIFYING("70450000000021", "20", "修改中"),
    //skId=70450000000022
    CANCELING("70450000000022", "21", "待撤销"),
    //skId=70450000000023
    UNCACNEL("70450000000023", "22", "未撤销"),
    //skId=70450000000024
    UNATTEND("70450000000024", "23", "未参与"),
    //skId=70450000000025
    CONFIRM("70450000000025", "24", "已确认"),
    //skId=70450000000026
    UNCONFIRM("70450000000026", "25", "未确认"),
    //skId=70450000000027
    UNDELIEVER("70450000000027", "26", "未发送"),
    //skId=70450000000028
    DELIEVERSUCCESS("70450000000028", "27", "发送成功"),
    //skId=70450000000029
    DELIEVERFAIL("70450000000029", "28", "发送失败"),
    //skId=70450000000030
    DELIEVERING("70450000000030", "29", "发送中"),
    //skId=70450000000031
    WAITINGEXAM("70450000000031", "30", "待审批"),
    //skId=70450000000032
    NONATTEND("70450000000032", "31", "不参与"),
    //skId=70450000000033
    FLASHBACK("70450000000033", "32", "已回溯"),
    //skId=70450000000034
    BACK("70450000000034", "33", "指令退回"),
    //skId=70450000000055
    UNINPUT("70450000000055", "0000000055", "未发起"),
    //skId=70450000000056
    DRAFT("70450000000056", "0000000056", "草稿"),
    //skId=70450000000057
    ASKING("70450000000057", "0000000057", "申请中"),
    //skId=70450000000058
    AUDITBACK("70450000000058", "0000000058", "审批退回"),
    //skId=70450000000059
    AUDITED("70450000000059", "0000000059", "审批通过"),
    //skId=70450000000060
    DELETED("70450000000060", "0000000050", "流程已删除"),
    ;

    private String skId;
    private String code;
    private String name;

    private InsFlowStatusEnums(String skId, String code, String name) {
        this.skId = skId;
        this.code = code;
        this.name = name;
    }

    public static InsFlowStatusEnums getEnumByCode(String code) {
        InsFlowStatusEnums[] var1 = values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            InsFlowStatusEnums enums = var1[var3];
            if (enums.code.equalsIgnoreCase(code)) {
                return enums;
            }
        }

        return null;
    }

    public static String getDictTypeCode() {
        return TradeDictTypeCodeEnums.TRADE_INS_FLOW_STATUS.getCode();
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
