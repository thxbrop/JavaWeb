package com.example.oems.util.thread;

public class ThreadUtil {
    public static ThreadBuilder thread(Runnable runnable) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                runnable.run();
            }
        };
        thread.start();
        return new ThreadBuilder(thread);
    }

    //*USELESS
    @Deprecated
    public static void run(Runnable runnable) {
        runnable.run();
    }

    public static void delay(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void repeat(int count, Repeatable repeatable) {
        for (int i = 0; i < count; i++) {
            repeatable.run(i + 1);
        }
    }
}