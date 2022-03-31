package com.example.application_in_servlet;

import com.example.application_in_servlet.lifecycle.LiveData;
import com.example.application_in_servlet.lifecycle.MutableLiveData;

import java.util.HashMap;
import java.util.Map;

public class MyDemo {
    private static final String THREAD_A = "thread-A";
    private static final String THREAD_B = "thread-B";

    public static void main(String[] args) {
        LiveData<String> liveData1 = LiveDataProvider.getLiveData(THREAD_A);
        LiveData<String> liveData2 = LiveDataProvider.getLiveData(THREAD_B);
        liveData1.observe(System.out::println);
        liveData2.observe(System.out::println);
        LiveDataProvider.start(THREAD_A, 1000, 5);
        LiveDataProvider.start(THREAD_B, 500, 10);
    }
}

/**
 * 演示多个LiveData在各自线程上更新的任务
 */
class LiveDataProvider {
    private static Map<String, MutableLiveData<String>> liveDataMap;

    /**
     * 使<code>name<code/>对应的LiveData开始在新开辟的线程上以间隔<code>delay<code/>时间更新总计<code>count<code/>条数据
     *
     * @param name  用于识别LiveData的标记
     * @param delay 每次更新时间间隔
     * @param count 总计更新次数
     */
    public static void start(String name, long delay, int count) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                for (int i = 0; i < count; i++) {
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    liveDataMap.get(name).setValue("[" + name + "] 这是第" + (i + 1) + "条数据");
                }
            }
        }.start();
    }

    /**
     * 根据<code>name<code/>获取对应LiveData
     *
     * @param name LiveData对应的标记
     * @return 不可修改的LiveData
     */
    public static LiveData<String> getLiveData(String name) {
        if (liveDataMap == null) liveDataMap = new HashMap<>();
        if (!liveDataMap.containsKey(name)) liveDataMap.put(name, new MutableLiveData<>());
        return liveDataMap.get(name);
    }
}




