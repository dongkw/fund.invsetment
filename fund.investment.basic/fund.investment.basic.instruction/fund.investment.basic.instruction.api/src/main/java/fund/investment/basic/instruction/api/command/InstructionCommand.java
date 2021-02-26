package fund.investment.basic.instruction.api.command;


import fund.investment.basic.common.DomainCommand;
import fund.investment.basic.instruction.api.enumeration.TradeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstructionCommand extends DomainCommand{

    private TradeType tradeType;

    /**
     * 唯一主键,指令表唯一主键，如果同步或者匹配成交后，该字段和公共库指令表值一样;生成规则：日期+时分秒+指令编号（四位数，不足补零）+指令修改次序（三位数，不足补零），如果前面一样时，后面补两位数据序号
     */
    private String skId;

    private String userId;

    /**
     * 待修改指令id
     */
    private String skOriginId;

    /**
     * 指令ID,同一笔指令修改多次，该字段的值一样，解决修改前后有成交回报的问题；生成规则：日期+时分秒+指令编码（四位数，不足补零）+指令序号（三位数，不足补零）
     */
    private String skInstr;

    private String chLastModifiedId;

    private Date tsLastModifiedTime;

    public InstructionCommand(TradeType tradeType, String id, String skId, String skInstr) {
        super(id);
        this.tradeType = tradeType;
        this.skId = skId;
        this.skInstr = skInstr;
    }

    public InstructionCommand(TradeType tradeType, String skId, String userId, String skInstr, String chLastModifiedId, Date tsLastModifiedTime) {
        this.tradeType = tradeType;
        this.skId = skId;
        this.userId = userId;
        this.skInstr = skInstr;
        this.chLastModifiedId = chLastModifiedId;
        this.tsLastModifiedTime = tsLastModifiedTime;
    }

    public InstructionCommand(String id, Long requestId, TradeType tradeType, String skId, String userId, String skInstr, String chLastModifiedId, Date tsLastModifiedTime) {
        super(id, requestId);
        this.tradeType = tradeType;
        this.skId = skId;
        this.userId = userId;
        this.skInstr = skInstr;
        this.chLastModifiedId = chLastModifiedId;
        this.tsLastModifiedTime = tsLastModifiedTime;
    }

    public InstructionCommand(String id, TradeType tradeType, String skId, String userId, String skInstr, String chLastModifiedId, Date tsLastModifiedTime) {
        super(id);
        this.tradeType = tradeType;
        this.skId = skId;
        this.userId = userId;
        this.skInstr = skInstr;
        this.chLastModifiedId = chLastModifiedId;
        this.tsLastModifiedTime = tsLastModifiedTime;
    }
}
