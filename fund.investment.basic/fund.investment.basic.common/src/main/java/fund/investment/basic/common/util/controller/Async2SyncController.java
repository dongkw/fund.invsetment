package fund.investment.basic.common.util.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.concurrent.*;

@Slf4j
public abstract class Async2SyncController {

    private static Map<Long, Object> responseMap;
    private static Map<Long, CyclicBarrier> lockMap;

    private static final long DEFAULT_TIMEOUT = 10000;

    public Async2SyncController() {
        this.lockMap = new ConcurrentHashMap<>();
        this.responseMap = new ConcurrentHashMap<>();
    }

    protected ResponseEntity waitResponse(Long requestId) {
        Object val;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        try {
            lockMap.put(requestId, cyclicBarrier);
            cyclicBarrier.await(30, TimeUnit.SECONDS);
        } catch (InterruptedException | BrokenBarrierException | TimeoutException e) {
            e.printStackTrace();
        }
        val = responseMap.remove(requestId);
        return ResponseEntity.ok(val);
    }

    protected void addResponse(Long requestId, Object obj) {
        CyclicBarrier cyclicBarrier = lockMap.get(requestId);
        try {
            assert cyclicBarrier != null;
            responseMap.put(requestId, obj);
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }


}
