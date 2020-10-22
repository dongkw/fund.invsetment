package fund.investment.infrastructure.common;

import fund.investment.infrastructure.util.LoggerTemplate;

public class DomainAggregate {
    @Override
    public String toString() {
        return LoggerTemplate.builder()
                .CONTENT(this)
                .NAME(this.getClass().getSimpleName())
                .build()
                .toJson();
    }
}
