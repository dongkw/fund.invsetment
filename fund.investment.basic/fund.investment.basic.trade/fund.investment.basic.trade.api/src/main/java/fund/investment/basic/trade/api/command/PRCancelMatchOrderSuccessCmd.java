package fund.investment.basic.trade.api.command;


import fund.investment.basic.common.util.BeanUtils;

public class PRCancelMatchOrderSuccessCmd extends PRCancelMatchOrderCmd {

    public PRCancelMatchOrderSuccessCmd(PRCancelMatchOrderCmd cmd) {
        BeanUtils.copyPropertiesIgnoreNull(cmd, this);
    }

}
