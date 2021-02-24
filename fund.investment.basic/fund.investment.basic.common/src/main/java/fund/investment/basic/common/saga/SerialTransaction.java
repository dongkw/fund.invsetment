package fund.investment.basic.common.saga;


import fund.investment.common.DomainCommand;
import fund.investment.common.DomainEvent;

import java.util.List;

public class SerialTransaction extends TransactionGroup {

    private int curIndex;

    public SerialTransaction(List<ITransaction> transactionList) {
        super(transactionList);
        this.curIndex = 0;
    }

    @Override
    public DomainCommand fillCmd(DomainCommand domainCommand) {
        list.forEach(t -> t.fillCmd(domainCommand));
        return domainCommand;
    }

    @Override
    public Status getStatus() {
        if (list.get(list.size() - 1).getStatus() == Status.SUCCEED) {
            return Status.SUCCEED;
        } else if (list.get(0).getStatus() == Status.FAILED) {
            return Status.FAILED;
        } else {
            return Status.UNFINISHED;
        }
    }

    @Override
    public void start() {
        list.get(curIndex).start();
    }

    @Override
    public void rollback() {
        list.get(curIndex).rollback();
    }

    @Override
    public void eventHandler(DomainEvent event) {
        ITransaction transaction = list.get(curIndex);
        transaction.eventHandler(event);
        if (transaction.getStatus() == Status.SUCCEED) {
            if (curIndex < list.size() - 1) {
                curIndex++;
                list.get(curIndex).start();
            }
        } else if (transaction.getStatus() == Status.FAILED) {
            if (curIndex > 0) {
                curIndex--;
                list.get(curIndex).rollback();
            }
        }
    }
}
