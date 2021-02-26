package fund.investment.gateway.api.compliance.command.inquriy;
import fund.investment.gateway.api.compliance.command.ComplianceCommand;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class CancelCmplInquiryCmd extends ComplianceCommand {

    private String istrId;

    private String skId;
    private String chLastModifiedId;
    private Date tsLastModifiedTime;

    @ApiModelProperty(value = "投资指令序号", required = true)
    private String skInquiry;

    public CancelCmplInquiryCmd(String id, String istrId, String userId, String skId, String chLastModifiedId, Date tsLastModifiedTime, String skInquiry) {
        super(id);
        this.istrId = istrId;
        this.skId = skId;
        this.chLastModifiedId = chLastModifiedId;
        this.tsLastModifiedTime = tsLastModifiedTime;
        this.skInquiry = skInquiry;
    }
}
