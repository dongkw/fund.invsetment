package fund.investment.app.pledge.repo.server.service.instruction.saga.cancel;

import fund.investment.basic.instruction.server.service.saga.IstrVo;
import fund.investment.basic.instruction.server.service.saga.cancel.IstrCancelCmplTranscation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PRInstrCancelCmplTranscationImpl extends IstrCancelCmplTranscation {

    public PRInstrCancelCmplTranscationImpl(IstrVo istrVo) {
        super(istrVo);
    }
}
