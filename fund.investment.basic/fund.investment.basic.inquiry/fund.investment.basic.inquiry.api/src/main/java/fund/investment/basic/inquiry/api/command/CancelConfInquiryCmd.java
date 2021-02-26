package fund.investment.basic.inquiry.api.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author dongkw
 * @Date 2020/10/10、9:29 上午
 **/

@Getter
@Setter
@NoArgsConstructor
public class CancelConfInquiryCmd extends InquiryCommand {

    private String cancelType;

    private String cancelMsg;

    public CancelConfInquiryCmd(String id) {
        super( id);
    }
}
