package fund.investment.basic.instruction.api.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DistributeIstrCmd extends InstructionCommand {

    /**
     * 指令分发状态
     */
    private String chInsDispStatus;

}
