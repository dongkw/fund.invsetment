package fund.investment.basic.common.enums.dict.instruction;


import fund.investment.common.bean.dict.TradeDictBaseEnums;
import fund.investment.common.enums.TradeDictTypeCodeEnums;

/**
 * 投资指令状态
 */
public enum InsStatusEnums implements TradeDictBaseEnums {
    //skId=70410000000001
    NEW("70410000000001", "1", "有效"),
    //skId=70410000000002
    MODIFYED("70410000000002", "2", "已修改"),
    //skId=70410000000003
    CANCELED("70410000000003", "3", "已撤销"),
    //skId=70410000000004
    STOPED("70410000000004", "4", "已暂停"),
    //skId=70410000000005
    AUDITREFUSE("70410000000005", "5", "审批拒绝"),
    //skId=70410000000006
    SPLITREFUSE("70410000000006", "6", "分发拒绝"),
    //skId=70410000000007
    INPUT("70410000000007", "7", "指令录入"),
    //skId=70410000000008
    FAIL("70410000000008", "8", "分仓失败"),
    //skId=70410000000009
    DRAFT("70410000000009", "9", "草稿指令"),
    //skId=70410000000010
    TEMPDIRECT("70410000000010", "a", "临时下达"),
    //skId=70410000000011
    TEMPMODIFY("70410000000011", "b", "临时修改"),
    //skId=70410000000012
    CANCELING("70410000000012", "c", "待撤销"),
    //skId=70410000000013
    FINISH("70410000000013", "d", "已成"),
    //skId=70410000000014
    ABANDON("70410000000014", "e", "已作废"),
    //skId=70410000000015
    NOPART("70410000000015", "f", "不参与"),
    //skId=70410000000016
    INSTFAIL("70410000000016", "g", "指令失败"),
    //skId=70410000000017
    BACK("70410000000017", "h", "指令退回"),
    ;

    private String skId;
    private String code;
    private String name;

    private InsStatusEnums(String skId, String code, String name) {
        this.skId = skId;
        this.code = code;
        this.name = name;
    }

    public static InsStatusEnums getEnumByCode(String code) {
        InsStatusEnums[] var1 = values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            InsStatusEnums enums = var1[var3];
            if (enums.code.equalsIgnoreCase(code)) {
                return enums;
            }
        }

        return null;
    }

    public static String getDictTypeCode() {
        return TradeDictTypeCodeEnums.TRADE_INS_STATUS.getCode();
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
