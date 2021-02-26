package fund.investment.app.pledge.repo.api.command.inquiry;

import fund.investment.basic.inquiry.api.command.InquiryCommand;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PRBackInquiryCmd extends InquiryCommand {

    public PRBackInquiryCmd(String id) {
        super(id);
    }
}
