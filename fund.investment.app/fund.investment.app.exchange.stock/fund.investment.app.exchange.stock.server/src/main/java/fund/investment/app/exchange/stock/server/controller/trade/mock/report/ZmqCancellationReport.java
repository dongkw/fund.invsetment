package fund.investment.app.exchange.stock.server.controller.trade.mock.report;

import fund.investment.basic.trade.api.dto.report.CancellationReport;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ZmqCancellationReport extends CancellationReport {

    private String id;

    private String unitId;

    private String instructionId;

    private long cancelQuantity;

    private String userId;
    private String skId;
    private String chLastModifiedId;
    private Date tsLastModifiedTime;

    public ZmqCancellationReport(String id, String unitId, String instructionId, long cancelQuantity, String userId, String skId, String chLastModifiedId, Date tsLastModifiedTime) {
        this.id = id;
        this.unitId = unitId;
        this.instructionId = instructionId;
        this.cancelQuantity = cancelQuantity;
        this.userId = userId;
        this.skId = skId;
        this.chLastModifiedId = chLastModifiedId;
        this.tsLastModifiedTime = tsLastModifiedTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getInstructionId() {
        return instructionId;
    }

    public void setInstructionId(String instructionId) {
        this.instructionId = instructionId;
    }

    public long getCancelQuantity() {
        return cancelQuantity;
    }

    public void setCancelQuantity(long cancelQuantity) {
        this.cancelQuantity = cancelQuantity;
    }

}
