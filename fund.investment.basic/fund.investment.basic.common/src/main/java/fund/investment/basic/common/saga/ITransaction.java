package fund.investment.basic.common.saga;


import fund.investment.common.DomainCommand;
import fund.investment.common.DomainEvent;

import java.util.List;

public interface ITransaction {

    Status getStatus();

    void start();

    void rollback();

    List<Class> getRegEventList();

    void eventHandler(DomainEvent event);

    DomainCommand fillCmd(DomainCommand command);
}
