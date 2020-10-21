package fund.investment.instruction.exchange.stock.domain.model.command;

import fund.investment.infrastructure.instruction.domain.model.command.CreateIstrCmd;
import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeSide;
import fund.investment.infrastructure.util.LoggerTemplate;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ESCreateIstrCmd extends CreateIstrCmd {

    @ApiModelProperty(value = "指令价格")
    private String price;

    @ApiModelProperty(value = "交易方向 ")
    private TradeSide side;

    @ApiModelProperty(value = "指令金额")
    private Long amount = 0L;

    @Override
    public String toString() {
        return LoggerTemplate.builder()
                .CONTENT(this)
                .NAME(this.getClass().getSimpleName())
                .build()
                .toJson();
    }

}
