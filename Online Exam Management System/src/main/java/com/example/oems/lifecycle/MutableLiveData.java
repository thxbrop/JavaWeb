package com.example.oems.lifecycle;

import java.util.Map;

/**
 * <code>MutableLiveData</code> 是可以修改其持有数据的<code>LiveData</code>
 *
 * @param <T> 持有数据的类型
 * @version 1.0.3
 * @see Observer
 * @see LifecycleOwner
 * @see LiveData
 */
public class MutableLiveData<T> extends LiveData<T> {
    public MutableLiveData() {
    }

    public MutableLiveData(T defValue) {
        super.value = defValue;
    }

    public void setValue(T value) {
        super.value = value;
        for (Map.Entry<LifecycleOwner, Observer<T>> entry : observers.entrySet()) {
            if (entry.getKey().isActive()) {
                entry.getValue().onChanged(value);
            }
        }
        observersForever.forEach(observer -> observer.onChanged(value));
    }
}