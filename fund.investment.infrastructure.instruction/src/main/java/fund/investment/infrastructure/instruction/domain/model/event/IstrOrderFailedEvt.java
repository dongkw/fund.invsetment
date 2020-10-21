package fund.investment.infrastructure.instruction.domain.model.event;

import fund.investment.infrastructure.util.LoggerTemplate;
import org.springframework.context.annotation.Profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Profile(value = "event")
@AllArgsConstructor
@NoArgsConstructor
public class IstrOrderFailedEvt extends InstructionEvent{

    private String orderId;

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
