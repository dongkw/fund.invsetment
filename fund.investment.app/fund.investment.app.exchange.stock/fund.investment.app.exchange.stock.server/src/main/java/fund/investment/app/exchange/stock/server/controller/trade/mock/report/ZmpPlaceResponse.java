package fund.investment.app.exchange.stock.server.controller.trade.mock.report;

import fund.investment.basic.trade.api.dto.report.PlaceResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ZmpPlaceResponse extends PlaceResponse {

    private String id;

    private String instructionId;

    private String userId;
    /**
     * 委托表主键
     */
    private String skId;
    /**
     * 当前日期
     */
    private Date dtCurrentDate;
    /**
     * 外部委托来源方式
     */
    private String chInstrSource;
    /**
     * 外部委托系统唯一编号
     */
    private String chSourceKey;
    /**
     * 备注
     */
    private String chMemo;
    /**
     * 修改人
     */
    private String chLastModifiedId;
    /**
     * 修改时间
     */
    private Date tsLastModifiedTime;

    public ZmpPlaceResponse(String id, String instructionId, String userId, String skId, Date dtCurrentDate, String chInstrSource, String chSourceKey, String chMemo, String chLastModifiedId, Date tsLastModifiedTime) {
        this.id = id;
        this.instructionId = instructionId;
        this.userId = userId;
        this.skId = skId;
        this.dtCurrentDate = dtCurrentDate;
        this.chInstrSource = chInstrSource;
        this.chSourceKey = chSourceKey;
        this.chMemo = chMemo;
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
