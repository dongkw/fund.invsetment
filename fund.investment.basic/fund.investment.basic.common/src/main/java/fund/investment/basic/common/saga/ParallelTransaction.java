package fund.investment.basic.common.saga;



import fund.investment.basic.common.DomainCommand;
import fund.investment.basic.common.DomainEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

public class ParallelTransaction extends TransactionGroup {

    private Map<Class, ITransaction> eventMap = new HashMap<>();

    private Map<ITransaction, Object> resultMap = new HashMap<>();

    @Override
    public DomainCommand fillCmd(DomainCommand domainCommand) {
        list.forEach(t -> t.fillCmd(domainCommand));
        return domainCommand;
    }


    public ParallelTransaction(List<ITransaction> transactionList) {
        super(transactionList);
        for (ITransaction transaction : transactionList) {
            for (Class eventClass : transaction.getRegEventList()) {
                eventMap.put(eventClass, transaction);
            }
        }
    }

    @Override
    public Status getStatus() {
        if (list.stream().allMatch(t -> Objects.equals(t.getStatus(), Status.SUCCEED))) {
            return Status.SUCCEED;
        } else if (list.stream().allMatch(t -> Objects.equals(t.getStatus(), Status.FAILED))) {
            return Status.FAILED;
        } else {
            return Status.UNFINISHED;
        }
    }

    @Override
    public void start() {
        list.forEach(ITransaction::start);
    }

    @Override
    public void rollback() {
        list.forEach(ITransaction::rollback);
    }

    @Override
    public void eventHandler(DomainEvent event) {
        ITransaction transaction = eventMap.get(event.getClass());
        transaction.eventHandler(event);
        if (list.stream().noneMatch(t -> Objects.equals(t.getStatus(), Status.UNFINISHED))) {
            if (list.stream().anyMatch(t -> Objects.equals(t.getStatus(), Status.FAILED))) {
                list.stream().filter(t -> t.getStatus() == Status.SUCCEED).forEach(ITransaction::rollback);
            }
        }
    }
}
