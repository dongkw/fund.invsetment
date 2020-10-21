package fund.investment.infrastructure.instruction.domain.model.event;

import fund.investment.infrastructure.util.LoggerTemplate;
import org.springframework.context.annotation.Profile;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@Profile(value = "event")
public class IstrPendingEvt extends InstructionEvent{

	@Override
	public String toString() {
		return LoggerTemplate.builder()
				.CONTENT(this)
				.NAME(this.getClass().getSimpleName())
				.build()
				.toJson();
	}
}
