package fund.investment.app.exchange.stock.server.controller.trade.mock.report;

import fund.investment.basic.trade.api.dto.report.FillReport;
import fund.investment.basic.trade.api.valueobject.Fill;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ZmqFillReport extends FillReport {

    private String id;

    private String userId;
    private String skId;
    private String chLastModifiedId;
    private Date tsLastModifiedTime;

    private Fill fill;

    public ZmqFillReport(String id, String userId, String skId, String chLastModifiedId, Date tsLastModifiedTime, Fill fill) {
        this.id = id;
        this.userId = userId;
        this.skId = skId;
        this.chLastModifiedId = chLastModifiedId;
        this.tsLastModifiedTime = tsLastModifiedTime;
        this.fill = fill;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Fill getFill() {
        return fill;
    }

    public void setFill(Fill fill) {
        this.fill = fill;
    }
}
