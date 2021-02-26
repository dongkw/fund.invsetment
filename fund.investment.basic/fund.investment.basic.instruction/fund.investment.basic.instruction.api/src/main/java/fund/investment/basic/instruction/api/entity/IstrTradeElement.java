package fund.investment.basic.instruction.api.entity;

import fund.investment.basic.instruction.api.command.CreateIstrOrderCmd;
import fund.investment.basic.instruction.api.enumeration.TradeType;
import fund.investment.basic.instruction.api.valueobject.OrderTradeElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IstrTradeElement {

    private TradeType tradeType;

    private String securityCode;

    private long quantity;

    public boolean checkOrder(CreateIstrOrderCmd createIstrOrderCmd) {
        OrderTradeElement orderTradeElement = createIstrOrderCmd.getOrderTradeElement();
        if (Objects.isNull(orderTradeElement)) {
            return true;
        }
        if (!orderTradeElement.getSecurityCode().equals(this.securityCode)) {
            return false;
        }
        if (!orderTradeElement.getTradeType().equals(this.tradeType.name())) {
            return false;
        }
        if (orderTradeElement.getQuantity() - this.quantity > 0) {
            this.quantity -= orderTradeElement.getQuantity();
            return false;
        }
        return true;
    }
}