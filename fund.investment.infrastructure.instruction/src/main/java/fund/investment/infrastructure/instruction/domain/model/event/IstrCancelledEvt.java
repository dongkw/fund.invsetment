package fund.investment.infrastructure.instruction.domain.model.event;

import fund.investment.infrastructure.util.LoggerTemplate;import fund.investment.infrastructure.instruction.domain.model.enumeration.ApprovalStatus;
import fund.investment.infrastructure.instruction.domain.model.enumeration.DistributeStatus;
import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeType;
import org.springframework.context.annotation.Profile;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@Profile(value = "event")
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class IstrCancelledEvt extends InstructionEvent {
	
	@ApiModelProperty(value = "成交单元id")
	private String unitId;

	private String description;

	@ApiModelProperty(value = "审批状态")
	private ApprovalStatus approvalStatus;

	@ApiModelProperty(value = "分发状态 ")
	private DistributeStatus distributeStatus;

	@ApiModelProperty(value = "证券内码")
	private String securityCode;

	@ApiModelProperty(value = "数量")
	private Long quantity;

	private Long cancelQuantity;


	@Override
	public String toString() {
		return LoggerTemplate.builder()
				.CONTENT(this)
				.NAME(this.getClass().getSimpleName())
				.build()
				.toJson();
	}

}
