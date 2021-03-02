package fund.investment.basic.instruction.api.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IstrCompletedEvt extends InstructionEvent {

    /**
     * 指令成交状态
     */
    private String chInsDealStatus;

}
