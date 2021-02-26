package fund.investment.basic.trade.api.event;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class OrderAutoMatchedEvt extends OrderEvent{

    private String userId;
    private String skId;
    private String chLastModifiedId;
    private Date tsLastModifiedTime;

    @ApiModelProperty(value = "投资指令Id", required = true)
    private String skInstr;

    @ApiModelProperty(value = "投资指令的组合信息", required = true)
    private String skCombi;

    public OrderAutoMatchedEvt(String id, String tradeType, String instructionId, String userId, String skId, String chLastModifiedId, Date tsLastModifiedTime, String skInstr, String skCombi) {
        super(id, tradeType, instructionId);
        this.userId = userId;
        this.skId = skId;
        this.chLastModifiedId = chLastModifiedId;
        this.tsLastModifiedTime = tsLastModifiedTime;
        this.skInstr = skInstr;
        this.skCombi = skCombi;
    }
}
