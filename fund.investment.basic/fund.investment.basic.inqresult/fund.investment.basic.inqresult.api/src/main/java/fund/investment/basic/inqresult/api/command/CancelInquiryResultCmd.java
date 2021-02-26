package fund.investment.basic.inqresult.api.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CancelInquiryResultCmd extends InquiryResultCommand {

    private String cancelMsg;

    private String cancelType;


    public CancelInquiryResultCmd(String id) {
        super(id);
    }
}
