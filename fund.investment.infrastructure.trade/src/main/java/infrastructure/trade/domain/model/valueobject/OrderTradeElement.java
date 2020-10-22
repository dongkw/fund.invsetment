package infrastructure.trade.domain.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderTradeElement {

    private String tradeType;

    private String securityCode;
}
