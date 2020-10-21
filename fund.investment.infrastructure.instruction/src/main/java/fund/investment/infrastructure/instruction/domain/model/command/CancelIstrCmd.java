package fund.investment.infrastructure.instruction.domain.model.command;

import fund.investment.infrastructure.util.LoggerTemplate;import fund.investment.infrastructure.instruction.domain.model.vo.Order;
import org.springframework.context.annotation.Profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Profile(value = "command")
public class CancelIstrCmd extends InstructionCommand {

	private String cancelMsg;

	private String CancelType;

	private List<Order> orders;

	@Override
	public String toString() {
		return LoggerTemplate.builder()
				.CONTENT(this)
				.NAME(this.getClass().getSimpleName())
				.build()
				.toJson();
	}

}
