package fund.investment.trade.exchange.stock.domain.eventhandler.saga.utils.create.vo;

import lombok.Data;

import java.util.Set;

/**
 * @Author dongkw
 * @Date 2020/10/10、3:46 下午
 **/
@Data
public class OrderVo {

    private String orderId;
    private String istrId;
    private String unitId;
    private String securityCode;
    private Set<OrderSagaStatus> statuses;
}
