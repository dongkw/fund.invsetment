package fund.investment.basic.instruction.api.command;

import fund.investment.basic.instruction.api.enumeration.TradeType;
import lombok.NoArgsConstructor;


@NoArgsConstructor
public class CreateConfirmIstrCmd extends InstructionCommand {

    private String inquiryResultId;
    private String chInstrSource;
    private String chSourceKey;
    private String chSourceNo;
    public CreateConfirmIstrCmd(TradeType tradeType, String id, String skId, String skInstr) {
        super(tradeType, id, skId, skInstr);
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
