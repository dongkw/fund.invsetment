package fund.investment.app.pledge.repo.api.command.inquiry;

import fund.investment.basic.inquiry.api.command.InquiryCommand;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PRRejectInquiryCmd extends InquiryCommand {

    public PRRejectInquiryCmd(String id) {
        super(id);
    }
}
