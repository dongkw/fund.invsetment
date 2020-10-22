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

}
