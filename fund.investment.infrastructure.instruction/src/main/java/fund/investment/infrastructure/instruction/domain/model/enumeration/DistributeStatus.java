package fund.investment.infrastructure.instruction.domain.model.enumeration;

import lombok.Getter;

/**
 * 审核状态
 *
 * @author 芳军
 */
@Getter
public enum DistributeStatus {

    UNDEFINED(0, "空"),
    WAIT(1, "待分发"),
    PASS(2, "已分发"),
    REJECT(3, "拒绝分发");

    DistributeStatus(int id, String note) {
        this.id = id;
        this.note = note;
    }

    private int id;

    private String note;
}
