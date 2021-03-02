package fund.investment.basic.instruction.api.event;

import fund.investment.basic.common.http.response.risk.RiskResultResponse;
import fund.investment.basic.instruction.api.valueobject.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IstrCancellingEvt extends InstructionEvent {

    private String unitId;

    private String securityCode;

    private List<Order> orders;

    private String cancelType;

    private String cancelMsg;

    private String chInstrSource;

    private String chSourceKey;

    private String chSourceNo;

    private List<RiskResultResponse> riskInfos;

}
