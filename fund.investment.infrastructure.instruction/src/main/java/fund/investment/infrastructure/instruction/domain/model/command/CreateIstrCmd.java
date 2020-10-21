package fund.investment.infrastructure.instruction.domain.model.command;

import fund.investment.infrastructure.util.LoggerTemplate;import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Profile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Profile(value = "command")
public class CreateIstrCmd extends InstructionCommand{
	
	@ApiModelProperty(value = "单元id")
	private String unitId;

	@ApiModelProperty(value = "账户id")
	private String accountId;

	@ApiModelProperty(value = "用户id")
	private String userId;

	@ApiModelProperty(value = "证券内码")
	private String securityCode;

	@ApiModelProperty(value = "数量")
	private Long quantity = 0L;

	@Override
	public String toString() {
		return LoggerTemplate.builder()
				.CONTENT(this)
				.NAME(this.getClass().getSimpleName())
				.build()
				.toJson();
	}
}
