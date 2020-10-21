package fund.investment.infrastructure.instruction.domain.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@ApiModel("成交")
@AllArgsConstructor
public class Fill {
	
	@ApiModelProperty(value = "成交Id")
    private String id;
	
	@ApiModelProperty(value = "委托Id")
	private String orderId;
	
	@ApiModelProperty(value = "成交量")
    private Long quantity = 0l;
	
}
