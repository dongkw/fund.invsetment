package fund.investment.infrastructure.instruction.domain.model.enumeration;

/**
 * 委托状态
 * @author 芳军
 *
 */
public enum OrderStatus {
	UNDIFINED("0", "未委托"),
	PART_ENTRUSTED("1", "部分委托"),
	ALL_ENTRUSTED("2", "全部委托");
	OrderStatus(String id, String note) {
		this.id = id;
		this.note = note;
	}
	
	private String id;
	private String note;
}
