package fund.investment.basic.trade.api.command;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CancelOrderCmd extends OrderCommand {

    private String unitId;

    private long cancelQuantity;

    private String userId;
    private String skId;
    private String chLastModifiedId;
    private Date tsLastModifiedTime;

    private  String instrSkInstr;

    /**
     * 指令序号
     **/
    private String insSourceNo;
    /**
     * 指令证券号
     **/
    private String insSourceKey;

    /**
     * 指令来源主键ID:存对应系统的唯一指令的主键
     */
    private String chSourceKey;

    /**
     * 指令来源编号1:存对应系统的指令序号
     */
    private String chSourceNo;

    @ApiModelProperty(value = "委托聚合根id", required = true)
    private String entrustAggrId;

    public CancelOrderCmd(String id, String instructionId, String tradeType, String unitId, long cancelQuantity, String userId, String skId, String chLastModifiedId, Date tsLastModifiedTime) {
        super(id, instructionId, tradeType);
        this.unitId = unitId;
        this.cancelQuantity = cancelQuantity;
        this.userId = userId;
        this.skId = skId;
        this.chLastModifiedId = chLastModifiedId;
        this.tsLastModifiedTime = tsLastModifiedTime;
    }

}
