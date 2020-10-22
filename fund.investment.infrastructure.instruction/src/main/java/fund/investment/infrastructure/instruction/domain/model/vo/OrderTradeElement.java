package fund.investment.infrastructure.instruction.domain.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderTradeElement {

    private String tradeType;

    private String securityCode;

    private long quantity;
}
