package fund.investment.gateway.api.compliance.command.instruction;

import fund.investment.gateway.api.compliance.command.ComplianceCommand;
import lombok.*;

import java.util.Date;

/**
 * @Author dongkw
 * @Date 2020/9/17、15:15
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CancelComplIstrCmd extends ComplianceCommand {

    private String istrId;

    private String cSourceNo;

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
     * 备注
     */
    private String chMemo;


    private String chInstrSource;

    private String chSourceKey;

    private String chSourceNo;

    public CancelComplIstrCmd(Long id, String userId, String skId, String skInstr, String chLastModifiedId, Date tsLastModifiedTime) {
        super(id);
        this.userId = userId;
        this.skId = skId;
        this.skInstr = skInstr;
        this.chLastModifiedId = chLastModifiedId;
        this.tsLastModifiedTime = tsLastModifiedTime;
    }

}
