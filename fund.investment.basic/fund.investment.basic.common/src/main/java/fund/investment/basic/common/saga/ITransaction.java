package fund.investment.basic.common.saga;



import fund.investment.basic.common.DomainCommand;
import fund.investment.basic.common.DomainEvent;

import java.util.List;

public interface ITransaction {

    Status getStatus();

    void start();

    void rollback();

    List<Class> getRegEventList();

    void eventHandler(DomainEvent event);

    DomainCommand fillCmd(DomainCommand command);
}
