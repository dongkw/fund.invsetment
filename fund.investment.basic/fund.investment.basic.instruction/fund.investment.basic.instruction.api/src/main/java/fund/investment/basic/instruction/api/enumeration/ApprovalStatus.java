package fund.investment.basic.instruction.api.enumeration;

import lombok.Getter;

/**
 * 审核状态
 *
 * @author 芳军
 */
@Getter
public enum ApprovalStatus {

    UNDEFINED(0, "空"),
    WAIT(1, "待审批"),
    PASS(2, "审批通过"),
    REJECT(3, "拒绝审批");

    ApprovalStatus(int id, String note) {
        this.id = id;
        this.note = note;
    }

    private int id;

    private String note;
}
