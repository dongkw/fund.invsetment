package infrastructure.trade.domain.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Fill {

    private String id;

    private String orderOriginalId;

    private String orderExchangeId;

    private String instructionId;

    private String tradeType;

    private String securityCode;

    private LocalDateTime fillTime;
}
