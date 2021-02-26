package fund.investment.gateway.api.compliance.event.instruction;

import fund.investment.gateway.api.compliance.event.ComplianceEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author dongkw
 * @Date 2020/9/17、14:59
 **/
@Getter
@Setter
@NoArgsConstructor
public class IstrCmplEvt extends ComplianceEvent {

    private long total;

    public IstrCmplEvt(String id, long total) {
        super(id);
        this.total = total;
    }
}
