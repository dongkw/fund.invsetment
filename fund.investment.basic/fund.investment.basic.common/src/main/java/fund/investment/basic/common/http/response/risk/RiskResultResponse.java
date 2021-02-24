package fund.investment.basic.common.http.response.risk;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("详细风控条款")
@Setter
@Getter
public class RiskResultResponse extends RiskResultInfo {
    @ApiModelProperty(
            value = "证券主键",
            example = "",
            notes = "下达/修改返回",
            required = true
    )
    private String skSecurity;
    @ApiModelProperty(
            value = "交易市场",
            example = "",
            notes = "下达/修改返回",
            required = true
    )
    private String skMarket;
    @ApiModelProperty(
            value = "证券代码",
            example = "",
            notes = "下达/修改返回",
            required = true
    )
    private String secuCode;
    @ApiModelProperty(
            value = "证券名称",
            example = "",
            notes = "下达/修改返回",
            required = true
    )
    private String secuName;
    @ApiModelProperty(value = "产品主键",
            example = "",
            notes = "下达/修改返回",
            required = true
    )
    private String skProduct;
    @ApiModelProperty(
            value = "风控规则类型翻译",
            example = "",
            notes = "下达/修改返回",
            required = true
    )
    private String riskruleTypeStr;
    @ApiModelProperty(
            value = "单位翻译",
            example = "",
            notes = "下达/修改返回",
            required = true
    )
    private String displayUnitStr;
    @ApiModelProperty(
            value = "阈值比较方向翻译",
            example = "",
            notes = "下达/修改返回",
            required = true
    )
    private String compareDirectionStr;
    @ApiModelProperty(
            value = "触警级别翻译",
            example = "",
            notes = "下达/修改返回",
            required = true
    )
    private String warnLevelStr;
    @ApiModelProperty(
            value = "证券类型翻译",
            example = "",
            notes = "下达/修改返回",
            required = true
    )
    private String cSecuTypeStr;
    @ApiModelProperty(
            value = "债项评级翻译",
            example = "",
            notes = "下达/修改返回",
            required = true
    )
    private String cSecuRateStr;
    @ApiModelProperty(
            value = "主体评级翻译",
            example = "",
            notes = "下达/修改返回",
            required = true
    )
    private String cInstRateStr;
    private Boolean isWarn;
    private Boolean isPassRisk;
    private String isPassRiskStr;
    private String productName;
}