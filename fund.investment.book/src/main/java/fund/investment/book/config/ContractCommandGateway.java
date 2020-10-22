package fund.investment.book.config;

import fund.investment.infrastructure.common.DomainCommand;
import org.axonframework.commandhandling.gateway.Timeout;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public interface ContractCommandGateway {

    // fire and forget
    void send(DomainCommand command);

    // method that will wait for a result for 10 seconds
    @Timeout(value = 6, unit = TimeUnit.SECONDS)
    Long sendCommandAndWaitForAResult(DomainCommand command);


    // method that will wait for a result for 10 seconds
    @Timeout(value = 6, unit = TimeUnit.SECONDS)
    void sendCommandAndWait(DomainCommand command);

    // method that attaches meta data and will wait for a result for 10 seconds
//    @Timeout(value = 6, unit = TimeUnit.SECONDS)
//    ContractAggregate sendCommandAndWaitForAResult(AbstractCommand command,
//                                                   @MetaDataValue("userId") String userId);

    // this method will also wait, caller decides how long
    void sendCommandAndWait(DomainCommand command, long timeout, TimeUnit unit) throws TimeoutException, InterruptedException;
}