package fund.investment.app.pledge.repo.server.service.instruction.saga.update;

import fund.investment.app.pledge.repo.api.event.instruction.PRIstrUpdatedEvt;
import fund.investment.basic.common.DomainCommand;
import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.instruction.server.service.saga.update.IstrUpdateCmplTranscation;
import fund.investment.gateway.api.compliance.command.instruction.pledge.PRInstrUpdateRiskCmd;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PRInstrUpdateCmplTranscationImpl extends IstrUpdateCmplTranscation {

    private PRIstrUpdatedEvt prIstrUpdatedEvt;


    public PRInstrUpdateCmplTranscationImpl(PRIstrUpdatedEvt prIstrUpdatedEvt) {
        this.prIstrUpdatedEvt = prIstrUpdatedEvt;
    }

    @Override
    protected DomainCommand buildStartCmd() {
        PRInstrUpdateRiskCmd cmd = new PRInstrUpdateRiskCmd();
        BeanUtils.copyProperties(prIstrUpdatedEvt, cmd);
        return cmd;
    }


}
