package fund.investment.app.pledge.repo.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author dongkw
 * @Date 2021/2/24、3:37 下午
 **/
@SpringBootApplication
@ComponentScan({"fund.investment.basic.common",
//        "fund.investment.basic.inquiry.server",
//        "fund.investment.basic.inqresult.server",
//        "fund.investment.basic.trade.server",
        "fund.investment.basic.instruction.server",
        "fund.investment.app.pledge.repo.server"
})

public class PledgeRepoApplication {
    public static void main(String[] args) {
        SpringApplication.run(PledgeRepoApplication.class);
    }
}
