package infrastructure.trade.userinterface.dto.report;

import fund.investment.infrastructure.util.LoggerTemplate;
import lombok.Data;

@Data
public class PlacedReport {
	
	@Override
	public String toString() {
		return LoggerTemplate.builder()
				.CONTENT(this)
				.NAME(this.getClass().getSimpleName())
				.build()
				.toJson();
	}

}
