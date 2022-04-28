package com.example.oems;

import com.example.oems.lifecycle.LiveData;
import com.example.oems.lifecycle.MutableLiveData;

import static com.example.oems.util.thread.ThreadUtil.*;

public class GlobalTaskManager {
    private static GlobalTaskManager INSTANCE = null;
    private final MutableLiveData<Integer> requestsLiveData = new MutableLiveData<>(0);

    private GlobalTaskManager() {

    }

    public static GlobalTaskManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GlobalTaskManager();
        }
        return INSTANCE;
    }

    public LiveData<Integer> getLiveData() {
        return requestsLiveData;
    }

    public MutableLiveData<Integer> getMutableLiveData() {
        return requestsLiveData;
    }
}


class A {
    public static void main(String[] args) {
        GlobalTaskManager manager = GlobalTaskManager.getInstance();
        manager.getLiveData().observe(System.out::println);
        thread(() -> {
            MutableLiveData<Integer> liveData = manager.getMutableLiveData();
            repeat(10, count -> {
                System.out.println(Thread.currentThread().getName());
                liveData.setValue(count);
                delay(1000);
            });
        });
    }
}
