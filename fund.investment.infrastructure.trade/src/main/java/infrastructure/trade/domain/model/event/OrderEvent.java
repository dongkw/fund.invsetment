package infrastructure.trade.domain.model.event;

import fund.investment.infrastructure.common.DomainEvent;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderEvent extends DomainEvent {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4834896732401579174L;
	private String instructionId;
	private String tradeType;
	
	public OrderEvent(String id, String tradeType, String instructionId) {
		super(id);
		
		this.instructionId = instructionId;
		this.tradeType = tradeType;
		
	}
	
}
