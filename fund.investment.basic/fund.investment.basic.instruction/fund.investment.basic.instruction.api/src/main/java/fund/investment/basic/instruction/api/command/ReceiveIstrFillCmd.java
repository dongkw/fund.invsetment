package fund.investment.basic.instruction.api.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReceiveIstrFillCmd extends InstructionCommand {

    private String orderId;

    private String fillId;

    private long fillQuantity;

    /**
     * 指令成交状态
     */
    private String chInsDealStatus;


}
