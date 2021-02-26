package fund.investment.basic.inquiry.api.command;

import fund.investment.basic.common.DomainCommand;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class InquiryCommand extends DomainCommand {


    /**
     * 唯一主键,指令表唯一主键，如果同步或者匹配成交后，该字段和公共库指令表值一样;生成规则：日期+时分秒+指令编号（四位数，不足补零）+指令修改次序（三位数，不足补零），如果前面一样时，后面补两位数据序号
     */
    private String userId;

    private String skId;

    private String skInquiry;

    private String chLastModifiedId;

    private Date tsLastModifiedTime;

    public InquiryCommand(String id) {
        super(id);

    }
}
