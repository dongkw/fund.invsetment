package fund.investment.gateway.api.approve.event;

import fund.investment.basic.common.DomainEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ApproveIstrEvt extends DomainEvent{

    private String userId;
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

    public ApproveIstrEvt(String id, String userId, String skId, String skInstr, String chLastModifiedId, Date tsLastModifiedTime, String chFlowApproveStatus) {
        super(id);
        this.userId = userId;
        this.skId = skId;
        this.skInstr = skInstr;
        this.chLastModifiedId = chLastModifiedId;
        this.tsLastModifiedTime = tsLastModifiedTime;
        this.chFlowApproveStatus = chFlowApproveStatus;
    }
}
