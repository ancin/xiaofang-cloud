package com.diandian.common.utils;

import lombok.extern.slf4j.Slf4j;


/**
 * @ClassName GenerateSequenceUtil
 * @description:
 * @author: ancin
 * @create: 2020-02-10 10:12
 * @Version 1.0
 **/
@Slf4j
public class GenerateSequenceUtil {


    private static long workerId = 0;
    private static long twepoch = 1303895660503L;
    private static long sequence = 0L;
    private static long workerIdBits = 10L;
    private static long maxWorkerId = -1L ^ -1L << workerIdBits;
    private static long sequenceBits = 12L;

    private static long workerIdShift = sequenceBits;
    private static long timestampLeftShift = sequenceBits + workerIdBits;
    private static long sequenceMask = -1L ^ -1L << sequenceBits;

    private static long lastTimestamp = -1L;

    /**
     * 时间格式生成序列
     *
     * @return String
     */
    public static synchronized String generateSequence() {


        return nextId() + "";
    }


    public static synchronized long nextId() {
        long timestamp = timeGen();
        if (lastTimestamp == timestamp) {
            sequence = sequence + 1 & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }


        lastTimestamp = timestamp;
        return timestamp - twepoch << timestampLeftShift | workerId << workerIdShift
                | sequence;
    }

    private static long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }


    private static long timeGen() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        System.out.println(nextId());
    }
}
