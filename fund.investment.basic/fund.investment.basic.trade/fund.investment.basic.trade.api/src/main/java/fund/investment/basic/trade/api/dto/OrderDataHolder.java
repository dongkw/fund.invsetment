package fund.investment.basic.trade.api.dto;


import java.util.function.Supplier;

public class OrderDataHolder<T> {

    private T orderEvt;

    private final Supplier<T> initEvt;

    public OrderDataHolder(Supplier<T> initEvt) {
        this.initEvt = initEvt;
    }

    public Supplier<T> getInitEvt() {
        return initEvt;
    }

    public T getOrInitOrderEvt() {
        if (orderEvt == null) {
            orderEvt = getInitEvt().get();
        }
        return orderEvt;
    }

    public T getOrderEvt() {
        return orderEvt;
    }

    public void setOrderEvt(T orderEvt) {
        this.orderEvt = orderEvt;
    }
}
