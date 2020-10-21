package fund.investment.infrastructure.instruction.domain.model.event;

import fund.investment.infrastructure.util.LoggerTemplate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.context.annotation.Profile;

@Data
@Builder
@AllArgsConstructor
@Profile(value = "event")
public class IstrCompletedEvt extends InstructionEvent {

    @Override
    public String toString() {
        return LoggerTemplate.builder()
                .CONTENT(this)
                .NAME(this.getClass().getSimpleName())
                .build()
                .toJson();
    }
}
