package fund.investment.basic.inquiry.api.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CancelInquiryCmd extends InquiryCommand {

    private String cancelMsg;

    private String cancelType;


    public CancelInquiryCmd(String id) {
        super(id);
    }
}
