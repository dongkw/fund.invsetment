package fund.investment.app.exchange.stock.server.controller.trade.mock;

import fund.investment.app.exchange.stock.server.controller.trade.mock.report.*;
import fund.investment.basic.trade.api.command.*;
import fund.investment.basic.trade.api.valueobject.Fill;

public class MockFactory {

    public static PlaceOrderCmd parse(ZmpPlaceResponse source) {
        return new PlaceOrderCmd();
    }

    public static PlaceConfirmOrderCmd parse(ZmqPlacedReport source) {
        return new PlaceConfirmOrderCmd();
    }

    public static PlaceCancelOrderCmd parse(ZmqCancelResponse source) {
        return new PlaceCancelOrderCmd(
                source.getId(),
                source.getInstructionId(),
                null,
                null,
                source.getUserId(),
                source.getSkId(),
                source.getChLastModifiedId(),
                source.getTsLastModifiedTime());
    }

    public static CancelConfirmOrderCmd parse(ZmqCancellationReport source) {
        return new CancelConfirmOrderCmd(
                source.getId(),
                source.getInstructionId(),
                null,
                source.getUnitId(),
                source.getCancelQuantity(),
                source.getUserId(),
                source.getSkId(),
                source.getChLastModifiedId(),
                source.getTsLastModifiedTime());
    }

    public static FillOrderCmd parse(ZmqFillReport source) {
        Fill fill = source.getFill();
        FillOrderCmd cmd=new FillOrderCmd();
        return cmd;
    }
}
