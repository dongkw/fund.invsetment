package fund.investment.basic.common.http.response.risk;

import io.swagger.annotations.ApiModelProperty;

public class RiskResultInfo {
    private String id;
    private String riskViolationId;
    private String productId;
    @ApiModelProperty(
            value = "风控规则序号",
            example = "",
            notes = "下达/修改返回",
            required = true
    )
    private String riskRuleId;
    @ApiModelProperty(
            value = "风控规则说明",
            example = "",
            notes = "下达/修改返回",
            required = true
    )
    private String riskRuleName;
    @ApiModelProperty(
            value = "风控规则类型",
            example = "",
            notes = "下达/修改返回",
            required = true
    )
    private String riskRuleType;
    @ApiModelProperty(
            value = "分子计算值",
            example = "",
            notes = "下达/修改返回",
            required = false
    )
    private Double numeratorValue;
    @ApiModelProperty(
            value = "分母计算值",
            example = "",
            notes = "下达/修改返回",
            required = false
    )
    private Double denominatorValue;
    private String securityId;
    private String securityCode;
    @ApiModelProperty(
            value = "计算值",
            example = "",
            notes = "下达/修改返回",
            required = false
    )
    private Double calcValue;
    @ApiModelProperty(
            value = "设置值",
            example = "",
            notes = "下达/修改返回",
            required = false
    )
    private Double setValue;
    @ApiModelProperty(
            value = "单位",
            example = "",
            notes = "下达/修改返回",
            required = false
    )
    private Integer displayUnit;
    @ApiModelProperty(
            value = "阈值比较方向",
            example = "",
            notes = "下达/修改返回",
            required = false
    )
    private String compareDirection;
    @ApiModelProperty(
            value = "触警级别",
            example = "",
            notes = "下达/修改返回",
            required = true
    )
    private Integer warnLevel;
    @ApiModelProperty(
            value = "备注",
            example = "",
            notes = "下达/修改返回",
            required = false
    )
    private String remark;
    private String thresholdId;
    private String riskApprFlowType;
    private String extRiskProperty;


    protected boolean canEqual(Object other) {
        return other instanceof RiskResultInfo;
    }

    public RiskResultInfo() {
    }

    public String getId() {
        return this.id;
    }

    public String getRiskViolationId() {
        return this.riskViolationId;
    }

    public String getProductId() {
        return this.productId;
    }

    public String getRiskRuleId() {
        return this.riskRuleId;
    }

    public String getRiskRuleName() {
        return this.riskRuleName;
    }

    public String getRiskRuleType() {
        return this.riskRuleType;
    }

    public Double getNumeratorValue() {
        return this.numeratorValue;
    }

    public Double getDenominatorValue() {
        return this.denominatorValue;
    }

    public String getSecurityId() {
        return this.securityId;
    }

    public String getSecurityCode() {
        return this.securityCode;
    }

    public Double getCalcValue() {
        return this.calcValue;
    }

    public Double getSetValue() {
        return this.setValue;
    }

    public Integer getDisplayUnit() {
        return this.displayUnit;
    }

    public String getCompareDirection() {
        return this.compareDirection;
    }

    public Integer getWarnLevel() {
        return this.warnLevel;
    }

    public String getRemark() {
        return this.remark;
    }

    public String getThresholdId() {
        return this.thresholdId;
    }

    public String getRiskApprFlowType() {
        return this.riskApprFlowType;
    }

    public String getExtRiskProperty() {
        return this.extRiskProperty;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRiskViolationId(String riskViolationId) {
        this.riskViolationId = riskViolationId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setRiskRuleId(String riskRuleId) {
        this.riskRuleId = riskRuleId;
    }

    public void setRiskRuleName(String riskRuleName) {
        this.riskRuleName = riskRuleName;
    }

    public void setRiskRuleType(String riskRuleType) {
        this.riskRuleType = riskRuleType;
    }

    public void setNumeratorValue(Double numeratorValue) {
        this.numeratorValue = numeratorValue;
    }

    public void setDenominatorValue(Double denominatorValue) {
        this.denominatorValue = denominatorValue;
    }

    public void setSecurityId(String securityId) {
        this.securityId = securityId;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public void setCalcValue(Double calcValue) {
        this.calcValue = calcValue;
    }

    public void setSetValue(Double setValue) {
        this.setValue = setValue;
    }

    public void setDisplayUnit(Integer displayUnit) {
        this.displayUnit = displayUnit;
    }

    public void setCompareDirection(String compareDirection) {
        this.compareDirection = compareDirection;
    }

    public void setWarnLevel(Integer warnLevel) {
        this.warnLevel = warnLevel;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setThresholdId(String thresholdId) {
        this.thresholdId = thresholdId;
    }

    public void setRiskApprFlowType(String riskApprFlowType) {
        this.riskApprFlowType = riskApprFlowType;
    }

    public void setExtRiskProperty(String extRiskProperty) {
        this.extRiskProperty = extRiskProperty;
    }

    public String toString() {
        return "RiskResultInfo(id=" + this.getId() + ", riskViolationId=" + this.getRiskViolationId() + ", productId=" + this.getProductId() + ", riskRuleId=" + this.getRiskRuleId() + ", riskRuleName=" + this.getRiskRuleName() + ", riskRuleType=" + this.getRiskRuleType() + ", numeratorValue=" + this.getNumeratorValue() + ", denominatorValue=" + this.getDenominatorValue() + ", securityId=" + this.getSecurityId() + ", securityCode=" + this.getSecurityCode() + ", calcValue=" + this.getCalcValue() + ", setValue=" + this.getSetValue() + ", displayUnit=" + this.getDisplayUnit() + ", compareDirection=" + this.getCompareDirection() + ", warnLevel=" + this.getWarnLevel() + ", remark=" + this.getRemark() + ", thresholdId=" + this.getThresholdId() + ", riskApprFlowType=" + this.getRiskApprFlowType() + ", extRiskProperty=" + this.getExtRiskProperty() + ")";
    }
}
