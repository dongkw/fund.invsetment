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
<<<<<<< HEAD

	@Override
	public String toString() {
		return LoggerTemplate.builder()
				.content(this)
				.name(this.getClass().getSimpleName())
				.build()
				.toJson();
	}
=======
>>>>>>> 3a0a2561e0fa4da06e959b868de6b818fed652af
	
}
