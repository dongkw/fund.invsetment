package fund.investment.basic.trade.api.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
public class PROrderMatchRollbackCmd extends OrderCommand {

    private String errorMsg;

    private String userId;
    /**
     * 委托唯一键
     */
    private String skId;
    private String chLastModifiedId;
    private Date tsLastModifiedTime;

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

    public PROrderMatchRollbackCmd(String id, String instructionId, String tradeType, String userId, String skId, String chLastModifiedId, Date tsLastModifiedTime) {
        super(id, instructionId, tradeType);
        this.userId = userId;
        this.skId = skId;
        this.chLastModifiedId = chLastModifiedId;
        this.tsLastModifiedTime = tsLastModifiedTime;
    }

}
