package fund.investment.instruction.domain.model.eventhandler.saga.util.create.vo;

public enum IstrSagaStatus {
        CMPL_SUCC, CMPL_FAIL, VERF_SUCC, VERF_FAIL, CLOSE_ISTR, CONFIRM_ISTR, ROLLBACK_CMPL, ROLLBACK_VREF,
    }