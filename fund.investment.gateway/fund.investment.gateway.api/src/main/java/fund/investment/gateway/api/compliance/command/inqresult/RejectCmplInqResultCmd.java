package fund.investment.gateway.api.compliance.command.inqresult;

import fund.investment.gateway.api.compliance.command.ComplianceCommand;
import lombok.Data;

import java.util.Date;

@Data
public class RejectCmplInqResultCmd extends ComplianceCommand {
    private String skId;

    private String skInquiry;

    private String chMemo;

    private String chLastModifiedId;

    private Date tsLastModifiedTime;
    private String chSourceKey;

}
