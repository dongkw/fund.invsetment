package fund.investment.app.pledge.repo.server.service.instruction.saga.create;

import fund.investment.app.pledge.repo.api.event.instruction.PRIstrCreatedEvt;
import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.instruction.server.service.saga.create.IstrCreateCmplTranscation;
import fund.investment.gateway.api.compliance.command.instruction.InstrRiskControlCmd;
import fund.investment.gateway.api.compliance.command.instruction.RollbackCmplIstrCmd;
import fund.investment.gateway.api.compliance.command.instruction.pledge.PRInstrRiskControlCmd;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;

@Slf4j
public class PRInstrCreateCmplTranscationImpl extends IstrCreateCmplTranscation {

    private final PRIstrCreatedEvt pledgeInstrBean;

    public PRInstrCreateCmplTranscationImpl(PRIstrCreatedEvt pledgeInstrBean, CommandGateway commandGateway) {
        super(commandGateway);
        this.pledgeInstrBean = pledgeInstrBean;
    }

    @Override
    protected InstrRiskControlCmd buildInstrCmd() {
        PRInstrRiskControlCmd prInstrRiskControlCmd = new PRInstrRiskControlCmd();
        BeanUtils.copyProperties(pledgeInstrBean, prInstrRiskControlCmd);
        prInstrRiskControlCmd.setId(pledgeInstrBean.getSkInstr());
        return prInstrRiskControlCmd;
    }

    @Override
    protected RollbackCmplIstrCmd buildInstrRollBackCmd() {
        return null;
    }
}
