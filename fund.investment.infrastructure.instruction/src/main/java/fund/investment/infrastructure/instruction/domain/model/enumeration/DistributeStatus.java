package fund.investment.infrastructure.instruction.domain.model.enumeration;

/**
 * 审核状态
 * @author 芳军
 *
 */
public enum DistributeStatus {
	UNDIFINED("-1", "空"),
	WAIT("0", "待分发"),
	PASS("1", "已分发"),
	REJECT("2", "拒绝分发")
	;
	DistributeStatus(String id, String note) {
		this.id = id;
		this.note = note;
	}
	
	private String id;
	private String note;
}
