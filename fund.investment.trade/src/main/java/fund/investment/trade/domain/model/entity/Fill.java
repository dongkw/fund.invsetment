package fund.investment.trade.domain.model.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Fill {
	
	private String id;
	
	private String orderOriginalId;
	
	private String orderExchangeId;
	
	private String instructionId;
	
	private String tradeType;
	
	private String securityCode;
	
	private LocalDateTime fillTime;
}
