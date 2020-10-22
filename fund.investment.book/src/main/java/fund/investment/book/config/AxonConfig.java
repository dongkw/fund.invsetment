package fund.investment.book.config;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.gateway.CommandGatewayFactory;
import org.axonframework.commandhandling.gateway.IntervalRetryScheduler;
import org.axonframework.commandhandling.gateway.RetryScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @Author dongkw
 * @Date 2020/10/22、10:52 上午
 **/
@Configuration
@ComponentScan({"fund.investment.infrastructure.util"})
public class AxonConfig {

    @Autowired
    private CommandBus commandBus;
    @Autowired
    private CommandInterceptor commandInterceptor;

    @Bean
    public ContractCommandGateway contractCommandGateway() {
//        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
//        RetryScheduler retryScheduler = IntervalRetryScheduler.builder()
//                .retryExecutor(scheduledExecutorService)
//                .maxRetryCount(10)
//                .retryInterval(1000)
//                .build();
        ContractCommandGateway commandGateway = CommandGatewayFactory.builder()
                .commandBus(commandBus)
                .retryScheduler(new CommandRetryScheduler())
                .dispatchInterceptors(commandInterceptor)
                .build()
                .createGateway(ContractCommandGateway.class);
        return commandGateway;
    }

}
