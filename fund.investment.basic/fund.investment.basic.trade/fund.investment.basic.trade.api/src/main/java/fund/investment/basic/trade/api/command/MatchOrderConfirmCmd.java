package fund.investment.basic.trade.api.command;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 自动匹配
 */
@Getter
@Setter
@NoArgsConstructor
public class MatchOrderConfirmCmd extends OrderCommand {

    private String userId;

    @ApiModelProperty(value = "投资指令的组合信息", required = true)
    private String skCombi;
}
