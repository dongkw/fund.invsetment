package fund.investment.trade.domain.model.aggregate.state;

import infrastructure.trade.domain.model.command.CancelConfirmOrderCmd;
import infrastructure.trade.domain.model.command.CancelOrderCmd;
import infrastructure.trade.domain.model.command.ConfirmOrderCmd;
import infrastructure.trade.domain.model.command.FailOrderCmd;
import infrastructure.trade.domain.model.command.FillOrderCmd;
import infrastructure.trade.domain.model.command.PlaceCancelOrderCmd;
import infrastructure.trade.domain.model.command.PlaceConfirmOrderCmd;
import infrastructure.trade.domain.model.command.PlaceOrderCmd;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderState {

	private OrderStatus orderStatus;

	public void createConfirm(ConfirmOrderCmd cmd) {
		
	};

	public void fail(FailOrderCmd cmd) {

	};

	public void placing(PlaceOrderCmd cmd) {

	};

	public void placed(PlaceConfirmOrderCmd cmd) {

	};

	public void cancel(CancelOrderCmd cmd) {

	};

	public void cancelling(PlaceCancelOrderCmd cmd) {

	};

	public void cancelConfirm(CancelConfirmOrderCmd cmd) {

	};

	public void fill(FillOrderCmd cmd) {

	};

}
