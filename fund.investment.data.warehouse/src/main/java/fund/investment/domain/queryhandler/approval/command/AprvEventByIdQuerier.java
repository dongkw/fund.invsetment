package fund.investment.domain.queryhandler.approval.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AprvEventByIdQuerier {
	
	private String id;

}
