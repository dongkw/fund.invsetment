package fund.investment.infrastructure.instruction.domain.model.enumeration;

/**
 * 成交状态
 * @author 芳军
 *
 */
public enum FillStatus {
	UNDEFINED("0", "未成交"),
	PART_ENTRUSTED("1", "部分成交"),
	ALL_ENTRUSTED("2", "全部成交");
	FillStatus(String id, String note) {
		this.id = id;
		this.note = note;
	}
	
	private String id;
	private String note;
}
