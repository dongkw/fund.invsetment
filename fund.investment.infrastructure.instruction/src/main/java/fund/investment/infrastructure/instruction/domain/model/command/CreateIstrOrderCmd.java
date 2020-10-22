package fund.investment.infrastructure.instruction.domain.model.command;

import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeType;
import fund.investment.infrastructure.instruction.domain.model.vo.OrderTradeElement;
import fund.investment.infrastructure.util.LoggerTemplate;
import lombok.*;
import org.springframework.context.annotation.Profile;

@Getter
@Setter
@NoArgsConstructor
public class CreateIstrOrderCmd extends InstructionCommand{

	private String orderId;

	private OrderTradeElement orderTradeElement;

	public CreateIstrOrderCmd(String id, TradeType tradeType, String orderId, OrderTradeElement orderTradeElement) {
		super(id, tradeType);
		this.orderId = orderId;
		this.orderTradeElement = orderTradeElement;
	}
}
