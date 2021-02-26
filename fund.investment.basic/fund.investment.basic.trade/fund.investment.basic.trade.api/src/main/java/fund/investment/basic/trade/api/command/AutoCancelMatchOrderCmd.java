package fund.investment.basic.trade.api.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
public class AutoCancelMatchOrderCmd extends OrderCommand{

    private String userId;
    /**
     * 委托唯一键
     */
    private String skId;
    private String chLastModifiedId;
    private Date tsLastModifiedTime;

    private String instrSkId;

    /**
     * 配对标志:数据字典7046：0：未配对1：配对成功2：配对失败
     */
    private String chMatchFlag;

    public AutoCancelMatchOrderCmd(String id, String instructionId, String tradeType, String userId, String skId, String chLastModifiedId, Date tsLastModifiedTime) {
        super(id, instructionId, tradeType);
        this.userId = userId;
        this.skId = skId;
        this.chLastModifiedId = chLastModifiedId;
        this.tsLastModifiedTime = tsLastModifiedTime;
    }

}
