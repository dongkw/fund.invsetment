package fund.investment.infrastructure.common;

import fund.investment.infrastructure.util.LoggerTemplate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DomainEvent {

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
