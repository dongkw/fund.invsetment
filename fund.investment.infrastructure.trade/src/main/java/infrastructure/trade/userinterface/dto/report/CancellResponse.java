package infrastructure.trade.userinterface.dto.report;

import fund.investment.infrastructure.util.LoggerTemplate;
import lombok.Data;

@Data
public class CancellResponse {
	
	@Override
	public String toString() {
		return LoggerTemplate.builder()
				.content(this)
				.name(this.getClass().getSimpleName())
				.build()
				.toJson();
	}

}
