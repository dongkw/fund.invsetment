package fund.investment.app.pledge.repo.api.command.inqresult;

import fund.investment.basic.inqresult.api.command.CancelInquiryResultCmd;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PRCancelInquiryResultCmd extends CancelInquiryResultCmd {

    public PRCancelInquiryResultCmd(String id) {
        super(id);
    }
}
