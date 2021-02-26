package fund.investment.gateway.api.book.event.instruction;

import fund.investment.gateway.api.book.event.VerificationEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author dongkw
 * @Date 2020/9/17、14:30
 **/
@Getter
@Setter
@NoArgsConstructor
public class IstrVerfUpdateFailedEvt extends VerificationEvent {

    private String istrId;

    public IstrVerfUpdateFailedEvt(String id, String istrId) {
        super(id);
        this.istrId = istrId;
    }
}
