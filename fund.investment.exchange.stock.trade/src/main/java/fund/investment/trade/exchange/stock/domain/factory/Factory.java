package fund.investment.trade.exchange.stock.domain.factory;

import fund.investment.trade.exchange.stock.domain.model.entity.ExchangeStockFill;
import fund.investment.trade.exchange.stock.domain.report.mock.ZmpPlaceResponse;
import fund.investment.trade.exchange.stock.domain.report.mock.ZmqCancelResponse;
import fund.investment.trade.exchange.stock.domain.report.mock.ZmqCancellationReport;
import fund.investment.trade.exchange.stock.domain.report.mock.ZmqFillReport;
import fund.investment.trade.exchange.stock.domain.report.mock.ZmqPlacedReport;
import infrastructure.trade.domain.model.command.CancelConfirmOrderCmd;
import infrastructure.trade.domain.model.command.FillOrderCmd;
import infrastructure.trade.domain.model.command.PlaceCancelOrderCmd;
import infrastructure.trade.domain.model.command.PlaceConfirmOrderCmd;
import infrastructure.trade.domain.model.command.PlaceOrderCmd;
import infrastructure.trade.exchange.stock.domain.model.command.ESFillOrderCmd;

public class Factory {

	public static PlaceOrderCmd parse(ZmpPlaceResponse source) {
		return new PlaceOrderCmd(
				source.getId(), 
				source.getInstructionId(), 
				null, 
				null);

	}

	public static PlaceConfirmOrderCmd parse(ZmqPlacedReport source) {
		return new PlaceConfirmOrderCmd(
				source.getId(), 
				source.getInstructionId(), 
				null);
	}

	public static PlaceCancelOrderCmd parse(ZmqCancelResponse source) {
		return new PlaceCancelOrderCmd(
				source.getId(), 
				source.getInstructionId(), 
				null, 
				null);
	}

	public static CancelConfirmOrderCmd parse(ZmqCancellationReport source) {
		return new CancelConfirmOrderCmd(
				source.getId(), 
				source.getInstructionId(), 
				null, 
				null,
				source.getCancelQuantity());
	}

	public static FillOrderCmd parse(ZmqFillReport source) {
		ExchangeStockFill fill = source.getFill();
		return new ESFillOrderCmd(
				source.getId(), 
				fill.getInstructionId(), 
				fill.getTradeType(), 
				fill);
	}

}
