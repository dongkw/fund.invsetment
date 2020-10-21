package fund.investment.instruction.domain.model.entity;

import fund.investment.infrastructure.instruction.domain.model.enumeration.OrderStatus;
import fund.investment.infrastructure.instruction.domain.model.event.IstrFillReceivedEvt;
import fund.investment.infrastructure.instruction.domain.model.event.IstrOrderCancelledEvt;
import fund.investment.infrastructure.instruction.domain.model.event.IstrOrderCreatedEvt;
import fund.investment.infrastructure.instruction.domain.model.vo.Order;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@Data
@ApiModel("委托信息")
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {

	@ApiModelProperty(value = "委托状态 ")
    private OrderStatus status = OrderStatus.UNDEFINED;
	
	@ApiModelProperty(value = "委托数量")
	private Long orderQuantity = 0l;

	@ApiModelProperty(value = "成交数量")
	private Long fillQuantity = 0l;

	@ApiModelProperty(value = "取消委托数量")
	private Long cancelQuantity = 0l;
	
	@ApiModelProperty("委托列表")
	List<Order> orders = new LinkedList<>();


	public void createOrder(IstrOrderCreatedEvt istrOrderCreatedEvt){
		this.orderQuantity += istrOrderCreatedEvt.getOrderQuantity();
	}

	public boolean cancelOrder(IstrOrderCancelledEvt istrOrderCancelledEvt){
		this.cancelQuantity += istrOrderCancelledEvt.getCancelQuantity();
		this.orderQuantity -= istrOrderCancelledEvt.getCancelQuantity();
		if(this.orderQuantity == 0 ){
			return true;
		}
		return false;
	}

	public void receiveFill(IstrFillReceivedEvt istrFillReceivedEvt){
		this.fillQuantity += istrFillReceivedEvt.getFillQuantity();
	}

	public OrderDetail(OrderStatus status2, Long total, List<Order> orders2) {
		// TODO Auto-generated constructor stub
		this.status = status2;
		this.orderQuantity = total;
		this.orders = orders2;
	}

}
