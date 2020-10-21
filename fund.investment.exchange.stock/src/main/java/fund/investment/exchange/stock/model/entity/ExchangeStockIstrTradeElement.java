package fund.investment.exchange.stock.model.entity;

import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeSide;
import fund.investment.instruction.domain.model.entity.IstrTradeElement;
import fund.investment.instruction.exchange.stock.domain.model.command.ESCreateIstrCmd;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ApiModel(value = "场内股票交易要素")
public class ExchangeStockIstrTradeElement extends IstrTradeElement {

	@ApiModelProperty(value = "指令价格")
	private String price;

	@ApiModelProperty(value = "交易方向 ")
	private TradeSide side;

	@ApiModelProperty(value = "指令金额")
	private Long amount = 0L;


	public void checkOrder(ESCreateIstrCmd esCreateIstrCmd){

	}


}
