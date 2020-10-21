package fund.investment.infrastructure.instruction.domain.model.event;

import fund.investment.infrastructure.util.LoggerTemplate;import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeType;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Profile;

@Data
@Builder
@Profile(value = "event")
@AllArgsConstructor
@NoArgsConstructor
public class IstrCreatedEvt extends InstructionEvent {
	
	@ApiModelProperty(value = "成交单元id")
	private String unitId;

	@ApiModelProperty(value = "账号id")
	private String accountId;

	@ApiModelProperty(value = "用户id")
	private String userId;

	@ApiModelProperty(value = "证券内码")
	private String securityCode;

	@ApiModelProperty(value = "数量")
	private Long quantity;

	@Override
	public String toString() {
		return LoggerTemplate.builder()
				.CONTENT(this)
				.NAME(this.getClass().getSimpleName())
				.build()
				.toJson();
	}
	
}
