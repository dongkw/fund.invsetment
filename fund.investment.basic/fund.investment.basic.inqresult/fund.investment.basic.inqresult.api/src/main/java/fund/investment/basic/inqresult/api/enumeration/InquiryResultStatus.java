package fund.investment.basic.inqresult.api.enumeration;

/**
 * 指令状态
 *
 * @author 芳军
 */
public enum InquiryResultStatus {

    CREATED(0, "已创建"),
    CONFIRMED(1, "已确认"),
    CANCELLED(6, "已撤销"),
    COMPLETED(7, "已完成"),
    FAILED(9, "创建失败"),
    CANCELLING(10, "撤销中"),
    COMMIT(11, "已提交"),
    REJECT(12, "拒绝");

    InquiryResultStatus(int id, String note) {
        this.id = id;
        this.note = note;
    }

    private int id;

    private String note;
}
