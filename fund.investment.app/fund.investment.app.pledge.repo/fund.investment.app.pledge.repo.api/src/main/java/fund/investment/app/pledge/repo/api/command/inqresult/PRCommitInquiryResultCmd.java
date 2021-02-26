package fund.investment.app.pledge.repo.api.command.inqresult;

import fund.investment.basic.common.valueobject.InterbankPledgeBond;
import fund.investment.basic.inqresult.api.command.CommitInqResultCmd;
import lombok.Data;

import java.util.List;


@Data
public class PRCommitInquiryResultCmd extends CommitInqResultCmd {

    /**
     * 质押债券信息
     */
    private List<InterbankPledgeBond> pledgeBondList;
}
