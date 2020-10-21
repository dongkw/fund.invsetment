package fund.investment.instruction.exchange.stock.domain.model.command;

import fund.investment.infrastructure.instruction.domain.model.command.CreateIstrOrderCmd;
import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeSide;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ESCreateIstrOrderCmd extends CreateIstrOrderCmd {
//    private ExchangeStockOrderTradeElement exchangeStockOrderTradeElement;
    @ApiModelProperty(value = "指令价格")
    private String price;

    @ApiModelProperty(value = "交易方向 ")
    private TradeSide side;

    @ApiModelProperty(value = "指令金额")
    private Long amount = 0L;

}
