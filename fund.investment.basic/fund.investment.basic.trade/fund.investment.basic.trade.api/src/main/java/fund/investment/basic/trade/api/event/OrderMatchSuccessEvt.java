package fund.investment.basic.trade.api.event;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class OrderMatchSuccessEvt extends OrderEvent {

    private String userId;
    private String skId;
    private String chLastModifiedId;
    private Date tsLastModifiedTime;

    @ApiModelProperty(value = "投资指令id", required = true)
    private String instrSkId;

    @ApiModelProperty(value = "投资指令序号（非id）", required = true)
    private String instrSkInstr;

    @ApiModelProperty(value = "投资指令的组合信息", required = true)
    private String skCombi;

    public OrderMatchSuccessEvt(String id, String tradeType, String instructionId, String userId, String skId, String chLastModifiedId, Date tsLastModifiedTime, String instrSkId, String instrSkInstr, String skCombi) {
        super(id, tradeType, instructionId);
        this.userId = userId;
        this.skId = skId;
        this.chLastModifiedId = chLastModifiedId;
        this.tsLastModifiedTime = tsLastModifiedTime;
        this.instrSkId = instrSkId;
        this.instrSkInstr = instrSkInstr;
        this.skCombi = skCombi;
    }
}
