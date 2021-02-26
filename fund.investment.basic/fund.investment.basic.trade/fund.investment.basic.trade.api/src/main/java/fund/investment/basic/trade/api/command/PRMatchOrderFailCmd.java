package fund.investment.basic.trade.api.command;

import fund.investment.basic.common.util.BeanUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PRMatchOrderFailCmd extends PRMatchOrderCmd {

    public PRMatchOrderFailCmd(PRMatchOrderCmd cmd) {
        BeanUtils.copyPropertiesIgnoreNull(cmd, this);
    }

    private String errorMsg;

}
