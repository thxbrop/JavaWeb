package com.example.oems;

import com.example.oems.lifecycle.LiveData;
import com.example.oems.lifecycle.MutableLiveData;

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
