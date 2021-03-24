package fund.investment.gateway.server.commandhandler.trade.pledge;

import fund.investment.app.pledge.repo.api.valueobject.trade.PledgeTradeElement;
import fund.investment.gateway.api.compliance.command.order.*;
import fund.investment.gateway.api.compliance.event.order.*;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PRTradeCommandHandler {
    private final CommandGateway commandGateway;
    private final EventGateway eventGateway;

    @Autowired
    public PRTradeCommandHandler(CommandGateway commandGateway,
                                 EventGateway eventGateway) {
        this.commandGateway = commandGateway;
        this.eventGateway = eventGateway;
    }

    @CommandHandler
    public void handler(PlacingOrderCmplCmd<PledgeTradeElement> cmd) {
        log.debug("接收到质押式委托报价命令：{}", cmd);
        //todo 调接口
        if (true) {
            OrderCmplPlacingEvt<PledgeTradeElement> evt = new OrderCmplPlacingEvt<>();
            BeanUtils.copyProperties(cmd, evt);
            eventGateway.publish(evt);
            log.debug("报价返回成功event:{}", evt);
        } else {
            log.debug("报价返回失败cmd:{}", cmd);
        }
    }

    @CommandHandler
    public void handler(CancelOrderCmplCmd<PledgeTradeElement> cmd) {
        log.debug("接收到质押式委托取消命令：{}", cmd);
        if (true) {
            OrderCmplCancelEvt<PledgeTradeElement> evt = new OrderCmplCancelEvt<>();
            BeanUtils.copyProperties(cmd, evt);
            eventGateway.publish(evt);
            log.debug("取消返回成功event:{}", evt);
        } else {
            log.debug("取消返回失败cmd:{}", cmd);
        }
    }

    @CommandHandler
    public void handler(UpdateOrderCmplCmd<PledgeTradeElement> cmd) {
        log.debug("接收到质押式委托修改命令：{}", cmd);
        if (true) {
            OrderCmplUpdateEvt<PledgeTradeElement> evt = new OrderCmplUpdateEvt<>();
            BeanUtils.copyProperties(cmd, evt);
            eventGateway.publish(evt);
            log.debug("修改返回成功event:{}", evt);
        } else {
            log.debug("修改返回失败cmd:{}", cmd);
        }
    }

    @CommandHandler
    public void handler(MatchOrderCmplCmd<PledgeTradeElement> cmd) {
        log.debug("接收到质押式委托匹配命令：{}", cmd);
        if (true) {
            OrderCmplMatchEvt<PledgeTradeElement> evt = new OrderCmplMatchEvt<>();
            BeanUtils.copyProperties(cmd, evt);
            eventGateway.publish(evt);
            log.debug("匹配返回成功event:{}", evt);
        } else {
            log.debug("匹配返回失败cmd:{}", cmd);
        }
    }

    @CommandHandler
    public void handler(FillOrderCmplCmd<PledgeTradeElement> cmd) {
        log.debug("接收到质押式委托成交命令：{}", cmd);
        if (true) {
            OrderCmplFillEvt<PledgeTradeElement> evt = new OrderCmplFillEvt<>();
            BeanUtils.copyProperties(cmd, evt);
            eventGateway.publish(evt);
            log.debug("成交返回成功event:{}", evt);
        } else {
            log.debug("成交返回失败cmd:{}", cmd);
        }
    }

    @CommandHandler
    public void handler(RejectOrderCmplCmd<PledgeTradeElement> cmd) {
        log.debug("接收到质押式委托拒绝命令：{}", cmd);
        if (true) {
            OrderCmplRejectEvt<PledgeTradeElement> evt = new OrderCmplRejectEvt<>();
            BeanUtils.copyProperties(cmd, evt);
            eventGateway.publish(evt);
            log.debug("拒绝返回成功event:{}", evt);
        } else {
            log.debug("拒绝返回失败cmd:{}", cmd);
        }
    }
}
