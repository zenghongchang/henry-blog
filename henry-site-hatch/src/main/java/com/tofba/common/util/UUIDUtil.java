package com.tofba.common.util;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @功能说明: UUID 生成器
 * @author Henry(fba02)
 * @version [版本号, 2017年8月22日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class UUIDUtil {
    private static boolean IS_THREADLOCALRANDOM_AVAILABLE = false;    
    private static Random random;    
    private static final long leastSigBits;    
    private static final ReentrantLock lock = new ReentrantLock();    
    private static long lastTime;
    
    static {
        try {
            IS_THREADLOCALRANDOM_AVAILABLE = null != UUIDUtil.class.getClassLoader().loadClass("java.util.concurrent.ThreadLocalRandom");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        byte[] seed = new SecureRandom().generateSeed(8);
        leastSigBits = new BigInteger(seed).longValue();
        if (!IS_THREADLOCALRANDOM_AVAILABLE) {
            random = new Random(leastSigBits);
        }
    }
    
    private UUIDUtil() {
    }
    
    /**
     * 生成UUID(JDK自带，去掉中间的'-'字符)
     * 
     * @return
     * @author fwenrong
     */
    public static String generateUUID() {
        String uuid = generateOriginnalUUID();
        uuid = uuid.replaceAll("-", "");
        return uuid;
    }
    
    /**
     * 生成UUID(JDK自带，中间带有'-'分割符)
     * 
     * @return
     * @author 3071
     */
    public static String generateOriginnalUUID() {
        return UUID.randomUUID().toString();
    }
    
    /**
     * Create a new random UUID.
     * 
     * @return
     * @author Henry(fba02)
     * @version [版本号, 2017年11月16日]
     * @see [类、类#方法、类#成员]
     */
    public static UUID random() {
        byte[] randomBytes = new byte[16];
        if (IS_THREADLOCALRANDOM_AVAILABLE) {
            java.util.concurrent.ThreadLocalRandom.current().nextBytes(randomBytes);
        } else {
            random.nextBytes(randomBytes);
        }
        long mostSigBits = 0;
        for (int i = 0; i < 8; i++) {
            mostSigBits = (mostSigBits << 8) | (randomBytes[i] & 0xff);
        }
        long leastSigBits = 0;
        for (int i = 8; i < 16; i++) {
            leastSigBits = (leastSigBits << 8) | (randomBytes[i] & 0xff);
        }
        return new UUID(mostSigBits, leastSigBits);
    }
    
    /**
     * Create a new time-based UUID.
     * 
     * @return
     * @author Henry(fba02)
     * @version [版本号, 2017年11月16日]
     * @see [类、类#方法、类#成员]
     */
    public static UUID create() {
        long timeMillis = (System.currentTimeMillis() * 10000) + 0x01B21DD213814000L;
        lock.lock();
        try {
            if (timeMillis > lastTime) {
                lastTime = timeMillis;
            } else {
                timeMillis = ++lastTime;
            }
        } finally {
            lock.unlock();
        }
        // time low
        long mostSigBits = timeMillis << 32;
        // time mid
        mostSigBits |= (timeMillis & 0xFFFF00000000L) >> 16;
        // time hi and version
        mostSigBits |= 0x1000 | ((timeMillis >> 48) & 0x0FFF); // version 1
        return new UUID(mostSigBits, leastSigBits);
    }
}