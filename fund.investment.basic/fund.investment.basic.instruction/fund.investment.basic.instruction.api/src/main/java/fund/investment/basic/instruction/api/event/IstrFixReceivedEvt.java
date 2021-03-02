package fund.investment.basic.instruction.api.event;

import fund.investment.basic.instruction.api.enumeration.TradeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class IstrFixReceivedEvt extends InstructionEvent {

    /**
     * fix发送状态
     */
    private String chFixStatus;
    /**
     * fix发送废单原因
     */
    private String chRevokeReason;

}
