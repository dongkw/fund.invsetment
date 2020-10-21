package infrastructure.trade.domain.model.valueobject;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderTradeElement implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7827519290181849862L;
	private String tradeType;
    private String securityCode;

}
