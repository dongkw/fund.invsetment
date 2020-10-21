package fund.investment.infrastructure.instruction.domain.model.event;

import fund.investment.infrastructure.util.LoggerTemplate;import fund.investment.infrastructure.instruction.domain.model.vo.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Profile;

import java.util.List;

/**
 * @Author dongkw
 * @Date 2020/10/10、10:08 上午
 **/
@Data
@Builder
@Profile(value = "event")
@AllArgsConstructor
@NoArgsConstructor
public class IstrCancellingEvt extends InstructionEvent {
    private String unitId;
    private String securityCode;

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
