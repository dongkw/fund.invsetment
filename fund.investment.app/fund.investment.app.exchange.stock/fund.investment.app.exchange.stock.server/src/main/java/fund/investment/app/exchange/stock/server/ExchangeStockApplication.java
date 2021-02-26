package fund.investment.app.exchange.stock.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author dongkw
 * @Date 2021/2/25、4:19 下午
 **/
@SpringBootApplication
@ComponentScan({"fund.investment.basic.common",
        "fund.investment.basic.instruction.server",
        "fund.investment.basic.trade.server"})
public class ExchangeStockApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExchangeStockApplication.class);
    }
}
