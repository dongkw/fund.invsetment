package fund.investment.basic.common.util.uid;

import java.util.Random;

class SnowFlake {

    private static class TimeBackwardsException extends RuntimeException {

        TimeBackwardsException(String message) {
            super(message);
        }
    }

    /**
     * 起始的时间戳
     * 946656000000L : 2000-01-01 00:00:00
     * 1577808000000L : 2020-01-01 00:00:00
     */
    private static final long START_STAMP = 1577808000000L;

    /**
     * 每一部分占用的位数
     */
    private static final long SEQUENCE_BIT = 12;
    private static final long WORKER_BIT   = 10;

    /**
     * 每一部分的最大值
     */
    private static final long WORKER_MAX   = ~(-1L << WORKER_BIT);
    private static final long SEQUENCE_MAX = ~(-1L << SEQUENCE_BIT);

    /**
     * 每一部分向左的位移
     */
    private static final long WORKER_LEFT    = SEQUENCE_BIT;
    private static final long TIMESTAMP_LEFT = SEQUENCE_BIT + WORKER_BIT;

    /**
     * 实例标识
     */
    private long workerId;

    /**
     * 序列号
     */
    private long sequence = 0L;

    /**
     * 上一次时间戳
     */
    private long lastStamp = -1L;

    SnowFlake(long workerId) {
        if (workerId > WORKER_MAX || workerId < 0) {
            throw new IllegalArgumentException("workerId can't be greater than WORKER_MAX or less than 0");
        }
        this.workerId = workerId;
    }

    /**
     * 产生下一个ID
     */
    synchronized Long nextId() {
        long currStamp = getCurrStamp();
        if (currStamp < lastStamp) {
            throw new TimeBackwardsException("Clock moved backwards.  Refusing to generate id");
        }
        if (currStamp == lastStamp) {
            sequence = (sequence + 1) & SEQUENCE_MAX;
            if (sequence == 0L) {
                currStamp = getNextMill();
                sequence = getNewStartSequence();
            }
        } else {
            sequence = getNewStartSequence();
        }
        lastStamp = currStamp;
        return (currStamp - START_STAMP) << TIMESTAMP_LEFT
               | workerId << WORKER_LEFT
               | sequence;
    }

    /**
     * 获取当前时间戳
     */
    private long getCurrStamp() {
        return System.currentTimeMillis();
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     */
    private long getNextMill() {
        long mill = getCurrStamp();
        while (mill <= lastStamp) {
            mill = getCurrStamp();
        }
        return mill;
    }

    /**
     * 产生新的起始序列号
     * 如果都从 0 开始的话，会导致生成的 ID 都是偶数
     */
    private long getNewStartSequence() {
        return new Random().nextInt(10);
    }
}
