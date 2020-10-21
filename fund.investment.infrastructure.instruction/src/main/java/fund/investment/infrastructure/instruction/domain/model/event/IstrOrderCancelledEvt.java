package fund.investment.infrastructure.instruction.domain.model.event;

import fund.investment.infrastructure.util.LoggerTemplate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Profile;

import lombok.Data;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Profile(value = "event")
public class IstrOrderCancelledEvt extends InstructionEvent {

    private String orderId;

    private Long cancelQuantity;

    @Override
    public String toString() {
        return LoggerTemplate.builder()
                .CONTENT(this)
                .NAME(this.getClass().getSimpleName())
                .build()
                .toJson();
    }

}
