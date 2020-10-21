package fund.investment.instruction.exchange.stock.domain.model.command;

import fund.investment.infrastructure.instruction.domain.model.command.CreateIstrOrderCmd;
import fund.investment.infrastructure.util.LoggerTemplate;
import fund.investment.instruction.exchange.stock.domain.model.vo.ExchangeStockIstrOrderTradeElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ESCreateIstrOrderCmd extends CreateIstrOrderCmd {

    private ExchangeStockIstrOrderTradeElement exchangeStockIstrOrderTradeElement;

    @Override
    public String toString() {
        return LoggerTemplate.builder()
                .CONTENT(this)
                .NAME(this.getClass().getSimpleName())
                .build()
                .toJson();
    }

}
