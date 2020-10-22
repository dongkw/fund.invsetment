package fund.investment.infrastructure.compliance.domain.model.event.instruction;

import lombok.*;

/**
 * @Author dongkw
 * @Date 2020/9/17、15:15
 **/
@Getter
@Setter
@NoArgsConstructor
public class IstrCmplRollbackedEvt {
    private String istrId;

    public IstrCmplRollbackedEvt(String istrId) {
        this.istrId = istrId;
    }
}
