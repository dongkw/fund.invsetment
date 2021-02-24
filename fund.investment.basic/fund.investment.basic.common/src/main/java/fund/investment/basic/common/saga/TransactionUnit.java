package fund.investment.basic.common.saga;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public abstract class TransactionUnit implements ITransaction {
    @Getter
    protected Status status;
    protected List<Class> eventList;

    protected TransactionUnit() {
        this.status = Status.UNFINISHED;
        this.eventList = new ArrayList<>();
    }

    @Override
    public List<Class> getRegEventList() {
        return eventList;
    }
}
