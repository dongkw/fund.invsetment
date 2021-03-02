package fund.investment.basic.common;

import fund.investment.basic.common.util.LoggerTemplate;
import lombok.*;

import java.util.Date;

@Getter
@Setter
public class DomainEvent {

    private Long id;
    private Long requestId;
    private String modifiedId;

    private Date modifiedTime;

    public void copyOf(DomainCommand command) {
        this.id = command.getId();
        this.modifiedId = command.getModifiedId();
        this.requestId = command.getRequestId();
        this.modifiedTime = command.getModifiedTime();
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
