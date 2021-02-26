package fund.investment.basic.trade.api.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Fill {

    private String id;



    private String orderOriginalId;

    private String orderExchangeId;

    private String instructionId;

    private String tradeType;

    private String securityCode;

    private LocalDateTime fillTime;

}
