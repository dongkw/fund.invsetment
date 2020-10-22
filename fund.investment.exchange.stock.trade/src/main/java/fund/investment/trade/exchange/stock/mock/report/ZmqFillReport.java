package fund.investment.trade.exchange.stock.mock.report;

import fund.investment.trade.exchange.stock.domain.model.entity.ExchangeStockFill;
import infrastructure.trade.userinterface.dto.report.FillReport;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ZmqFillReport extends FillReport {

    private String id;

    private ExchangeStockFill fill;

    public ZmqFillReport(String id, ExchangeStockFill fill) {
        super();
        this.id = id;
        this.fill = fill;
    }
}
