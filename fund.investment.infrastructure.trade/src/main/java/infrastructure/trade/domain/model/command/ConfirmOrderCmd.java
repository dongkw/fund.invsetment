package infrastructure.trade.domain.model.command;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ConfirmOrderCmd extends OrderCommand{
	public ConfirmOrderCmd(String id, String instructionId, String tradeType) {
		super(id, instructionId, tradeType);
		
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
