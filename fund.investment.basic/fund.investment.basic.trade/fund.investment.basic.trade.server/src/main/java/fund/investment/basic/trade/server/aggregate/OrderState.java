package fund.investment.basic.trade.server.aggregate;

import fund.investment.basic.trade.api.command.*;
import fund.investment.basic.trade.api.valueobject.TradeElement;

public class OrderState<T extends TradeElement> {


    //创建
    public void handler(OrderAggregate<T> aggregate, CreateOrderConfirmCmd<T> cmd) {

    }

    public void handler(OrderAggregate<T> aggregate, CreateOrderFailedCmd cmd) {

    }

    public void handler(OrderAggregate<T> aggregate, PlacingOrderCmd<T> cmd) {

    }

    public void handler(OrderAggregate<T> aggregate, PlacedOrderCmd<T> cmd) {

    }


    //撤销
    public void handler(OrderAggregate<T> aggregate, CancelOrderCmd cmd) {

    }

    public void handler(OrderAggregate<T> aggregate, CancellingOrderCmd cmd) {

    }

    public void handler(OrderAggregate<T> aggregate, CancelledOrderCmd cmd) {

    }

    public void handler(OrderAggregate<T> aggregate, CancelOrderFailCmd cmd) {

    }
    //修改

    public void handler(OrderAggregate<T> aggregate, UpdateOrderCmd<T> cmd) {

    }

    public void handler(OrderAggregate<T> aggregate, UpdateOrderConfirmCmd<T> cmd) {

    }

    public void handler(OrderAggregate<T> aggregate, UpdateOrderFailCmd cmd) {

    }

    //成交
    public void handler(OrderAggregate<T> aggregate, FillOrderCmd<T> cmd) {

    }

    public void handler(OrderAggregate<T> aggregate, FillOrderConfirmCmd cmd) {

    }

    //拒绝
    public void handler(OrderAggregate<T> aggregate, RejectOrderCmd cmd) {

    }

    public void handler(OrderAggregate<T> aggregate, RejectOrderConfirmCmd cmd) {

    }

    //匹配
    public void handler(OrderAggregate<T> aggregate, MatchOrderCmd cmd) {

    }

    public void handler(OrderAggregate<T> aggregate, MatchOrderConfirmCmd cmd) {

    }

    public void handler(OrderAggregate<T> aggregate, MatchOrderCancelCmd cmd) {

    }
    public void handler(OrderAggregate<T> aggregate, CounterpartyUpdateCmd<T> cmd) {

    }
    public void handler(OrderAggregate<T> aggregate, AutoMatchOrderCmd cmd){

    }
}
