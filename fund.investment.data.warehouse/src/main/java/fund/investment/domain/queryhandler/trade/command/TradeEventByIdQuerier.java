package fund.investment.domain.queryhandler.trade.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TradeEventByIdQuerier {
	
	private String id;
}
