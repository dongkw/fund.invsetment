package fund.investment.domain.queryhandler.instruction.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IstrEventByIdQuerier {
	
	private String id;

}
