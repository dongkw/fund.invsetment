package fund.investment.trade.exchange.stock.domain.model.entity;

import java.math.BigDecimal;

import infrastructure.trade.domain.model.valueobject.Fill;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ExchangeStockFill extends Fill {
	private String side;
	private BigDecimal fillPrice;
	private Long fillQuantity;
	private BigDecimal fillAmount;
	private BigDecimal clearAmount;
		
}
