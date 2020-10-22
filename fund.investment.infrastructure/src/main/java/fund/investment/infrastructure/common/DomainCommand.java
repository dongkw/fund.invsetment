package fund.investment.infrastructure.common;

import fund.investment.infrastructure.util.LoggerTemplate;
import lombok.Data;

@Data
public class DomainCommand {

    @Override
    public String toString() {
        return LoggerTemplate.builder()
                .CONTENT(this)
                .NAME(this.getClass().getSimpleName())
                .build()
                .toJson();
    }
}
