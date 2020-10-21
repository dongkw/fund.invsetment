package fund.investment.infrastructure.instruction.domain.model.enumeration;

/**
 * 审核状态
 * @author 芳军
 *
 */
public enum ApprovalStatus {
	UNDEFINED("-1", "空"),
	WAIT("0", "待审批"),
	PASS("1", "审批通过"),
	REJECT("2", "拒绝审批")
	;
	ApprovalStatus(String id, String note) {
		this.id = id;
		this.note = note;
	}
	
	private String id;
	private String note;
}
