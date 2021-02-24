package fund.investment.basic.common.util.uid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

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

    public Long nextId() {
        return snowFlake.nextId();
    }
}
