package fund.investment.basic.common.exception;

import fund.investment.common.DomainEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExceptionEvent extends DomainEvent {
    public ExceptionEvent(String id, String payloadType, Object payload, String errorMsg){
        super(id);
        this.payload = payload;
        this.payloadType = payloadType;
        this.errorMsg = errorMsg;
    }
    private String payloadType;
    private Object payload;
    private String errorMsg;
}
