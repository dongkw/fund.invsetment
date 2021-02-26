package fund.investment.gateway.server.commandhandler.inquiry.pledge;

import fund.investment.gateway.api.compliance.command.inquriy.CancelCmplInquiryCmd;
import fund.investment.gateway.api.compliance.command.inquriy.pledge.PRDirectCmplInquiryCmd;
import fund.investment.gateway.api.compliance.command.inquriy.pledge.PRModifyCmplInquiryCmd;
import fund.investment.gateway.api.compliance.event.inquiry.InquiryCmplCancelSucceedEvt;
import fund.investment.gateway.api.compliance.event.inquiry.InquiryCmplDirectFailEvt;
import fund.investment.gateway.api.compliance.event.inquiry.InquiryCmplDirectSucceedEvt;
import fund.investment.gateway.api.compliance.event.inquiry.InquiryCmplMotifySucceedEvt;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PRInquiryCommandHandler {
    private final EventGateway eventGateway;

    @Autowired
    public PRInquiryCommandHandler(EventGateway eventGateway) {
        this.eventGateway = eventGateway;

    }

    @CommandHandler
    public void handler(PRDirectCmplInquiryCmd cmd) {
        log.debug("接收到质押式风控校验询价指令：{}", cmd);
//        PledgeInquiryDirectRequest request = new PledgeInquiryDirectRequest();
//        request.setInquiry(cmd);
//        request.setPayChannelCode(PledgeInquiryDirectBizEnum.FD01.getPayChannelCode());
//
//        AjaxResult<InquiryDirectResponse> result = inquiryDirectService.action(request);
        if (true) {
            InquiryCmplDirectSucceedEvt evt = new InquiryCmplDirectSucceedEvt();
            evt.setIstrId(cmd.getSkInquiry());
            evt.setId(cmd.getId());
            evt.setChSourceNo("result.getData().getInquiry().getCSourceNo()");
            evt.setChSourceKey("result.getData().getInquiry().getCSourceKey()");
            evt.setChInstrSource("result.getData().getInquiry().getCInstrSource()");
            eventGateway.publish(evt);
            log.debug("风控校验通过返回校验成功event:{}", evt);
        } else {
            InquiryCmplDirectFailEvt evt = new InquiryCmplDirectFailEvt();
            evt.setIstrId(cmd.getSkInquiry());
            evt.setId(cmd.getId());

            evt.setRiskRiskInfos(evt.getRiskRiskInfos());
            eventGateway.publish(evt);
            log.debug("风控校验通过返回校验失败event:{}", evt);

        }
    }

    @CommandHandler
    public void handler(CancelCmplInquiryCmd cmd) {
        log.debug("接收到质押式风控校验询价指令：{}", cmd);
//        InquiryCancelRequest request = new InquiryCancelRequest();
//        request.setInquiry(cmd);
//        request.setPayChannelCode(PledgeInquiryDirectBizEnum.FD01.getPayChannelCode());
//
//        AjaxResult<InquiryCancelResponse> result = inquiryCancelService.action(request);
        if (true) {
            InquiryCmplCancelSucceedEvt evt = new InquiryCmplCancelSucceedEvt();
            evt.setIstrId(cmd.getIstrId());
            evt.setId(cmd.getId());
            eventGateway.publish(evt);
            log.debug("风控校验通过返回校验成功event:{}", evt);
        } else {
            InquiryCmplDirectFailEvt evt = new InquiryCmplDirectFailEvt();
            evt.setIstrId(cmd.getIstrId());
            evt.setId(cmd.getId());

            evt.setRiskRiskInfos(evt.getRiskRiskInfos());
            eventGateway.publish(evt);
            log.debug("风控校验通过返回校验失败event:{}", evt);

        }
    }

    @CommandHandler
    public void handler(PRModifyCmplInquiryCmd cmd) {
        log.debug("接收到质押式风控校验询价指令：{}", cmd);
//        PledgeInquiryModifyRequest request = new PledgeInquiryModifyRequest();
//        request.setInquiry(cmd);
//        request.setPayChannelCode(PledgeInquiryDirectBizEnum.FD01.getPayChannelCode());
//
//        AjaxResult<InquiryModifyResponse> result = inquiryModifyService.action(request);
        if (true) {
            InquiryCmplMotifySucceedEvt evt = new InquiryCmplMotifySucceedEvt();
            evt.setIstrId(cmd.getSkInquiry());
            evt.setId(cmd.getId());
            evt.setChSourceNo("result.getData().getInquiry().getCSourceNo()");
            evt.setChSourceKey("result.getData().getInquiry().getCSourceKey()");
            evt.setChInstrSource("result.getData().getInquiry().getCInstrSource()");

            eventGateway.publish(evt);
            log.debug("风控校验通过返回校验成功event:{}", evt);
        } else {
            InquiryCmplDirectFailEvt evt = new InquiryCmplDirectFailEvt();
            evt.setIstrId(cmd.getSkInquiry());
            evt.setId(cmd.getId());

            evt.setRiskRiskInfos(evt.getRiskRiskInfos());
            eventGateway.publish(evt);
            log.debug("风控校验通过返回校验失败event:{}", evt);

        }
    }
}
