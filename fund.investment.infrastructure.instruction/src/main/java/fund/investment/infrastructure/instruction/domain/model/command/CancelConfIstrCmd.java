package fund.investment.infrastructure.instruction.domain.model.command;

import fund.investment.infrastructure.util.LoggerTemplate;import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Profile;

/**
 * @Author dongkw
 * @Date 2020/10/10、9:29 上午
 **/
@Data
@Builder
@NoArgsConstructor
@Profile(value = "command")
public class CancelConfIstrCmd extends InstructionCommand{

    @Override
    public String toString() {
        return LoggerTemplate.builder()
                .CONTENT(this)
                .NAME(this.getClass().getSimpleName())
                .build()
                .toJson();
    }

}
