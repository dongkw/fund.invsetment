package fund.investment.trade.domain.model.eventhandler.saga.create.valueobject;

/**
 * @Author dongkw
 * @Date 2020/10/10、3:48 下午
 **/
public enum OrderSagaStatus {
    CMPL_SUCC,
    CMPL_FAIL,
    CMPL_ROLLBACK,
    ISTR_SUCC,
    ISTR_FAIL,
    ISTR_ROLLBACK,
    VERF_SUCC,
    VERF_FAIL,
    VERF_ROLLBACK
}