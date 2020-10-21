package fund.investment.infrastructure.instruction.domain.model.vo;

import fund.investment.infrastructure.instruction.domain.model.enumeration.OrderStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
@ApiModel("委托")
@AllArgsConstructor
public class Order {
	
	@ApiModelProperty(value = "委托Id")
	private String id;
	
	@ApiModelProperty(value = "委托状态")
    private OrderStatus status;

	@ApiModelProperty(value = "委托数量")
	private Long quantity;

	@ApiModelProperty(value = "成交数量")
	private Long fillQuantity;

	@ApiModelProperty("委托列表")
	List<Fill> fills = new LinkedList<>();
	
}
