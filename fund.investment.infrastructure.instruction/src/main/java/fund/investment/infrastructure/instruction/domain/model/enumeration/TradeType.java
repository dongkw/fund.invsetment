package fund.investment.infrastructure.instruction.domain.model.enumeration;

/**
 * 交易类型
 * @author 芳军
 *
 */
public enum TradeType {
	//TODO
	UNDIFINED("0", "未定义"),
	EXCHANGE_STOCKE("1", "场内股票交易"),
	EXCHANGE_BOND("2", "场内现券买卖"),
	INNNERBANK_BOND("3", "银行间现券买卖"),
	INNERBANK_REPO("4", "银行间质押式回购")
	;
	
	TradeType(String id, String note) {
		this.id = id;
		this.note = note;
	}
	private String id;
	private String note;
}
