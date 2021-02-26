package fund.investment.basic.instruction.server.service.saga.create;


import fund.investment.basic.common.DomainCommand;
import fund.investment.basic.common.DomainEvent;
import fund.investment.basic.common.http.response.risk.RiskResultResponse;
import fund.investment.basic.common.saga.Status;
import fund.investment.basic.common.saga.TransactionUnit;
import fund.investment.basic.instruction.api.command.CreateConfirmIstrCmd;
import fund.investment.basic.instruction.api.command.CreateFailIstrCmd;
import fund.investment.gateway.api.compliance.command.instruction.InstrRiskControlCmd;
import fund.investment.gateway.api.compliance.command.instruction.RollbackCmplIstrCmd;
import fund.investment.gateway.api.compliance.event.instruction.IstrCmplFailedEvt;
import fund.investment.gateway.api.compliance.event.instruction.IstrCmplRollbackedEvt;
import fund.investment.gateway.api.compliance.event.instruction.IstrCmplSucceedEvt;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;

import java.util.List;
import java.util.Objects;

@Slf4j
public abstract class IstrCreateCmplTranscation extends TransactionUnit {

    private final transient CommandGateway commandGateway;

    private String cInstrSource;
    private String cSourceKey;
    private String cSourceNo;
    private List<RiskResultResponse> riskRiskInfos;


    public IstrCreateCmplTranscation(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
        eventList.add(IstrCmplRollbackedEvt.class);
        eventList.add(IstrCmplFailedEvt.class);
        eventList.add(IstrCmplSucceedEvt.class);
    }


    public DomainCommand fillCmd(DomainCommand domainCommand) {
        if (Objects.equals(status, Status.SUCCEED)) {
            CreateConfirmIstrCmd cmd = (CreateConfirmIstrCmd) domainCommand;
            cmd.setChInstrSource(cInstrSource);
            cmd.setChSourceKey(cSourceKey);
            cmd.setChSourceNo(cSourceNo);
            return cmd;
        } else if (Objects.equals(status, Status.FAILED)) {
            CreateFailIstrCmd cmd = (CreateFailIstrCmd) domainCommand;
            cmd.setRiskRiskInfos(riskRiskInfos);
            return cmd;
        } else {
            return domainCommand;
        }

    }

    @Override
    public void start() {
        InstrRiskControlCmd cmd = this.buildInstrCmd();
        commandGateway.send(cmd);
    }

    protected abstract InstrRiskControlCmd buildInstrCmd();

    @Override
    public void rollback() {
        RollbackCmplIstrCmd cmd = this.buildInstrRollBackCmd();
        commandGateway.send(cmd);
    }

    protected abstract RollbackCmplIstrCmd buildInstrRollBackCmd();


    @Override
    public void eventHandler(DomainEvent event) {
        if (event.getClass().isAssignableFrom(IstrCmplSucceedEvt.class)) {
            status = Status.SUCCEED;
            IstrCmplSucceedEvt succ = (IstrCmplSucceedEvt) event;
            cInstrSource = succ.getChInstrSource();
            cSourceKey = succ.getChSourceKey();
            cSourceNo = succ.getChSourceNo();
        } else if (event.getClass().isAssignableFrom(IstrCmplFailedEvt.class)) {
            status = Status.FAILED;
            IstrCmplFailedEvt fail = (IstrCmplFailedEvt) event;
            riskRiskInfos = fail.getRiskRiskInfos();

        } else if (event.getClass().isAssignableFrom(IstrCmplRollbackedEvt.class)) {
            status = Status.FAILED;
        }
    }
}
