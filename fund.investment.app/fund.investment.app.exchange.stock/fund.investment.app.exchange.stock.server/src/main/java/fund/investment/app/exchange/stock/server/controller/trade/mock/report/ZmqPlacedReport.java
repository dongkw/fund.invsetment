package fund.investment.app.exchange.stock.server.controller.trade.mock.report;

import fund.investment.basic.trade.api.dto.report.PlacedReport;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ZmqPlacedReport extends PlacedReport{

    private String id;

    private String instructionId;

    private String userId;
    private String skId;
    private String chLastModifiedId;
    private Date tsLastModifiedTime;
    private String chReportNo;
    private String chCancelReason;

    public ZmqPlacedReport(String id, String instructionId, String userId, String skId, String chLastModifiedId, Date tsLastModifiedTime, String chReportNo, String chCancelReason) {
        this.id = id;
        this.instructionId = instructionId;
        this.userId = userId;
        this.skId = skId;
        this.chLastModifiedId = chLastModifiedId;
        this.tsLastModifiedTime = tsLastModifiedTime;
        this.chReportNo = chReportNo;
        this.chCancelReason = chCancelReason;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInstructionId() {
        return instructionId;
    }

    public void setInstructionId(String instructionId) {
        this.instructionId = instructionId;
    }
}
