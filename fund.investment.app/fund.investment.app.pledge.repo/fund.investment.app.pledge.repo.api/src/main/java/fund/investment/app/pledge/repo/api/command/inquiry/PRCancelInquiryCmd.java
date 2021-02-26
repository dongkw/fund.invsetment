package fund.investment.app.pledge.repo.api.command.inquiry;

import fund.investment.basic.inquiry.api.command.CancelInquiryCmd;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PRCancelInquiryCmd extends CancelInquiryCmd {

    public PRCancelInquiryCmd(String id) {
        super(id);
    }
}
