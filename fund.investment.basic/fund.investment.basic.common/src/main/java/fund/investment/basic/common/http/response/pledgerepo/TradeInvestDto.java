package fund.investment.basic.common.http.response.pledgerepo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class TradeInvestDto {
    @ApiModelProperty(
            value = "投资指令表主键",
            example = "",
            notes = "修改使用",
            required = true
    )
    private String skId;
    @ApiModelProperty(
            hidden = true
    )
    private String skInstr;
    @ApiModelProperty(
            hidden = true
    )
    private String cInstrNo;
    @ApiModelProperty(
            hidden = true
    )
    private String cInstrModify;
    @ApiModelProperty(
            hidden = true
    )
    private String cStockNo;
    @ApiModelProperty(
            hidden = true
    )
    private Date dCurrentDate;
    @ApiModelProperty(
            hidden = true
    )
    private Date dTradeDate;
    @ApiModelProperty(
            value = "产品代理键",
            example = "",
            notes = "下达使用",
            required = true
    )
    private String skProduct;
    @ApiModelProperty(
            value = "资产单元代理键",
            example = "",
            notes = "下达使用",
            required = true
    )
    private String skAsset;
    @ApiModelProperty(
            value = "投资组合代理键",
            example = "",
            notes = "下达使用",
            required = true
    )
    private String skCombi;
    @ApiModelProperty(
            hidden = true
    )
    private String cInstrStatus;
    @ApiModelProperty(
            hidden = true
    )
    private Date dDirectDate;
    @ApiModelProperty(
            hidden = true
    )
    private String cDirectTime;
    @ApiModelProperty(
            hidden = true
    )
    private Date tModifyDate;
    @ApiModelProperty(
            hidden = true
    )
    private Date dBeginDate;
    @ApiModelProperty(
            hidden = true
    )
    private String cBeginTime;
    @ApiModelProperty(
            hidden = true
    )
    private Date dInstrEndDate;
    @ApiModelProperty(
            hidden = true
    )
    private String cEndTime;
    @ApiModelProperty(
            hidden = true
    )
    private String cPreInstrDesc;
    @ApiModelProperty(
            hidden = true
    )
    private String cPreIndexModify;
    @ApiModelProperty(
            hidden = true
    )
    private String skMarktExe;
    @ApiModelProperty(
            value = "交易类型代理键",
            example = "",
            notes = "下达使用",
            required = true
    )
    private String skTradeType;
    @ApiModelProperty(
            hidden = true
    )
    private String skSecurity;
    @ApiModelProperty(
            hidden = true
    )
    private String cInvestType;
    @ApiModelProperty(
            hidden = true
    )
    private BigDecimal fInstrQtty;
    @ApiModelProperty(
            value = "指令金额",
            example = "",
            notes = "下达/修改使用",
            required = true
    )
    private BigDecimal fInstrAmt;
    @ApiModelProperty(
            value = "指令价格",
            example = "",
            notes = "下达/修改使用",
            required = true
    )
    private BigDecimal fInstrPrice;
    @ApiModelProperty(
            value = "清算速度",
            example = "",
            notes = "下达/修改使用",
            required = true
    )
    private String cSettleDays;
    @ApiModelProperty(
            hidden = true
    )
    private String cTraderUserId;
    @ApiModelProperty(
            value = "交易对手代理键",
            notes = "存款对应为银行总行"
    )
    private String skInst;
    @ApiModelProperty(
            value = "对手方交易员ID",
            example = "",
            notes = "下达/修改使用",
            required = false
    )
    private String cTraderId;
    @ApiModelProperty(
            hidden = true
    )
    private String cInstrSource;
    @ApiModelProperty(
            hidden = true
    )
    private String cSourceKey;
    @ApiModelProperty(
            hidden = true
    )
    private String cSourceNo;
    @ApiModelProperty(
            hidden = true
    )
    private String cInstrSourceTwo;
    @ApiModelProperty(
            hidden = true
    )
    private String cSourceKeyTwo;
    @ApiModelProperty(
            hidden = true
    )
    private String cSourceNoTwo;
    @ApiModelProperty(
            hidden = true
    )
    private Date tTimestamp;
    @ApiModelProperty("备注")
    private String cMemo;
    @ApiModelProperty(
            hidden = true
    )
    private String cDelete;
    @ApiModelProperty(
            hidden = true
    )
    private String cCreateId;
    @ApiModelProperty(
            hidden = true
    )
    private Date tCreateTime;
    @ApiModelProperty(
            hidden = true
    )
    private String cLastModifiedId;
    @ApiModelProperty(
            hidden = true
    )
    private Date tLastModifiedTime;
    @ApiModelProperty(
            hidden = true
    )
    private String cDataSource;
    @ApiModelProperty(
            hidden = true
    )
    private String cAuditorId;
    @ApiModelProperty(
            hidden = true
    )
    private String cAuditStatus;
    @ApiModelProperty(
            hidden = true
    )
    private Date tAuditTime;
    @ApiModelProperty(
            hidden = true
    )
    private String cIsLock;
    @ApiModelProperty(
            hidden = true
    )
    private String cInstrIssuedSource;
}
