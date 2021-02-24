package fund.investment.basic.common.enums.dict.inqresult;

import fund.investment.common.bean.dict.TradeDictBaseEnums;
import fund.investment.common.enums.TradeDictTypeCodeEnums;

/**
 * 询价结果状态
 */
public enum InqResultStatusEnums implements TradeDictBaseEnums {
    NEW("71860000000001", "1", "有效"),
    INVAILD("71860000000002", "2", "无效"),
    DRAFT("71860000000003", "3", "草稿"),
    RECALL("71860000000004", "4", "已回溯"),
    TEMPMODIFY("71860000000005", "5", "临时修改"),
    CANCELED("71860000000006", "6", "已撤销"),
    CANCELLATION("71860000000007", "7", "已作废"),
    BACK("71860000000008", "8", "退回"),
    FINISH("71860000000009", "9", "已下达"),
    WAITINGCOMMIT("71860000000010", "10", "待提交");

    private String skId;
    private String code;
    private String name;

    private InqResultStatusEnums(String skId, String code, String name) {
        this.skId = skId;
        this.code = code;
        this.name = name;
    }

    public static InqResultStatusEnums getEnumByCode(String code) {
        InqResultStatusEnums[] var1 = values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            InqResultStatusEnums enums = var1[var3];
            if (enums.code.equalsIgnoreCase(code)) {
                return enums;
            }
        }

        return null;
    }

    public static String getDictTypeCode() {
        return TradeDictTypeCodeEnums.TRADE_INQUIRE_RESULT_STATUS.getCode();
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
