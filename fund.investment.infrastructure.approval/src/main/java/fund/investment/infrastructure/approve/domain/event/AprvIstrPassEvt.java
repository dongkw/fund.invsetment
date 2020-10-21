package fund.investment.infrastructure.approve.domain.event;

import org.springframework.context.annotation.Profile;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Profile("command")
@ApiModel(value = "审批通过")
public class AprvIstrPassEvt {
	@ApiModelProperty(value = "审批id")
	private String id;
	@ApiModelProperty(value = "指令id")
	private String instructionId;
	private Integer status;
	private String userId;
	private String operatorId;
}
