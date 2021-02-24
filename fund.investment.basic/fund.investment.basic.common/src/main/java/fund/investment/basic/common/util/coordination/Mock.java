package fund.investment.basic.common.util.coordination;

import fund.investment.common.DomainCommand;
import fund.investment.common.DomainEvent;
import fund.investment.common.http.response.pledgerepo.ResultEntity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Mock {
    public static void main(String[] args) throws InterruptedException {
        String lock = "mock01";
        WaitRouserBridge bridge = new WaitRouserBridge().addRouser(new WaitRouserConfig(lock))
                .executeWaiter(new Waiter(lock, new DomainCommand("id0001")) {

                    @Override
                    public ResultEntity preHandle(DomainCommand command) {
                        ResultEntity entity = WaitRouserBridge.results.getOrDefault(lock, new ResultEntity());
                        entity.setCommandStatus(Boolean.TRUE);
                        return entity;
                    }
                })
                .sleep(1500)
        ;
        WaitRouserBridge.bridges.get(lock)
                .executeRouser(new Rouser(lock, new DomainEvent("id0001")) {
                    @Override
                    public ResultEntity handle(DomainEvent event) {
                        ResultEntity entity = WaitRouserBridge.results.getOrDefault(lock, new ResultEntity());
                        entity.setComplianceStatus(Boolean.TRUE);
                        return entity;
                    }
                })
                .await()
                .result();

        bridge.shutdown();
    }
}