package fund.investment.basic.common.util.coordination;

import fund.investment.common.DomainEvent;
import fund.investment.common.http.response.pledgerepo.ResultEntity;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nonnull;
import java.util.concurrent.CountDownLatch;

@Slf4j
@Getter
public abstract class Rouser implements Runnable{
    @Nonnull
    private Object ObjectKey;
    private CountDownLatch downLatch;
    private DomainEvent event;

    public Rouser(Object lock, DomainEvent event){
        this.ObjectKey = lock;
        this.downLatch = WaitRouserBridge.bridges.get(lock).downLatch();
        this.event = event;
    }

    @Override
    public void run() {
        synchronized (WaitRouserBridge.bridges.get(ObjectKey).getObjectKey()){
            WaitRouserBridge.results.put(ObjectKey.toString(), handle(event));
            WaitRouserBridge.bridges.get(ObjectKey).getLockedObject().notify();
           log.debug("god awaken {}", ObjectKey);
        }
        downLatch.countDown();
    }

    public abstract ResultEntity handle(DomainEvent event);
}