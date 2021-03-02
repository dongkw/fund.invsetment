package fund.investment.basic.instruction.api.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class IstrPendingEvt extends InstructionEvent {

    /**
     * 指令分发状态
     */
    private String chInsDispStatus;


}
