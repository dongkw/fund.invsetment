package fund.investment.basic.inqresult.server.saga.cancel;


import fund.investment.basic.common.DomainCommand;
import fund.investment.basic.common.DomainEvent;
import fund.investment.basic.common.saga.CommandGatewayFactory;
import fund.investment.basic.common.saga.Status;
import fund.investment.basic.common.saga.TransactionUnit;
import fund.investment.basic.inqresult.server.saga.InqResultVo;
import fund.investment.gateway.api.compliance.command.inqresult.CancelCmplInqResultCmd;
import fund.investment.gateway.api.compliance.event.inqresult.InqResultCmplCancelFailedEvt;
import fund.investment.gateway.api.compliance.event.inqresult.InqResultCmplCancelSucceedEvt;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InqResultCancelCmplTranscation extends TransactionUnit {
    private InqResultVo vo;

    public InqResultCancelCmplTranscation(InqResultVo vo) {
        this.vo = vo;
        eventList.add(InqResultCmplCancelSucceedEvt.class);
        eventList.add(InqResultCmplCancelFailedEvt.class);
    }

    @Override
    public void start() {
        CancelCmplInqResultCmd cmd = new CancelCmplInqResultCmd();
        cmd.setId(vo.getSkId());
        cmd.setSkId(vo.getSkId());
        cmd.setSkInquiry(vo.getSkInquiry());
        cmd.setChLastModifiedId(vo.getLastmodifiedId());
        cmd.setChMemo(vo.getChMemo());
        cmd.setUserId(vo.getUserId());
        CommandGatewayFactory.getCommandGateway().send(cmd);
    }

    @Override
    public void rollback() {
    }

    @Override
    public void eventHandler(DomainEvent event) {
        if (event.getClass().isAssignableFrom(InqResultCmplCancelSucceedEvt.class)) {
            status = Status.SUCCEED;
        } else if (event.getClass().isAssignableFrom(InqResultCmplCancelFailedEvt.class)) {
            status = Status.FAILED;
        }
    }

    @Override
    public DomainCommand fillCmd(DomainCommand command) {
        return null;
    }
}
