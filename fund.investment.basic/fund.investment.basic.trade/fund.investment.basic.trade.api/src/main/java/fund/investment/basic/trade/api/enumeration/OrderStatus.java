package fund.investment.basic.trade.api.enumeration;

public enum OrderStatus {

    CREATED("已创建"),
    CONFIRMED("已确认"),
    FAILED("废单"),
    PLACING("正报"),
    PLACED("已报"),
    NEW_REC("新接收"),
    CANCELLING("取消中"),
    CANCELLED("已取消"),
    DELETED("已删除"),
    PARTIAL_FILLED("部分成交"),
    PARTIAL_FILLED_CANCELLING("部分成交，其余正撤"),
    PARTIAL_FILLED_CANCELLED("部分成交，其余已撤"),
    CONFIRMING("成交确认中"),
    REJECT_CONFIRMING("拒绝确认中"),
    COMPLETED("已完成");

    OrderStatus(String desc) {
        this.desc = desc;
    }

    private String desc;
}
