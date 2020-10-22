package fund.investment.infrastructure.instruction.domain.model.event;

import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeType;
import fund.investment.infrastructure.util.LoggerTemplate;
import lombok.*;
import org.springframework.context.annotation.Profile;

import org.springframework.context.annotation.Profile;

@NoArgsConstructor
public class IstrPassedEvt extends InstructionEvent{

	public IstrPassedEvt(TradeType tradeType, String id) {
		super(tradeType, id);
	}
}
