package fund.investment.basic.inqresult.server.saga.update;


import fund.investment.basic.common.DomainCommand;
import fund.investment.basic.common.DomainEvent;
import fund.investment.basic.common.http.response.risk.RiskResultResponse;
import fund.investment.basic.common.saga.CommandGatewayFactory;
import fund.investment.basic.common.saga.Status;
import fund.investment.basic.common.saga.TransactionUnit;
import fund.investment.gateway.api.compliance.event.inqresult.InqResultCmplModifyFailedEvt;
import fund.investment.gateway.api.compliance.event.inqresult.InqResultCmplModifySucceedEvt;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public abstract class InqResultUpdateCmplTranscation extends TransactionUnit {

    protected List<RiskResultResponse> riskInfos;

    public InqResultUpdateCmplTranscation() {
        eventList.add(InqResultCmplModifySucceedEvt.class);
        eventList.add(InqResultCmplModifyFailedEvt.class);
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
        if (event.getClass().isAssignableFrom(InqResultCmplModifySucceedEvt.class)) {
            status = Status.SUCCEED;
        } else if (event.getClass().isAssignableFrom(InqResultCmplModifyFailedEvt.class)) {
            InqResultCmplModifyFailedEvt evt = (InqResultCmplModifyFailedEvt) event;
            riskInfos = evt.getRiskInfos();
            status = Status.FAILED;
        }
    }


}
