package fund.investment.basic.trade.api.command;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class MatchOrderCmd extends OrderCommand {

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

    public MatchOrderCmd(String id, String instructionId, String tradeType, String userId, String skId, String chLastModifiedId, Date tsLastModifiedTime, String instrSkInstr, String skCombi) {
        super(id, instructionId, tradeType);
        this.userId = userId;
        this.skId = skId;
        this.chLastModifiedId = chLastModifiedId;
        this.tsLastModifiedTime = tsLastModifiedTime;
        this.instrSkInstr = instrSkInstr;
        this.skCombi = skCombi;
    }

}
