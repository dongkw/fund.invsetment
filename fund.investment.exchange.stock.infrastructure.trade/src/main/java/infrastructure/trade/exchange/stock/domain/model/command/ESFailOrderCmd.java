package infrastructure.trade.exchange.stock.domain.model.command;

import infrastructure.trade.domain.model.command.FailOrderCmd;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ESFailOrderCmd extends FailOrderCmd {

    public ESFailOrderCmd(String id, String instructionId, String tradeType, String unitId, String failCode,
                          String failMsg) {
        super(id, instructionId, tradeType, unitId, failCode, failMsg);
    }
}
