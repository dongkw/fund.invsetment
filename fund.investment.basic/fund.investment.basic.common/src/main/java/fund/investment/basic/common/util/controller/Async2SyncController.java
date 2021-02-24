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

//    @SuppressWarnings("all")
//    protected ResponseEntity waitResponse(Long requestId) {
//        Object val;
//        if ((val = responseMap.put(requestId, requestId)) == null) {
//            synchronized (requestId) {
//                try {
//                    requestId.wait(DEFAULT_TIMEOUT);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                val = responseMap.get(requestId);
//                return val instanceof Long ? new ResponseEntity<>(HttpStatus.REQUEST_TIMEOUT) : ResponseEntity.ok(val);
//            }
//        }
//        return ResponseEntity.ok(val);
//    }
//
//    @SuppressWarnings("all")
//    protected void addResponse(Long requestId, Object obj) {
//        Object lock = responseMap.put(requestId, obj);
//        if (lock != null) {
//            synchronized (lock) {
//                lock.notify();
//            }
//        }
//    }


    //    protected ResponseEntity waitResponse(Long requestId) {
//        Object val;
//        CountDownLatch countDownLatch = new CountDownLatch(1);
//        try {
//            responseMap.put(requestId, countDownLatch);
//            countDownLatch.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        val = responseMap.get(requestId);
//        return ResponseEntity.ok(val);
//    }
//
//    protected void addResponse(Long requestId, Object obj) {
//        CountDownLatch lock = (CountDownLatch) responseMap.put(requestId, obj);
//        lock.countDown();
//    }
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

//    @EventHandler
//    public void handler(QueryEvent event) {
//        CyclicBarrier cyclicBarrier = lockMap.get(event.getRequestId());
//        if (cyclicBarrier != null) {
//            try {
//                cyclicBarrier.await();
//                lockMap.remove(event.getRequestId());
//            } catch (InterruptedException | BrokenBarrierException e) {
//                e.printStackTrace();
//            }
//        }
//    }


//    protected ResponseEntity waitResponse(Long requestId) {
//        tryLock(requestId);
//        Object val = responseMap.get(requestId);
//        responseMap.remove(requestId);
////        lockMap.remove(requestId);
//        Object val;
//        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
//        try {
//            responseMap.put(requestId, cyclicBarrier);
//            cyclicBarrier.await();
//        } catch (InterruptedException | BrokenBarrierException e) {
//            e.printStackTrace();
//        }
//        val = responseMap.get(requestId);
//        return ResponseEntity.ok(val);
//    }
//
//    protected void addResponse(Long requestId, Object obj) {
//        responseMap.put(requestId, obj);
//        tryLock(requestId);
//    }
//
//    private synchronized void  tryLock(Long requestId) {
//        CyclicBarrier cyclicBarrier;
//        Object lock = lockMap.get(requestId);
//        if (lock != null) {
//            cyclicBarrier = (CyclicBarrier) lock;
//        } else {
//            cyclicBarrier = new CyclicBarrier(2);
//            lockMap.put(requestId, cyclicBarrier);
//        }
//        CyclicBarrier cyclicBarrier = (CyclicBarrier) responseMap.put(requestId, obj);
//        try {
//            cyclicBarrier.await(5, TimeUnit.SECONDS);
//        } catch (TimeoutException e) {
//            log.error("超时");
//            assert cyclicBarrier != null;
//            cyclicBarrier.await();
//        } catch (InterruptedException | BrokenBarrierException e) {
//            log.error("异常");
//            e.printStackTrace();
//        }
//    }
}
