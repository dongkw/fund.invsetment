package fund.investment.basic.inquiry.api.command;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CreateConfirmInquiryCmd extends InquiryCommand {
    private String chInstrSource;
    private String chSourceKey;
    private String chSourceNo;
    public CreateConfirmInquiryCmd(String id, String cInstrSource) {
        super(id);
        this.chInstrSource = cInstrSource;
    }

}
