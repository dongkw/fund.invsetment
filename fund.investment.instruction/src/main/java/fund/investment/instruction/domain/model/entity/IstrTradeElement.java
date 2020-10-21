package fund.investment.instruction.domain.model.entity;

import fund.investment.infrastructure.instruction.domain.model.command.CreateIstrOrderCmd;
import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeType;
import fund.investment.infrastructure.instruction.domain.model.vo.OrderTradeElement;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@ApiModel("交易要素")
public class IstrTradeElement {

    @ApiModelProperty(value = "交易类型")
    private TradeType tradeType;
    @ApiModelProperty(value = "证券内码")
    private String securityCode;
    @ApiModelProperty(value = "数量")
    private Long quantity;

    public boolean checkOrder(CreateIstrOrderCmd createIstrOrderCmd){
        OrderTradeElement orderTradeElement = createIstrOrderCmd.getOrderTradeElement();
        if(Objects.isNull(orderTradeElement)){
           return true;
        }
        if(!orderTradeElement.getSecurityCode().equals(this.securityCode)){
            return false;
        }
        if(!orderTradeElement.getTradeType().equals(this.tradeType.name())){
            return false;
        }
        if(orderTradeElement.getQuantity().compareTo(this.quantity) > 0){
            this.quantity -= orderTradeElement.getQuantity();
            return false;
        }
        return true;
    }

}
