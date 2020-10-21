package fund.investment.trade.domain.model.aggregate.state;

public enum OrderStatus {
	CREATED("1", "已创建"),
	CONFIRMED("21", "已确认"),
	FAILED("3", "废单"),
	PLACING("4", "正报"),
	PLACED("5", "已报"),
	CANCELLING("6", "撤销中"),
	PARTIAL_FAILED("7", "部分成交"),
	PARTIAL_FAILED_CANCELLING("8", "部分成交其余取消中"),
	PARTIAL_FAILED_CANCELLED("9","部分成交剩余已取消"),
	COMPLETED("10", "已完成"),
	CANCELLED("11", "撤销")
	;
	OrderStatus(String id, String desc) {
		this.id = id;
		this.desc = desc;
	}
	
	private String id;
	private String desc;
	
	public String statusId() {
		return id;
	}
}
