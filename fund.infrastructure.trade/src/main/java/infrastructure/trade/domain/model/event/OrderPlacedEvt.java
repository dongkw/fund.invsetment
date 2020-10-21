package infrastructure.trade.domain.model.event;

import java.time.LocalDateTime;

import fund.investment.infrastructure.util.LoggerTemplate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderPlacedEvt extends OrderEvent{
	private LocalDateTime orderTime;
	
	public OrderPlacedEvt(String id, String instructionId, String tradeType) {
		super(id, tradeType, instructionId);
		
		this.orderTime = LocalDateTime.now();
	}

	@Override
	public String toString() {
		return LoggerTemplate.builder()
				.CONTENT(this)
				.NAME(this.getClass().getSimpleName())
				.build()
				.toJson();
	}

}
