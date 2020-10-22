package fund.investment.trade.exchange.stock.mock;

import fund.investment.trade.exchange.stock.domain.model.entity.ExchangeStockFill;
import fund.investment.trade.exchange.stock.mock.report.*;
import infrastructure.trade.domain.model.command.*;
import infrastructure.trade.exchange.stock.domain.model.command.ESFillOrderCmd;
import infrastructure.trade.exchange.stock.domain.model.valueobject.ExchangeStockOrderTradeElement;

import java.math.BigDecimal;

public class MockFactory {

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
                fill,
                new ExchangeStockOrderTradeElement(fill.getTradeType(), null, null, null, 0l, BigDecimal.ZERO));
    }
}
