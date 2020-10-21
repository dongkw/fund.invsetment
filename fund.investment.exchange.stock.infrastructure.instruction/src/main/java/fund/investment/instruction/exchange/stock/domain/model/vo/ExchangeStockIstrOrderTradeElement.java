package fund.investment.instruction.exchange.stock.domain.model.vo;

import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeSide;
import fund.investment.infrastructure.instruction.domain.model.vo.OrderTradeElement;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class ExchangeStockIstrOrderTradeElement extends OrderTradeElement {

	@ApiModelProperty(value = "指令价格")
	private String price;

	@ApiModelProperty(value = "交易方向 ")
	private TradeSide side;

	@ApiModelProperty(value = "指令金额")
	private Long amount = 0L;

}
