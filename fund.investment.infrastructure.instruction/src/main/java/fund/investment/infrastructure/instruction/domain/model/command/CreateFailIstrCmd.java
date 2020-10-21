package fund.investment.infrastructure.instruction.domain.model.command;

import fund.investment.infrastructure.util.LoggerTemplate;import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Profile;

@Data
@Profile("command")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateFailIstrCmd extends InstructionCommand{

	private String failCode;

	private String failMsg;

	@Override
	public String toString() {
		return LoggerTemplate.builder()
				.CONTENT(this)
				.NAME(this.getClass().getSimpleName())
				.build()
				.toJson();
	}

}
