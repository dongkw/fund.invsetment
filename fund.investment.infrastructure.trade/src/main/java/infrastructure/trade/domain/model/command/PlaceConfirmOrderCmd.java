package infrastructure.trade.domain.model.command;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlaceConfirmOrderCmd extends OrderCommand {

	private LocalDateTime orderTime;
	
	public PlaceConfirmOrderCmd(String id, String instructionId, String tradeType) {
		super(id, instructionId, tradeType);
		
		this.orderTime = LocalDateTime.now();
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
