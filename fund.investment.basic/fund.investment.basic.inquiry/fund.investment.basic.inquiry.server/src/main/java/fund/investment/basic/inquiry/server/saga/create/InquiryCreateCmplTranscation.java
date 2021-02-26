package fund.investment.basic.inquiry.server.saga.create;


import fund.investment.basic.common.DomainCommand;
import fund.investment.basic.common.DomainEvent;
import fund.investment.basic.common.http.response.risk.RiskResultResponse;
import fund.investment.basic.common.saga.CommandGatewayFactory;
import fund.investment.basic.common.saga.Status;
import fund.investment.basic.common.saga.TransactionUnit;
import fund.investment.basic.inquiry.api.command.CreateConfirmInquiryCmd;
import fund.investment.basic.inquiry.api.command.CreateFailInquiryCmd;
import fund.investment.gateway.api.compliance.event.inquiry.InquiryCmplDirectFailEvt;
import fund.investment.gateway.api.compliance.event.inquiry.InquiryCmplDirectSucceedEvt;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;

@Slf4j
public abstract class InquiryCreateCmplTranscation extends TransactionUnit {

    private String cInstrSource;
    private String cSourceKey;
    private String cSourceNo;
    private List<RiskResultResponse> riskRiskInfos;
    public InquiryCreateCmplTranscation() {
        eventList.add(InquiryCmplDirectSucceedEvt.class);
        eventList.add(InquiryCmplDirectFailEvt.class);
//        eventList.add(IstrCmplSucceedEvt.class);
    }

    protected abstract DomainCommand buildStartCmd();


    @Override
    public void start() {
//        InstrRiskControlCmd cmd = new InstrRiskControlCmd(vo.getUnitId(), vo.getInquiryId(), vo.getAmount());
        //TODO 校验风控信息
//        PRDirectCmplInquiryCmd cmd=new PRDirectCmplInquiryCmd();

        CommandGatewayFactory.getCommandGateway().send(buildStartCmd());
    }

    @Override
    public void rollback() {
//        RollbackCmplIstrCmd cmd = new RollbackCmplIstrCmd(vo.getUnitId(), vo.getInquiryId());
//        CommandGatewayFactory.getCommandGateway().send(cmd);
    }

    @Override
    public void eventHandler(DomainEvent event) {
        if (event.getClass().isAssignableFrom(InquiryCmplDirectSucceedEvt.class)) {
            status = Status.SUCCEED;
            InquiryCmplDirectSucceedEvt succ = (InquiryCmplDirectSucceedEvt) event;
            cInstrSource = succ.getChInstrSource();
            cSourceKey = succ.getChSourceKey();
            cSourceNo = succ.getChSourceNo();
        } else if (event.getClass().isAssignableFrom(InquiryCmplDirectFailEvt.class)) {
            status = Status.FAILED;
            InquiryCmplDirectFailEvt fail = (InquiryCmplDirectFailEvt) event;
            riskRiskInfos = fail.getRiskRiskInfos();
        }
    }

    @Override
    public DomainCommand fillCmd(DomainCommand domainCommand) {
        if (Objects.equals(status, Status.SUCCEED)) {
            CreateConfirmInquiryCmd cmd =(CreateConfirmInquiryCmd) domainCommand;
            cmd.setChInstrSource(cInstrSource);
            cmd.setChSourceKey(cSourceKey);
            cmd.setChSourceNo(cSourceNo);
            return cmd;
        } else if (Objects.equals(status, Status.FAILED)) {
            CreateFailInquiryCmd cmd = new CreateFailInquiryCmd();
            cmd = (CreateFailInquiryCmd) domainCommand;
            cmd.setRiskRiskInfos(riskRiskInfos);
            return cmd;
        } else {
            return domainCommand;
        }
    }
}
