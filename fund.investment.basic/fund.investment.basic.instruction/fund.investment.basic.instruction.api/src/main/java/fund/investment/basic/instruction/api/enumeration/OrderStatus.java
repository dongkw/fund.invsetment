package fund.investment.basic.instruction.api.enumeration;

/**
 * 委托状态
 *
 * @author 芳军
 */
public enum OrderStatus {

    UNDEFINED(0, "未委托"),
    PART_ENTRUSTED(1, "部分委托"),
    ALL_ENTRUSTED(2, "全部委托");

    OrderStatus(int id, String note) {
        this.id = id;
        this.note = note;
    }

    private int id;

    private String note;
}
