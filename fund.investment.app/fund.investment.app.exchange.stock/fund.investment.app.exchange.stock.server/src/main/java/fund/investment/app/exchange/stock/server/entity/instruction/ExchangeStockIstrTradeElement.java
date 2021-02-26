package fund.investment.app.exchange.stock.server.entity.instruction;

import fund.investment.app.exchange.stock.api.command.instruction.ESCreateIstrCmd;
import fund.investment.basic.instruction.api.entity.IstrTradeElement;
import fund.investment.basic.instruction.api.enumeration.TradeSide;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeStockIstrTradeElement extends IstrTradeElement {

    private String price;

    private TradeSide side;

    private long amount;

    public void checkOrder(ESCreateIstrCmd esCreateIstrCmd) {

    }
}
