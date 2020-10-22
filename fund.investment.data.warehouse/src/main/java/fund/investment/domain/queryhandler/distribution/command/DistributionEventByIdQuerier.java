package fund.investment.domain.queryhandler.distribution.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DistributionEventByIdQuerier {
	
	private String id;
}
