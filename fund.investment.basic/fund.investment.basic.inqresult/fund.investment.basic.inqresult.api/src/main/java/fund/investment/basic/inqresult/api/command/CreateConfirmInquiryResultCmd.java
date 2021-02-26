package fund.investment.basic.inqresult.api.command;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CreateConfirmInquiryResultCmd extends InquiryResultCommand {
    private Boolean success;

    private Boolean passWarn;
    public CreateConfirmInquiryResultCmd(String id) {
        super(id);
    }
}
