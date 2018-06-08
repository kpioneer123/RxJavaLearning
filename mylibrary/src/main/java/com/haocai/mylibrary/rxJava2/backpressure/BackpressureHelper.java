package com.haocai.mylibrary.rxJava2.backpressure;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Xionghu on 2018/6/8.
 * Desc:
 */

public final class BackpressureHelper {
    public static void add(AtomicLong requested, long n) {
        long r = requested.get();
        if (r == Long.MAX_VALUE) {
            return;
        }
        long u = r + n;
        if (u < 0L) {
            u = Long.MAX_VALUE;
        }
        // compareAndSet：如果当前值 == 预期值，则以原子方式将该值设置为给定的更新值。这里需要注意的是这个方法的返回值实际上是是否成功修改，而与之前的值无关。
        requested.compareAndSet(r, u);//把requested中的值设置为u
    }

    public static void produced(AtomicLong requested, long n) {
        long current = requested.get();
        if (current == Long.MAX_VALUE) {
            return;
        }
        long update = current - n;
        if (update < 0L) {
            update = 0L;
        }
        requested.compareAndSet(current, update);
    }
}
