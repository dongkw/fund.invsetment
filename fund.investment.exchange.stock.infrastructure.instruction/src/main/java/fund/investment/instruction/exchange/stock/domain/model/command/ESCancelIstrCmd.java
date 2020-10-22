package fund.investment.instruction.exchange.stock.domain.model.command;

import fund.investment.infrastructure.util.LoggerTemplate;import fund.investment.infrastructure.instruction.domain.model.command.CancelIstrCmd;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@NoArgsConstructor
@Slf4j
public class ESCancelIstrCmd extends CancelIstrCmd {

    @Override
    public String toString() {
        return LoggerTemplate.builder()
                .content(this)
                .name(this.getClass().getSimpleName())
                .build()
                .toJson();
    }
}
