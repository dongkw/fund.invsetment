package fund.investment.basic.instruction.api.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
public class IstrFillReceivedEvt extends InstructionEvent {

    private String orderId;

    private String fillId;

    private long fillQuantity;

    /**
     * 指令成交状态
     */
    private String chInsDealStatus;

}
