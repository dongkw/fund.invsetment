package fund.investment.basic.instruction.api.enumeration;

/**
 * 成交状态
 *
 * @author 芳军
 */
public enum FillStatus {

    UNDEFINED(0, "未成交"),
    PART_ENTRUSTED(1, "部分成交"),
    ALL_ENTRUSTED(2, "全部成交");

    FillStatus(int id, String note) {
        this.id = id;
        this.note = note;
    }

    private int id;

    private String note;
}
