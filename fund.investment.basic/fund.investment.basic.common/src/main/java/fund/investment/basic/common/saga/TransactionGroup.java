package fund.investment.basic.common.saga;

import java.util.ArrayList;
import java.util.List;

public abstract class TransactionGroup implements ITransaction {

    protected List<ITransaction> list;

    protected TransactionGroup(List<ITransaction> transactionList) {
        this.list = transactionList;
    }

    public List<Class> getRegEventList() {
        List<Class> eventList = new ArrayList<>();
        for (ITransaction transaction : list) {
            eventList.addAll(transaction.getRegEventList());
        }
        return eventList;
    }
}
