package fund.investment.infrastructure.instruction.domain.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Fill {

    private String id;

    private String orderId;

    private long quantity;
}