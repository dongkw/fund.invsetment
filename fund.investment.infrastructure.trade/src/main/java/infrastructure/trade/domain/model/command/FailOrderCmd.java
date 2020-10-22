package infrastructure.trade.domain.model.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FailOrderCmd extends OrderCommand {
	private String unitId;
	private String failCode;
	private String failMsg;
	
	public FailOrderCmd(String id, String instructionId, String tradeType, String unitId, String failCode, String failMsg) {
		super(id, instructionId, tradeType);
		
		this.unitId = unitId;
		this.failCode = failCode;
		this.failMsg = failCode;
		
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
