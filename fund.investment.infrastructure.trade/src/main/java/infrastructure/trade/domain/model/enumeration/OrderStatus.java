package infrastructure.trade.domain.model.enumeration;

public enum OrderStatus {

    CREATED("已创建"),
    CONFIRMED("已确认"),
    FAILED("废单"),
    PLACING("正报"),
    PLACED("已报"),
    CANCELLING("取消中"),
    CANCELLED("已取消"),
    PARTIAL_FILLED("部分成交"),
    PARTIAL_FILLED_CANCELLING("部分成交，其余正撤"),
    PARTIAL_FILLED_CANCELLED("部分成交，其余已撤"),
    COMPLETED("已完成");

    OrderStatus(String desc) {
        this.desc = desc;
    }

    private String desc;
}
