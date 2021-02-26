package fund.investment.basic.inquiry.server.saga.update;


import fund.investment.basic.common.DomainCommand;
import fund.investment.basic.common.DomainEvent;
import fund.investment.basic.common.http.response.risk.RiskResultResponse;
import fund.investment.basic.common.saga.CommandGatewayFactory;
import fund.investment.basic.common.saga.Status;
import fund.investment.basic.common.saga.TransactionUnit;
import fund.investment.gateway.api.compliance.event.inquiry.InquiryCmplMotifyFailEvt;
import fund.investment.gateway.api.compliance.event.inquiry.InquiryCmplMotifySucceedEvt;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public abstract class InquiryUpdateCmplTransaction extends TransactionUnit {

    protected String cInstrSource;
    protected String cSourceKey;
    protected String cSourceNo;
    protected List<RiskResultResponse> riskRiskInfos;

    public InquiryUpdateCmplTransaction() {

        eventList.add(InquiryCmplMotifySucceedEvt.class);
        eventList.add(InquiryCmplMotifyFailEvt.class);
    }

    protected abstract DomainCommand buildStartCmd();

    @Override
    public void start() {
        CommandGatewayFactory.getCommandGateway().send(buildStartCmd());
    }

    @Override
    public void rollback() {
//        CommandGatewayFactory.getCommandGateway().send(buildRollbackCmd());
    }

    @Override
    public void eventHandler(DomainEvent event) {
        if (event.getClass().isAssignableFrom(InquiryCmplMotifySucceedEvt.class)) {
            status = Status.SUCCEED;
        } else if (event.getClass().isAssignableFrom(InquiryCmplMotifyFailEvt.class)) {
            status = Status.FAILED;
        }
    }

}
