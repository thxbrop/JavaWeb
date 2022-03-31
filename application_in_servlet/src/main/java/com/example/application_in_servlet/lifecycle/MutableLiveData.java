package com.example.application_in_servlet.lifecycle;

/**
 * <code>MutableLiveData</code> 是可以修改其持有数据的<code>LiveData</code>
 *
 * @param <T> 持有数据的类型
 * @see LiveData
 * @see Observer
 */
public class MutableLiveData<T> extends LiveData<T> {
    public void setValue(T value) {
        super.value = value;
        for (Observer<T> observer : observers) observer.onChanged(value);
    }
}