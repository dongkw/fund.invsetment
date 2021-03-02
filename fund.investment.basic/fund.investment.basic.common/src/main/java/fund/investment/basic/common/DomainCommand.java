package fund.investment.basic.common;

import fund.investment.basic.common.util.LoggerTemplate;
import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.Date;

@Getter
@Setter
public class DomainCommand {

    @TargetAggregateIdentifier
    private Long id;

    private Long requestId;

    private String modifiedId;

    private Date modifiedTime;

    @Override
    public String toString() {
        return LoggerTemplate.builder()
                .content(this)
                .name(this.getClass().getSimpleName())
                .build()
                .toJson();
    }

    public void copyOf(DomainEvent event) {
        this.id = event.getId();
        this.modifiedId = event.getModifiedId();
        this.requestId = event.getRequestId();
        this.modifiedTime = event.getModifiedTime();
    }
}
