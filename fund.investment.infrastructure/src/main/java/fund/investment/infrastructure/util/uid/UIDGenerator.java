package fund.investment.infrastructure.util.uid;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author zmami
 */
@Component
public class UIDGenerator {

    @Value("${generator.workerId:0}")
    private int workerId;

    private SnowFlake snowFlake;

    @PostConstruct
    public void init() {
        this.snowFlake = new SnowFlake(workerId);
    }

    public String getId() {
        return String.valueOf(snowFlake.nextId());
    }
}
