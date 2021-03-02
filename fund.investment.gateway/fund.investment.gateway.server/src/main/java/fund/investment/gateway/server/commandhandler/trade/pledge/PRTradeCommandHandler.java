//package fund.investment.gateway.server.commandhandler.trade.pledge;
//
//import fund.investment.basic.common.http.enums.GatewayChannelCodeEnums;
//import fund.investment.basic.common.util.BeanUtils;
//import fund.investment.basic.trade.api.command.*;
//import fund.investment.basic.trade.api.event.FillConfirmingSuccessEvt;
//import fund.investment.basic.trade.api.event.OrderChangedCancellingEvt;
//import fund.investment.basic.trade.api.event.OrderChangedPlacingEvt;
//import fund.investment.basic.trade.api.event.OrderChangedRejectingEvt;
//import fund.investment.gateway.api.compliance.command.order.pledge.PRModifyingOrderCmd;
//import fund.investment.gateway.api.compliance.command.order.pledge.PRPlacingOrderCmd;
//import fund.investment.gateway.api.compliance.event.order.pledge.OrderChangeModifyingEvt;
//import lombok.extern.slf4j.Slf4j;
//import org.axonframework.commandhandling.CommandHandler;
//import org.axonframework.commandhandling.gateway.CommandGateway;
//import org.axonframework.eventhandling.gateway.EventGateway;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Slf4j
//@Component
//public class PRTradeCommandHandler {
//    private final CommandGateway commandGateway;
//    private final EventGateway eventGateway;
//
//    @Autowired
//    public PRTradeCommandHandler(CommandGateway commandGateway,
//                                 EventGateway eventGateway){
//        this.commandGateway = commandGateway;
//        this.eventGateway = eventGateway;
//    }
//
//    @CommandHandler
//    public void handler(PRPlacingOrderCmd cmd) {
//        log.debug("接收到质押式委托报价命令：{}", cmd);
////        PledgeBankOrderReportRequest request = new PledgeBankOrderReportRequest();
////        request.setTradeEntrust(cmd);
////        request.getTradeEntrust().setUserId(cmd.getChCreateId());
////        request.setPayChannelCode(GatewayChannelCodeEnums.FD01.getCode());
////        AjaxResult<BankOrderReportResponse> ajaxResult = pledgeBankOrderReportService.action(request);
//        if (true) {
////            BankOrderReportResponse data = ajaxResult.getData();
//            String cInstrSource = "data.getCInstrSource()";
//            String cSourceKey = "data.getCSourceKey()";
//            String skInstr = "data.getSkInstr()";
//
//            OrderChangedPlacingEvt orderChangedPlacingEvt = new OrderChangedPlacingEvt();
//            orderChangedPlacingEvt.setChInstrSource(cInstrSource);
//            orderChangedPlacingEvt.setChSourceKey(cSourceKey);
//            orderChangedPlacingEvt.setSkInstr(skInstr);
//            orderChangedPlacingEvt.setSkId(cmd.getSkId());
//            eventGateway.publish(orderChangedPlacingEvt);
//            log.debug("报价返回成功event:{}", "ajaxResult");
//        } else {
//            log.debug("报价返回失败event:{}", "ajaxResult");
//        }
//    }
//
//    @CommandHandler
//    public void handler(PRMatchRemoteCmd cmd) {
//        log.debug("接收手动匹配命令：{}", cmd);
////        BankOrderManualMatchInsRequest request = new BankOrderManualMatchInsRequest();
////        request.setTradeEntrust(cmd);
////        request.setPayChannelCode(BankorderManualMatchInsBizEnum.FD01.getPayChannelCode());
////        AjaxResult<BankOrderManualMatchInsResponse> ajaxResult = bankOrderManualMatchInsService.action(request);
//        if (true) {
//            PROrderMatchRemoteSuccessCmd successCmd = new PROrderMatchRemoteSuccessCmd();
//            successCmd.setSkId(cmd.getSkId());
//            successCmd.setSkCombi(cmd.getSkCombi());
//            successCmd.setInstrSkId(cmd.getInstrSkId());
//            successCmd.setChLastModifiedId(cmd.getChLastModifiedId());
//            successCmd.setRequestId(cmd.getRequestId());
//            successCmd.setId(cmd.getId());
//            commandGateway.send(successCmd);
//            log.debug("手动匹配成功cmd:{}", successCmd);
//        } else {
//            PROrderMatchRollbackCmd rollbackCmd = new PROrderMatchRollbackCmd();
//            rollbackCmd.setId(cmd.getId());
//            rollbackCmd.setRequestId(cmd.getRequestId());
//            rollbackCmd.setErrorMsg("ajaxResult.getMessage()");
//            commandGateway.send(rollbackCmd);
//            log.debug("取消匹配失败回滚cmd:{}", rollbackCmd);
//        }
//    }
//    @CommandHandler
//    public void handler(PRCancelMatchRemoteCmd cmd) {
//        log.debug("接收手动匹配命令：{}", cmd);
////        BankOrderCancelMatchInsRequest request = new BankOrderCancelMatchInsRequest();
////        request.setTradeEntrust(cmd);
////        request.setPayChannelCode(BankorderManualMatchInsBizEnum.FD01.getPayChannelCode());
////        AjaxResult<BankOrderCancelMatchInsResponse> ajaxResult = bankOrderUnMatchInsService.action(request);
//        if (true) {
//            PROrderCancelMatchRemoteSuccessCmd successCmd = new PROrderCancelMatchRemoteSuccessCmd();
//            successCmd.setSkId(cmd.getSkId());
//            successCmd.setInstructionId(cmd.getInstructionId());
//            successCmd.setChLastModifiedId(cmd.getChLastModifiedId());
//            successCmd.setRequestId(cmd.getRequestId());
//            successCmd.setId(cmd.getId());
//            commandGateway.send(successCmd);
//            log.debug("取消匹配成功cmd:{}", successCmd);
//        } else {
//            PROrderCancelMatchRollbackCmd rollbackCmd = new PROrderCancelMatchRollbackCmd();
//            rollbackCmd.setId(cmd.getId());
//            rollbackCmd.setRequestId(cmd.getRequestId());
//            rollbackCmd.setErrorMsg("ajaxResult.getMessage()");
//            commandGateway.send(rollbackCmd);
//            log.debug("取消匹配失败回滚cmd:{}", rollbackCmd);
//        }
//    }
//
//
//    @CommandHandler
//    public void handler(PRModifyingOrderCmd cmd) {
//        log.debug("接收到委托修改命令：{}", cmd);
////        PledgeBankReportModifyRequest request = new PledgeBankReportModifyRequest();
////        request.setTradeEntrust(cmd);
//////        request.getTradeEntrust().setUserId(cmd.getChCreateId());
////        request.setPayChannelCode(GatewayChannelCodeEnums.FD01.getCode());
////        AjaxResult<BankOrderReportResponse> ajaxResult = pledgeBankReportModifyService.action(request);
//        if (true) {
////            BankOrderReportResponse data = ajaxResult.getData();
//            String cInstrSource = "data.getCInstrSource()";
//            String cSourceKey = "data.getCSourceKey()";
//            String skInstr = "data.getSkInstr()";
//
//            OrderChangeModifyingEvt evt = new OrderChangeModifyingEvt();
//            BeanUtils.copyProperties(cmd,evt);
//            evt.setChInstrSource(cInstrSource);
//            evt.setChSourceKey(cSourceKey);
//            evt.setSkInstr(skInstr);
//            evt.setRequestId(cmd.getRequestId());
//            eventGateway.publish(evt);
//            log.debug("修改报价请求成功event:{}", "ajaxResult");
//        } else {
//            log.debug("修改报价请求失败event:{}", "ajaxResult");
//        }
//    }
//
//    @CommandHandler
//    public void handler(CancellingOrderCmd cmd) {
//        log.debug("接收到质押式撤销本方委托报价命令：{}", cmd);
////        OrderCancelRequest request = new OrderCancelRequest();
////        request.setTradeEntrust(cmd);
////        request.setPayChannelCode(GatewayChannelCodeEnums.FD01.getCode());
////        AjaxResult<OrderCancelResponse> ajaxResult = bankOrderCancelService.action(request);
//        if (true) {
////            OrderCancelResponse data = ajaxResult.getData();
//            String cInstrSource = "data.getCInstrSource()";
//            String cSourceKey = "data.getCSourceKey()";
//            String skInstr = "data.getSkInstr()";
//            OrderChangedCancellingEvt orderChangedCancellingEvt = new OrderChangedCancellingEvt();
//            BeanUtils.copyPropertiesIgnoreNull(cmd,orderChangedCancellingEvt);
//            orderChangedCancellingEvt.setChInstrSource(cInstrSource);
//            orderChangedCancellingEvt.setChSourceKey(cSourceKey);
//            orderChangedCancellingEvt.setSkInstr(skInstr);
//            orderChangedCancellingEvt.setSkId(cmd.getSkId());
//            orderChangedCancellingEvt.setRequestId(cmd.getRequestId());
//            eventGateway.publish(orderChangedCancellingEvt);
//            log.debug("撤销本方委托报价返回成功event:{}", "ajaxResult");
//        } else {
//            log.debug("撤销本方委托报价返回失败event:{}", "ajaxResult");
//        }
//    }
//
//    @CommandHandler
//    public void handler(RejectingOrderCmd cmd) {
//        log.debug("接收到拒绝委托命令：{}", cmd);
////        BankRejectReportRequest request = new BankRejectReportRequest();
////        request.setTradeEntrust(cmd);
////        request.getTradeEntrust().setUserId(cmd.getUserId());
////        request.setPayChannelCode(GatewayChannelCodeEnums.FD01.getCode());
////        AjaxResult<BankRejectReportResponse> ajaxResult = commonBankOrderRejectService.action(request);
//        if (true) {
////            BankRejectReportResponse data = ajaxResult.getData();
//            String cInstrSource = "data.getCInstrSource()";
//            String cSourceKey = "data.getCSourceKey()";
//            String skInstr = "data.getSkInstr()";
//            OrderChangedRejectingEvt orderChangedRejectingEvt = new OrderChangedRejectingEvt();
//            BeanUtils.copyPropertiesIgnoreNull(cmd,orderChangedRejectingEvt);
//            orderChangedRejectingEvt.setChInstrSource(cInstrSource);
//            orderChangedRejectingEvt.setChSourceKey(cSourceKey);
//            orderChangedRejectingEvt.setSkInstr(skInstr);
//            orderChangedRejectingEvt.setId(cmd.getId());
//            orderChangedRejectingEvt.setRequestId(cmd.getRequestId());
//            eventGateway.publish(orderChangedRejectingEvt);
//            log.debug("拒绝委托命令返回成功event:{}", "ajaxResult");
//        } else {
//            log.debug("拒绝委托命令报价返回失败event:{}", "ajaxResult");
//        }
//    }
//
//    @CommandHandler
//    public void handler(FillConfirmingCmd cmd) {
//        log.debug("接收到确认成交命令：{}", cmd);
////        BankCfmReportRequest request = new BankCfmReportRequest();
////        List<FillConfirmingCmd> cmds = new ArrayList<>();
////        cmds.add(cmd);
////        request.setTradeEntrustRequestList(cmds);
//////        request.getTradeEntrustRequest().setUserId(cmd.getUserId());
////        request.setPayChannelCode(GatewayChannelCodeEnums.FD01.getCode());
////        AjaxResult<BankCfmReportResponse> ajaxResult = bankCfmReportService.action(request);
//        if (true) {
//
//            FillConfirmingSuccessEvt evt = new FillConfirmingSuccessEvt(cmd.getId(),
//                    cmd.getTradeType(),
//                    cmd.getInstructionId(),
//                    cmd.getUserId(),
//                    cmd.getSkId(),
//                    cmd.getChLastModifiedId(),
//                    cmd.getTsLastModifiedTime(),
//                    null);
////            evt.setInsSourceNo(cInstrSource);
////            evt.setChSourceKey(cSourceKey);
////            evt.setInsSourceKey(data.getInsSourceKey());
//            evt.setRequestId(cmd.getRequestId());
//            eventGateway.publish(evt);
//            log.debug("确认命令返回成功event:{}", "ajaxResult");
//        } else {
//            log.debug("确认命令报价返回失败event:{}", "ajaxResult");
//        }
//    }
//
//}
