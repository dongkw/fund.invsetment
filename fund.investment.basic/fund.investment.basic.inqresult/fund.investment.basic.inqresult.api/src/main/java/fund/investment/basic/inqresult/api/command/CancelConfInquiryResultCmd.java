package fund.investment.basic.inqresult.api.command;

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
public class CancelConfInquiryResultCmd extends InquiryResultCommand {

    private String cancelType;

    private String cancelMsg;

    public CancelConfInquiryResultCmd(String id) {
        super( id);
    }
}
