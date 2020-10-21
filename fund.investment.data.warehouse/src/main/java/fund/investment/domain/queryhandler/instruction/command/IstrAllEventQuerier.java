package fund.investment.domain.queryhandler.instruction.command;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IstrAllEventQuerier {
	private LocalDateTime time;

}
