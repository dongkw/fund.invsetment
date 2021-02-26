package fund.investment.basic.inquiry.api.enumeration;

/**
 * 指令状态
 *
 * @author 芳军
 */
public enum InquiryStatus {

    CREATED(0, "已创建"),
    CONFIRMED(1, "已确认"),
    CANCELLED(6, "已撤销"),
    COMPLETED(7, "已完成"),
    FAILED(9, "创建失败"),
    CANCELLING(10, "撤销中");

    InquiryStatus(int id, String note) {
        this.id = id;
        this.note = note;
    }

    private int id;

    private String note;
}
