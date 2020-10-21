package fund.investment.infrastructure.instruction.domain.model.event;

import fund.investment.infrastructure.util.LoggerTemplate;import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeType;
import lombok.*;

/**
 * 指令事件父类
 */
@Getter
@Setter
@NoArgsConstructor
public class InstructionEvent extends DomainEvent{

    private TradeType tradeType;

    public InstructionEvent(TradeType tradeType, String id){
        super(id);
        this.tradeType=tradeType;
    }

    @Override
    public String toString() {
        return LoggerTemplate.builder()
                .CONTENT(this)
                .NAME(this.getClass().getSimpleName())
                .build()
                .toJson();
    }

}
