package fund.investment.instruction.exchange.stock.domain.model.command;

import fund.investment.infrastructure.instruction.domain.model.command.CreateIstrCmd;
import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeSide;
import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ESCreateIstrCmd extends CreateIstrCmd {

    @ApiModelProperty(value = "指令价格")
    private String price;

    @ApiModelProperty(value = "交易方向 ")
    private TradeSide side;

    @ApiModelProperty(value = "指令金额")
    private Long amount = 0L;

    public ESCreateIstrCmd(String id, TradeType tradeType, String unitId, String accountId, String userId, String securityCode, long quantity, String price, TradeSide side, Long amount) {
        super(id, tradeType, unitId, accountId, userId, securityCode, quantity);
        this.price = price;
        this.side = side;
        this.amount = amount;
    }
}
