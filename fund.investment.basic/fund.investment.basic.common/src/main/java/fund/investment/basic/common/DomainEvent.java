package fund.investment.basic.common;

import fund.investment.basic.common.util.LoggerTemplate;
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
    private Long requestId;

    public DomainEvent(String id) {
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
