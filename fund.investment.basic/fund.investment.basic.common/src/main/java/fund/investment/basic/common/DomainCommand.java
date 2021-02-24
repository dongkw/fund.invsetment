package fund.investment.basic.common;

import fund.investment.basic.common.util.LoggerTemplate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DomainCommand {

    @TargetAggregateIdentifier
    private String id;

    private Long requestId;

    public DomainCommand(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return LoggerTemplate.builder()
                .content(this)
                .name(this.getClass().getSimpleName())
                .build()
                .toJson();
    }
}
