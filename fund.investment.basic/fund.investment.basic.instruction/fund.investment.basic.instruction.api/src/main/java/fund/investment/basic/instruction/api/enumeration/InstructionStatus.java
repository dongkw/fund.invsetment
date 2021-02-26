package fund.investment.basic.instruction.api.enumeration;

/**
 * 指令状态
 *
 * @author 芳军
 */
public enum InstructionStatus {

    CREATED(0, "已创建"),
    CONFIRMED(1, "已确认"),
    APRV_PASS(2, "审批通过"),
    APRV_REJECTED(3, "审批通过"),
    PENDING(4, "待执行"),
    EXECUTING(5, "执行中"),
    CANCELLED(6, "已撤销"),
    COMPLETED(7, "已完成"),
    CLOSED(8, "已关闭"),
    FAILED(9, "创建失败"),
    CANCELLING(10, "撤销中");

    InstructionStatus(int id, String note) {
        this.id = id;
        this.note = note;
    }

    private int id;

    private String note;
}
