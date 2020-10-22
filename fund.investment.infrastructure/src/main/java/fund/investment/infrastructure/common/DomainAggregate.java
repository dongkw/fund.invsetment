package fund.investment.infrastructure.common;

import org.axonframework.modelling.command.AggregateIdentifier;

import fund.investment.infrastructure.util.LoggerTemplate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DomainAggregate {

    @AggregateIdentifier
    private String id;

    @Override
    public String toString() {
        return LoggerTemplate.builder()
                             .content(this)
                             .name(this.getClass().getSimpleName())
                             .build()
                             .toJson();
    }
}
