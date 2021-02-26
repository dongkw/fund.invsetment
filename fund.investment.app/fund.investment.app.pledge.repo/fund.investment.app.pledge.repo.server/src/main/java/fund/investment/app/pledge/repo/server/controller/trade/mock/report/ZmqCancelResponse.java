package fund.investment.app.pledge.repo.server.controller.trade.mock.report;

import fund.investment.basic.trade.api.dto.report.CancelResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ZmqCancelResponse extends CancelResponse {

    private String id;

    private String instructionId;

    private String userId;
    private String skId;
    private String chLastModifiedId;
    private Date tsLastModifiedTime;

    public ZmqCancelResponse(String id, String instructionId, String userId, String skId, String chLastModifiedId, Date tsLastModifiedTime) {
        this.id = id;
        this.instructionId = instructionId;
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

    public String getInstructionId() {
        return instructionId;
    }

    public void setInstructionId(String instructionId) {
        this.instructionId = instructionId;
    }

}
