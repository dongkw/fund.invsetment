package fund.investment.basic.instruction.api.event;

import fund.investment.basic.instruction.api.enumeration.TradeType;
import lombok.NoArgsConstructor;

import java.util.Date;


@NoArgsConstructor
public class IstrConfirmedEvt extends InstructionEvent {

    private String inquiryResultId;
    private String chInstrSource;
    private String chSourceKey;
    private String chSourceNo;
    public IstrConfirmedEvt(String id, Long requestId, TradeType tradeType, String userId, String skId, String skInstr, String cLastModifiedId, Date tLastModifiedTime, String inquiryResultId) {
        super(id, requestId, tradeType, userId, skId, skInstr, cLastModifiedId, tLastModifiedTime);
        this.inquiryResultId = inquiryResultId;
    }

    public String getInquiryResultId() {
        return inquiryResultId;
    }

    public void setInquiryResultId(String inquiryResultId) {
        this.inquiryResultId = inquiryResultId;
    }

    public String getChInstrSource() {
        return chInstrSource;
    }

    public void setChInstrSource(String chInstrSource) {
        this.chInstrSource = chInstrSource;
    }

    public String getChSourceKey() {
        return chSourceKey;
    }

    public void setChSourceKey(String chSourceKey) {
        this.chSourceKey = chSourceKey;
    }

    public String getChSourceNo() {
        return chSourceNo;
    }

    public void setChSourceNo(String chSourceNo) {
        this.chSourceNo = chSourceNo;
    }
}
