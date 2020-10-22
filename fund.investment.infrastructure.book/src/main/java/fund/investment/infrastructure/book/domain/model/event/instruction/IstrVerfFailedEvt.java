package fund.investment.infrastructure.book.domain.model.event.instruction;

import fund.investment.infrastructure.book.domain.model.event.VerificationEvent;
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
public class IstrVerfFailedEvt extends VerificationEvent {

    private String istrId;

    public IstrVerfFailedEvt(String id, String istrId) {
        super(id);
        this.istrId = istrId;
    }
}
