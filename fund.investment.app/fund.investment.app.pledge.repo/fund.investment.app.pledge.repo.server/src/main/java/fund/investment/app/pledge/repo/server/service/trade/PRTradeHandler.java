package fund.investment.app.pledge.repo.server.service.trade;

import fund.investment.app.pledge.repo.api.command.trade.PRCreateOrderCmd;
import fund.investment.app.pledge.repo.api.valueobject.trade.PledgeTradeElement;
import fund.investment.basic.common.util.uid.UIDGenerator;
import fund.investment.basic.trade.api.valueobject.SourceType;
import fund.investment.basic.trade.server.service.TradeHandler;
import fund.investment.gateway.api.compliance.event.order.OrderCmplCreateEvt;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PRTradeHandler extends TradeHandler<PledgeTradeElement> {

    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    private UIDGenerator uidGenerator;

    //对手方创建

    @EventHandler
    public void handler(OrderCmplCreateEvt<PledgeTradeElement> evt) {
        PRCreateOrderCmd cmd = new PRCreateOrderCmd();
        BeanUtils.copyProperties(evt, cmd);
        cmd.setId(uidGenerator.nextId());
        cmd.setRequestId(uidGenerator.nextId());
        cmd.setSourceType(SourceType.COUNTERPARTY);
        commandGateway.send(cmd);
    }

    //正报
    //已报
    //拒绝报价
    //成交
    //已撤



    //对手方修改



}
