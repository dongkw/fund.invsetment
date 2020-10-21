package fund.investment.instruction.domain.model.eventhandler.saga.util.create.vo;

import fund.investment.instruction.domain.model.aggregate.status.InstructionState;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

/**
 * @Author dongkw
 * @Date 2020/9/21、9:35 上午
 **/
@Data
public class IstrVo {

    private String istrId;
    private InstructionState state;
    private String securityCode;
    private String unitId;
    private BigDecimal amount;

    Set<IstrSagaStatus> statuses;

}
