package fund.investment.basic.common.util.coordination;

import fund.investment.common.DomainCommand;
import fund.investment.common.http.response.pledgerepo.ResultEntity;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nonnull;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Setter
public abstract class Waiter implements Runnable{
    @Nonnull
    private String ObjectKey;
    private CountDownLatch downLatch;
    private volatile AtomicInteger rousers;
    private DomainCommand command;
    public Waiter(String lock, DomainCommand command){
        this.ObjectKey = lock;
        WaitRouserBridge bridge = WaitRouserBridge.bridges.get(lock);
        bridge.downLatch();
        this.rousers = (AtomicInteger) bridge.getRousersMap().getOrDefault(lock, new AtomicInteger(1));
        this.command = command;
    }

    @Override
    public void run() {
        try {
            WaitRouserBridge.results.put(ObjectKey, preHandle(command));
            synchronized (WaitRouserBridge.bridges.get(ObjectKey).getLockedObject()){
                while (rousers.intValue() > 0 ){
                    ObjectKey.wait();
                    log.debug("god lock {} and and this are {} tasks has been waiting...", ObjectKey, rousers.decrementAndGet());
                }
                WaitRouserBridge.latchMap.get(ObjectKey).countDown();
                log.debug("god updated Monitor {} and released. ", ObjectKey);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public abstract ResultEntity preHandle(DomainCommand command);
}