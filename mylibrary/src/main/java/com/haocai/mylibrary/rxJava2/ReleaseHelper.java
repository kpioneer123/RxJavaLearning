package com.haocai.mylibrary.rxJava2;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Xionghu on 2018/6/7.
 * Desc:
 */

public enum ReleaseHelper implements Release {

    RELEASED;

    public static boolean isReleased(Release release) {
        return release == RELEASED;
    }

    public static boolean release(AtomicReference<Release> releaseAtomicReference) {
        Release current = releaseAtomicReference.get();
        Release d = RELEASED;
        if (current != d) {
            current = releaseAtomicReference.getAndSet(d);
            if (current != d) {
                if (current != null) {
                    current.release();
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isReleased() {
        return true;
    }

    @Override
    public void release() {

    }
}
