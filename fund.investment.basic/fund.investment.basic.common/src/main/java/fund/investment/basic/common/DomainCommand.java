package fund.investment.basic.common;

import fund.investment.basic.common.util.LoggerTemplate;
import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class DomainCommand {

    private Long id;
    @TargetAggregateIdentifier
    private Long aggregateId;

    private Long requestId;

    private String regId;

    private long timestamp;


    @Override
    public String toString() {
        return LoggerTemplate.builder()
                .content(this)
                .name(this.getClass().getSimpleName())
                .build()
                .toJson();
    }

    public void copyOf(DomainEvent event) {
//        this.id = event.getId();
        this.aggregateId = event.getAggregateId();
        this.timestamp = System.currentTimeMillis();
        this.requestId = event.getRequestId();
        this.regId = event.getRegId();
    }
}
