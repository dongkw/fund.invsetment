package fund.investment.infrastructure.instruction.domain.model.vo;

import lombok.Data;

@Data
public class OrderTradeElement {
	private String tradeType;
	private String securityCode;
	private Long quantity = 0L;

}
