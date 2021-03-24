package fund.investment.basic.common;

import fund.investment.basic.common.util.LoggerTemplate;
import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.Date;

@Getter
@Setter
public class DomainEvent {

    private Long id;

    private Long aggregateId;

    private Long requestId;

    private String regId;

    private long timestamp;

    public void copyOf(DomainCommand command) {
//        this.id = event.getId();
        this.aggregateId = command.getAggregateId();
        this.timestamp = System.currentTimeMillis();
        this.requestId = command.getRequestId();
        this.regId = command.getRegId();
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
