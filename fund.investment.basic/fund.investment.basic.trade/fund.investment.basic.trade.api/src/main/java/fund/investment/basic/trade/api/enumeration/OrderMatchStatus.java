package fund.investment.basic.trade.api.enumeration;

public enum OrderMatchStatus {

    UNMATCH("未匹配"),
    NONEED("无需匹配"),
    AUTO("自动匹配"),
    CANCELED("撤销匹配"),
    HAND_MATCH("手工匹配"),
    ;

    OrderMatchStatus(String desc) {
        this.desc = desc;
    }

    private String desc;
}
