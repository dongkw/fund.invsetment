package fund.investment.basic.instruction.server.service.saga.update;


import fund.investment.basic.common.DomainCommand;
import fund.investment.basic.common.DomainEvent;
import fund.investment.basic.common.http.response.risk.RiskResultResponse;
import fund.investment.basic.common.saga.CommandGatewayFactory;
import fund.investment.basic.common.saga.Status;
import fund.investment.basic.common.saga.TransactionUnit;
import fund.investment.basic.instruction.api.command.UpdateConfirmIstrCmd;
import fund.investment.basic.instruction.api.command.UpdateFailIstrCmd;
import fund.investment.gateway.api.compliance.event.instruction.IstrCmplUpdateFailedEvt;
import fund.investment.gateway.api.compliance.event.instruction.IstrCmplUpdateRollbackedEvt;
import fund.investment.gateway.api.compliance.event.instruction.IstrCmplUpdateSucceedEvt;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;

@Slf4j
public abstract class IstrUpdateCmplTranscation extends TransactionUnit {


    private String chInstrSource;

    private String chSourceKey;

    private String chSourceNo;

    private List<RiskResultResponse> riskRiskInfos;

    public IstrUpdateCmplTranscation() {
        eventList.add(IstrCmplUpdateRollbackedEvt.class);
        eventList.add(IstrCmplUpdateFailedEvt.class);
        eventList.add(IstrCmplUpdateSucceedEvt.class);
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
        if (event.getClass().isAssignableFrom(IstrCmplUpdateSucceedEvt.class)) {
            status = Status.SUCCEED;
            IstrCmplUpdateSucceedEvt succ = (IstrCmplUpdateSucceedEvt) event;
            chInstrSource = succ.getChInstrSource();
            chSourceKey = succ.getChSourceKey();
            chSourceNo = succ.getChSourceNo();
        } else if (event.getClass().isAssignableFrom(IstrCmplUpdateFailedEvt.class)) {
            status = Status.FAILED;
            IstrCmplUpdateFailedEvt evt = (IstrCmplUpdateFailedEvt) event;
            riskRiskInfos = evt.getRiskRiskInfos();
        } else if (event.getClass().isAssignableFrom(IstrCmplUpdateRollbackedEvt.class)) {
            status = Status.FAILED;
        }
    }

    @Override
    public DomainCommand fillCmd(DomainCommand domainCommand) {
        if (Objects.equals(status, Status.SUCCEED)) {
            UpdateConfirmIstrCmd cmd = (UpdateConfirmIstrCmd) domainCommand;
            return cmd;
        } else if (Objects.equals(status, Status.FAILED)) {
            UpdateFailIstrCmd cmd = (UpdateFailIstrCmd) domainCommand;
            cmd.setRiskRiskInfos(riskRiskInfos);
            return cmd;
        } else {
            return domainCommand;
        }
    }
}
