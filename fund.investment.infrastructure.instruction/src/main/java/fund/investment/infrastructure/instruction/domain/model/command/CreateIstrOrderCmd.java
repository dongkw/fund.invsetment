package fund.investment.infrastructure.instruction.domain.model.command;

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

	@Override
	public String toString() {
		return LoggerTemplate.builder()
				.content(this)
				.name(this.getClass().getSimpleName())
				.build()
				.toJson();
	}

}
