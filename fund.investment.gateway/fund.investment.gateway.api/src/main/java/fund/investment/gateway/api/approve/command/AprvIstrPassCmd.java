package fund.investment.gateway.api.approve.command;

import fund.investment.basic.common.DomainCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AprvIstrPassCmd extends DomainCommand {

    private String instructionId;

    private String userId;

    private String operatorId;

    /**
     * 唯一主键,指令表唯一主键，如果同步或者匹配成交后，该字段和公共库指令表值一样;生成规则：日期+时分秒+指令编号（四位数，不足补零）+指令修改次序（三位数，不足补零），如果前面一样时，后面补两位数据序号
     */
    private String skId;

    /**
     * 指令ID,同一笔指令修改多次，该字段的值一样，解决修改前后有成交回报的问题；生成规则：日期+时分秒+指令编码（四位数，不足补零）+指令序号（三位数，不足补零）
     */
    private String skInstr;

    /**
     * 修改人
     */
    private String chLastModifiedId;

    /**
     * 修改时间
     */
    private Date tsLastModifiedTime;

    /**
     * 指令审批状态
     */
    private String chFlowApproveStatus;

    public AprvIstrPassCmd(String id, String instructionId, String userId, String operatorId) {
        super(id);
        this.instructionId = instructionId;
        this.userId = userId;
        this.operatorId = operatorId;
    }
}
