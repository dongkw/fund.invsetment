package fund.investment.basic.common.http.response.pledgerepo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultEntity {
    private Boolean commandStatus;
    private Boolean complianceStatus;
    private Boolean verifyStatus;

    public ResultEntity(){}
    public ResultEntity(boolean commandStatus ){
        this.commandStatus = commandStatus;
    }
    public ResultEntity(boolean commandStatus, boolean complianceStatus){
        this.commandStatus = commandStatus;
        this.complianceStatus = complianceStatus;
    }
    public ResultEntity(boolean commandStatus, boolean complianceStatus, boolean verifyStatus){
        this.commandStatus = commandStatus;
        this.complianceStatus = complianceStatus;
        this.verifyStatus = verifyStatus;
    }
}
