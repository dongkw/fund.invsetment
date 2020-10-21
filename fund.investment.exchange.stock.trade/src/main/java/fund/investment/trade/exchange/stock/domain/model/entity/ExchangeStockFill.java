package fund.investment.trade.exchange.stock.domain.model.entity;

import java.math.BigDecimal;

import fund.investment.infrastructure.util.LoggerTemplate;
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
	private Long FillQuantity;
	private BigDecimal fillAmount;
	private BigDecimal clearAmount;
	
	@Override
	public String toString() {
		return LoggerTemplate.builder()
				.CONTENT(this)
				.NAME(this.getClass().getSimpleName())
				.build()
				.toJson();
	}

}
