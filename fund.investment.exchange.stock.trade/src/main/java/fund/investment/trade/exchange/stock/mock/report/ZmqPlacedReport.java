package fund.investment.trade.exchange.stock.mock.report;

import infrastructure.trade.userinterface.dto.report.PlacedReport;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ZmqPlacedReport extends PlacedReport {

    private String id;

    private String instructionId;

    public ZmqPlacedReport(String id, String instructionId) {
        super();
        this.id = id;
        this.instructionId = instructionId;
    }
}
