package fund.investment.basic.common.http.response.risk;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "风控信息返回")
public class RiskResultDtoResponse {

    @ApiModelProperty(value = "详细风控条款列表", example = "", notes = "下达/修改返回", required = true)
    private List<RiskResultResponse> riskInfos;

    @ApiModelProperty(value = "是否有不合规风控", example = "", notes = "下达/修改返回", required = true)
    private Boolean success;

    @ApiModelProperty(value = "风控是否能跳过预警", example = "", notes = "下达/修改返回", required = true)
    private Boolean passWarn;

    /**
     * 风控试算是否通过
     */
    private Boolean riskPass;
    /**
     * 风控结果
     */
    private String riskPassStr;

    /**
     * 产品代理键
     */
    private String skProduct;

    /*修改字段*/
    private Boolean modification;

}
