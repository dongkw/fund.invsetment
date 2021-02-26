package fund.investment.basic.inqresult.api.event;

import fund.investment.basic.common.DomainEvent;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class InquiryResultEvent extends DomainEvent {


    /**
     * 唯一主键,指令表唯一主键，如果同步或者匹配成交后，该字段和公共库指令表值一样;生成规则：日期+时分秒+指令编号（四位数，不足补零）+指令修改次序（三位数，不足补零），如果前面一样时，后面补两位数据序号
     */
    private String skId;

    private String skInquiry;

    private String userId;

    private String chLastModifiedId;

    private Date tsLastModifiedTime;

    public InquiryResultEvent(String id, String skId, String skInquiry) {
        super(id);
        this.skId = skId;
        this.skInquiry = skInquiry;
    }
}
