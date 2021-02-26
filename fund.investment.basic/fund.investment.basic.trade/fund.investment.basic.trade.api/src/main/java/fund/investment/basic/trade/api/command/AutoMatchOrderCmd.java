package fund.investment.basic.trade.api.command;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class AutoMatchOrderCmd extends OrderCommand {

    private String userId;
    private String skId;
    private String chLastModifiedId;
    private Date tsLastModifiedTime;
    private String skTradeUserId;

    @ApiModelProperty(value = "投资指令Id", required = true)
    private String skInstr;

    @ApiModelProperty(value = "投资指令的组合信息", required = true)
    private String skCombi;

    /**
     * 配对标志:数据字典7046：0：未配对1：配对成功2：配对失败
     */
    private String chMatchFlag;

    public AutoMatchOrderCmd(String id, String instructionId, String tradeType, String userId, String skId, String chLastModifiedId, Date tsLastModifiedTime, String skInstr, String skCombi) {
        super(id, instructionId, tradeType);
        this.userId = userId;
        this.skId = skId;
        this.chLastModifiedId = chLastModifiedId;
        this.tsLastModifiedTime = tsLastModifiedTime;
        this.skInstr = skInstr;
        this.skCombi = skCombi;
    }

}
