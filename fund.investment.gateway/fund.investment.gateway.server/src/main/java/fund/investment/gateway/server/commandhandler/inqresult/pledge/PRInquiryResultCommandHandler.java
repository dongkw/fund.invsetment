//package fund.investment.gateway.server.commandhandler.inqresult.pledge;
//
//import fund.investment.app.pledge.repo.api.command.inqresult.PRCreateInquiryResultCmd;
//import fund.investment.basic.inqresult.api.command.RejectInquiryResultCmd;
//import fund.investment.gateway.api.compliance.command.inqresult.BackCmplInqResultCmd;
//import fund.investment.gateway.api.compliance.command.inqresult.CancelCmplInqResultCmd;
//import fund.investment.gateway.api.compliance.command.inqresult.FinishCmplInqResultCmd;
//import fund.investment.gateway.api.compliance.command.inqresult.RejectCmplInqResultCmd;
//import fund.investment.gateway.api.compliance.command.inqresult.pledge.PRDirectCmplInqResultCmd;
//import fund.investment.gateway.api.compliance.command.inqresult.pledge.PRModifyCmplInqResultCmd;
//import fund.investment.gateway.api.compliance.command.inqresult.pledge.PRModifyDraCmplInqResultCmd;
//import fund.investment.gateway.api.compliance.command.inqresult.pledge.PRSubmitCmplInqResultCmd;
//import fund.investment.gateway.api.compliance.event.inqresult.*;
//import lombok.extern.slf4j.Slf4j;
//import org.axonframework.commandhandling.CommandHandler;
//import org.axonframework.commandhandling.gateway.CommandGateway;
//import org.axonframework.eventhandling.gateway.EventGateway;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//
//@Slf4j
//@Component
//public class PRInquiryResultCommandHandler {
//
//    @Autowired
//    private EventGateway eventGateway;
//    @Autowired
//    private CommandGateway commandGateway;
//
//    @CommandHandler
//    public void handler(PRDirectCmplInqResultCmd cmd) {
//        log.debug("接收到询价结果草稿录入：{}", cmd);
////        PRInqResultDraInputRequest request = new PRInqResultDraInputRequest();
////        request.setCmd(cmd);
////        request.setPayChannelCode(PledgeInqResultDraInputBizEnum.FD01.getPayChannelCode());
////        AjaxResult<InqResultDraInputResponse> result = inqResultDraInputService.action(request);
//        if (true) {
//            PRCreateInquiryResultCmd createInquiryResultCmd = new PRCreateInquiryResultCmd();
//            BeanUtils.copyProperties(cmd, createInquiryResultCmd);
//            createInquiryResultCmd.setChSourceKey("result.getData().getCSourceKey()");
//            createInquiryResultCmd.setChInstrSource("result.getData().getCInquireSource()");
//            commandGateway.send(createInquiryResultCmd);
//            InqResultCmplDirectSucceedEvt evt = new InqResultCmplDirectSucceedEvt();
//            evt.setId(cmd.getId());
//            evt.setChSourceKey("result.getData().getCSourceKey()");
//            evt.setChInstrSource("result.getData().getCInquireSource()");
//            eventGateway.publish(evt);
//            log.debug("询价结果草稿录入：{}", createInquiryResultCmd);
//
//        } else {
//            InqResultCmplDirectFailedEvt evt = new InqResultCmplDirectFailedEvt();
//            evt.setId(cmd.getId());
//            eventGateway.publish(evt);
//            log.debug("询价结果草稿录入失败：{}", evt);
//
//        }
//    }
//
//    @CommandHandler
//    public void handler(PRSubmitCmplInqResultCmd cmd) {
//        log.debug("接收到询价结果草稿转正式：{}", cmd);
////        PRInqResultDraToNormalRequest request = new PRInqResultDraToNormalRequest();
////        request.setCmd(cmd);
////        request.setPayChannelCode(PledgeInqResultDraInputBizEnum.FD01.getPayChannelCode());
////        AjaxResult<InqResultDraToNormalResponse> result = inqResultDraToNormalService.action(request);
//        if (true) {
//            InqResultCmplCommitSucceedEvt evt = new InqResultCmplCommitSucceedEvt();
//            evt.setId(cmd.getId());
//            evt.setPassWarn(true);
//            evt.setSuccess(true);
//            eventGateway.publish(evt);
//            log.debug("询价结果草稿转正式成功：{}", evt);
//
//        } else {
//            InqResultCmplCommitFailedEvt evt = new InqResultCmplCommitFailedEvt();
//            evt.setId(cmd.getId());
////            evt.setRiskInfos(result.getData().getRiskRiskInfos());
//            eventGateway.publish(evt);
//            log.debug("询价结果草稿转正式失败：{}", evt);
//
//        }
//    }
//
//    @CommandHandler
//    public void handler(PRModifyCmplInqResultCmd cmd) {
//        log.debug("接收到询价结果正式修改：{}", cmd);
////        PRInqResultModifyRequest request = new PRInqResultModifyRequest();
////        request.setCmd(cmd);
////        request.setPayChannelCode(PledgeInqResultModifyBizEnum.FD01.getPayChannelCode());
////        AjaxResult<InqResultModifyResponse> result = pledgeInqResultModifyService.action(request);
//        if (true) {
//            InqResultCmplModifySucceedEvt evt = new InqResultCmplModifySucceedEvt();
//            evt.setId(cmd.getId());
//            evt.setPassWarn(true);
//            evt.setSuccess(true);
//            eventGateway.publish(evt);
//            log.debug("询价结果草稿正式修改：{}", evt);
//
//        } else {
//            InqResultCmplModifyFailedEvt evt = new InqResultCmplModifyFailedEvt();
//            evt.setId(cmd.getId());
////            evt.setRiskInfos(result.getData().getRiskRiskInfos());
//            eventGateway.publish(evt);
//            log.debug("询价结果草稿正式修改：{}", evt);
//
//        }
//    }
//
//    @CommandHandler
//    public void handler(PRModifyDraCmplInqResultCmd cmd) {
//        log.debug("接收到询价结果草稿修改：{}", cmd);
////        PRInqResultDraModifyRequest request = new PRInqResultDraModifyRequest();
////        request.setCmd(cmd);
////        request.setPayChannelCode(PledgeInqResultDraModifyBizEnum.FD01.getPayChannelCode());
////        AjaxResult<InqResultDraModifyResponse> result = inqResultDraModifyService.action(request);
//        if (true) {
//            InqResultCmplDraModifySucceedEvt evt = new InqResultCmplDraModifySucceedEvt();
//            evt.setId(cmd.getId());
//            evt.setChSourceKey("result.getData().getCSourceKey()");
//            evt.setChInstrSource("result.getData().getCInquireSource()");
//            eventGateway.publish(evt);
//            eventGateway.publish(evt);
//            log.debug("询价结果草稿修改成功：{}", evt);
//
//        } else {
//            InqResultCmplDraModifyFailedEvt evt = new InqResultCmplDraModifyFailedEvt();
//            evt.setId(cmd.getId());
//
//            eventGateway.publish(evt);
//            log.debug("询价结果草稿修改失败：{}", evt);
//
//        }
//    }
//
//    @CommandHandler
//    public void handler(CancelCmplInqResultCmd cmd) {
//        log.debug("接收到询价结果取消：{}", cmd);
////        InqResultCancelRequest request = new InqResultCancelRequest();
////        request.setCmd(cmd);
////        request.setPayChannelCode(InqResultCancelBizEnum.FD01.getPayChannelCode());
////        AjaxResult<InqResultCancelResponse> result = commonInqResultCancelService.action(request);
//        if (true) {
//            InqResultCmplCancelSucceedEvt evt = new InqResultCmplCancelSucceedEvt();
//            evt.setId(cmd.getId());
//            evt.setSuccess(true);
//            evt.setPassWarn(true);
//            eventGateway.publish(evt);
//            eventGateway.publish(evt);
//            log.debug("询价结果草稿取消成功：{}", evt);
//
//        } else {
//            InqResultCmplCancelFailedEvt evt = new InqResultCmplCancelFailedEvt();
//            evt.setId(cmd.getId());
////            evt.setRiskInfos(result.getData().getRiskInfos());
////            evt.setRiskPass(result.getData().getRiskPass());
////            evt.setRiskPassStr(result.getData().getRiskPassStr());
////            evt.setSkProduct(result.getData().getSkProduct());
//            eventGateway.publish(evt);
//            log.debug("询价结果草稿取消失败：{}", evt);
//
//        }
//    }
//
//    @CommandHandler
//    public void handler(BackCmplInqResultCmd cmd) {
//        log.debug("接收到询价结果打回：{}", cmd);
////        InqResultBackRequest request = new InqResultBackRequest();
////        request.setCmd(cmd);
////        request.setPayChannelCode(InqResultCancelBizEnum.FD01.getPayChannelCode());
////        AjaxResult<InqResultBackResponse> result = inqResultBackService.action(request);
//        if (true) {
//            InqResultCmplBackSucceedEvt evt = new InqResultCmplBackSucceedEvt();
//            evt.setId(cmd.getId());
//            eventGateway.publish(evt);
//            eventGateway.publish(evt);
//            log.debug("询价结果草稿取消打回：{}", evt);
//
//        } else {
//            InqResultCmplBacklFailedEvt evt = new InqResultCmplBacklFailedEvt();
//            evt.setId(cmd.getId());
//            eventGateway.publish(evt);
//            log.debug("询价结果草稿取消打回：{}", evt);
//
//        }
//    }
//
//    @CommandHandler
//    public void handler(RejectCmplInqResultCmd cmd) {
//        log.debug("接收到询价结果拒绝：{}", cmd);
////        InqResultRejectServiceRequest request = new InqResultRejectServiceRequest();
////        request.setCmds(Arrays.asList(cmd));
////        request.setPayChannelCode(InqResultCancelBizEnum.FD01.getPayChannelCode());
////        AjaxResult<InqResultRejectServiceResponse> result = inqResultRejectService.action(request);
//
//        if (true) {
//            RejectInquiryResultCmd rejectInquiryResultCmd=new RejectInquiryResultCmd();
//            BeanUtils.copyProperties(cmd,rejectInquiryResultCmd);
//            commandGateway.send(rejectInquiryResultCmd);
//
//            log.debug("询价结果草稿拒绝成功：{}", cmd);
//
//        } else {
//            InqResultCmplRejectFailedEvt evt = new InqResultCmplRejectFailedEvt();
//            evt.setId(cmd.getId());
//            eventGateway.publish(evt);
//            log.debug("询价结果草稿拒绝失败：{}", evt);
//
//        }
//    }
//
//    @CommandHandler
//    public void handler(FinishCmplInqResultCmd cmd) {
//        log.debug("接收到询价结果完成：{}", cmd);
////        InquiryFinishRequest request = new InquiryFinishRequest();
////        request.setCmd(cmd);
////        request.setPayChannelCode(InqResultCancelBizEnum.FD01.getPayChannelCode());
////        AjaxResult<InquiryFinishResponse> result = inqFinishService.action(request);
//
//    }
//
//}
