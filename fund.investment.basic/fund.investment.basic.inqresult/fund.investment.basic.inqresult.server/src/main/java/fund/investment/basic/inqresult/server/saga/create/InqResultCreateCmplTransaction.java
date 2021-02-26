package fund.investment.basic.inqresult.server.saga.create;


import fund.investment.basic.common.DomainCommand;
import fund.investment.basic.common.DomainEvent;
import fund.investment.basic.common.http.response.risk.RiskResultResponse;
import fund.investment.basic.common.saga.CommandGatewayFactory;
import fund.investment.basic.common.saga.Status;
import fund.investment.basic.common.saga.TransactionUnit;
import fund.investment.basic.inqresult.api.command.CreateConfirmInquiryResultCmd;
import fund.investment.basic.inqresult.api.command.CreateFailInquiryResultCmd;
import fund.investment.gateway.api.compliance.event.inqresult.InqResultCmplCommitFailedEvt;
import fund.investment.gateway.api.compliance.event.inqresult.InqResultCmplCommitSucceedEvt;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;

@Slf4j
public abstract class InqResultCreateCmplTransaction extends TransactionUnit {
    private List<RiskResultResponse> riskInfos;
    private Boolean success;

    private Boolean passWarn;

    public InqResultCreateCmplTransaction() {

        eventList.add(InqResultCmplCommitFailedEvt.class);
        eventList.add(InqResultCmplCommitSucceedEvt.class);
    }

    protected abstract DomainCommand buildStartCmd();

    @Override
    public void start() {
        CommandGatewayFactory.getCommandGateway().send(buildStartCmd());
    }

    @Override
    public void rollback() {

    }

    @Override
    public void eventHandler(DomainEvent event) {
        if (event.getClass().isAssignableFrom(InqResultCmplCommitSucceedEvt.class)) {
            status = Status.SUCCEED;
            InqResultCmplCommitSucceedEvt evt = (InqResultCmplCommitSucceedEvt) event;
            this.success = evt.getSuccess();
            this.passWarn = evt.getPassWarn();
        } else if (event.getClass().isAssignableFrom(InqResultCmplCommitFailedEvt.class)) {
            status = Status.FAILED;
            InqResultCmplCommitFailedEvt evt = (InqResultCmplCommitFailedEvt) event;
            this.riskInfos = evt.getRiskInfos();
        }
    }

    @Override
    public DomainCommand fillCmd(DomainCommand domainCommand) {
        if (Objects.equals(status, Status.SUCCEED)) {
            CreateConfirmInquiryResultCmd cmd = (CreateConfirmInquiryResultCmd) domainCommand;
            cmd.setSuccess(success);
            cmd.setPassWarn(passWarn);
            return cmd;
        } else if (Objects.equals(status, Status.FAILED)) {
            CreateFailInquiryResultCmd cmd = (CreateFailInquiryResultCmd) domainCommand;
            cmd.setRiskInfos(riskInfos);
            return cmd;
        } else {
            return domainCommand;
        }
    }
}
