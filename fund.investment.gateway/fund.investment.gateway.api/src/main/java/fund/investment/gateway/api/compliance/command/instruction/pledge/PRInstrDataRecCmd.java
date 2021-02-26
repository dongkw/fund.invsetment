package fund.investment.gateway.api.compliance.command.instruction.pledge;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PRInstrDataRecCmd extends PRInstrRiskControlCmd {

    /**
     * 流程状态类型
     */
    @ApiModelProperty(hidden = true)
    private String cFlowOperateType;

    /**
     * 流程状态
     */
    @ApiModelProperty(hidden = true)
    private String cFlowOperateStatus;


}
