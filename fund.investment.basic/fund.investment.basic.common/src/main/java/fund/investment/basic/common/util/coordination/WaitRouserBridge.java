package fund.investment.basic.common.util.coordination;

import com.google.gson.Gson;
import fund.investment.common.http.response.pledgerepo.ResultEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * one waiter with many routers notification
 * @param <V>
 */
@Getter
@Setter
@Slf4j
public class WaitRouserBridge<V> {
    private int DEFAULT_NUM_OF_ROUSERS = 1;
    public enum  Key {
        COMPLAINCE("complaince"),
        ACCOUNT_BOOK("account_book");

        Key(String complaince) {
        }
    }
    public static Map<String, WaitRouserBridge> bridges = new ConcurrentHashMap<>();
    public static Map<String, ResultEntity> results = new ConcurrentHashMap<>();
    public static Map<String, CountDownLatch> latchMap = new ConcurrentHashMap<>();
    private String ObjectKey;
    private Map<String, AtomicInteger> rousersMap;
    private ExecutorService service;
    private void init(WaitRouserConfig waitRouterConfig){
        initContainer();
        configRouser(waitRouterConfig);
    }
    private void initContainer(){
        this.rousersMap = new ConcurrentHashMap<>();
        this.service = Executors.newCachedThreadPool();
    }
    private void configRouser(WaitRouserConfig waitRouterConfig){
        getRousersMap().put(
                waitRouterConfig.getObjectKey(), new AtomicInteger(DEFAULT_NUM_OF_ROUSERS));
        latchMap.put(
                waitRouterConfig.getObjectKey(), downLatch());
    }
    public WaitRouserBridge(){
        initContainer();
        ObjectKey = "";
    }
    public WaitRouserBridge(WaitRouserConfig waitRouterConfig){
        init(waitRouterConfig);
        bridges.put(waitRouterConfig.getObjectKey(), this);
        this.ObjectKey = waitRouterConfig.getObjectKey();
    }
    public synchronized  CountDownLatch downLatch(){
        if (latchMap.get(String.valueOf(this.ObjectKey)) != null){
            return latchMap.get(getObjectKey());
        }
        CountDownLatch downLatch = new CountDownLatch(
                Math.addExact(
                        rousersMap.getOrDefault(getObjectKey(), new AtomicInteger(0)).intValue(),
                        1));
        latchMap.put(getObjectKey(), downLatch);
        return downLatch;
    }
    public WaitRouserBridge addRouser(WaitRouserConfig waitRouterConfig){
        configRouser(waitRouterConfig);
        bridges.put(waitRouterConfig.getObjectKey(), this);
        this.ObjectKey = waitRouterConfig.getObjectKey();
        return this;
    }
    public WaitRouserBridge executeWaiter(Waiter task){
        V v = (V) new Integer(0);
        Future<?> fu = getService().submit(task, v);
        return this;
    }
    public WaitRouserBridge sleep(long millis) throws InterruptedException {
        Thread.sleep(millis);
        return this;
    }
    public WaitRouserBridge executeRouser(Rouser task){
        if (rousersMap.get(task.getObjectKey().toString()).intValue() <= 0){
            log.debug("Routers has be finished, skipped this");
            return this;
        }
        getService().execute(task);
        return this;
    }
    public WaitRouserBridge executeMultiRousers(List<Rouser> tasks){
        tasks.stream().forEach(task -> {
            if (rousersMap.get(task.getObjectKey().toString()).intValue() <= 0){
                log.debug("Routers has be finished, skipped this");
                return;
            }
            getService().execute(task);
        });
        return this;
    }
    public void shutdown(){
        if (!getService().isShutdown())
            getService().shutdown();
    }
    public WaitRouserBridge await() {
        try {
            downLatch().await();
            log.info("waiters and rousers has been down.");
        } catch (Exception e){
            e.printStackTrace();
            log.info("waiters and rousers trys to down, but failed.");
        }
        return this;
    }
    public Object result(){
        Object rs = WaitRouserBridge.results.get(ObjectKey);
        log.debug("find reslut : {}", new Gson().toJson(rs));
        if (rs != null){
            clearCahce();
        }
        return rs;
    }
    private void clearCahce(){
        WaitRouserBridge.results.remove(ObjectKey);
    }
    public String getLockedObject(){
        if (bridges.containsKey(ObjectKey)){
            return bridges.keySet().stream().filter(pre -> pre.equalsIgnoreCase(ObjectKey)).findAny().orElse("");
        }
        return "";
    }
}