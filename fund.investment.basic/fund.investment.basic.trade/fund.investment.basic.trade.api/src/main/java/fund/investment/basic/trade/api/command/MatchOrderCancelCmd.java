package fund.investment.basic.trade.api.command;

import lombok.Getter;
import lombok.Setter;

/**
 * 取消匹配
 */
@Getter
@Setter
public class MatchOrderCancelCmd extends OrderCommand {
    private String userId;
    /**
     * 配对标志:数据字典7046：0：未配对1：配对成功2：配对失败
     */
    private String chMatchFlag;

}
