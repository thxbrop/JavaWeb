package com.example.oems.lifecycle;

import java.util.ArrayList;
import java.util.List;

/**
 * <code>LiveData<code/>是一种可以被观察的数据持有类。
 * <p>
 * 当数据发生改变时将会通知<code>Observer</code>
 *
 * @param <T> 持有数据的类型
 * @see MutableLiveData
 * @see Observer
 */
public abstract class LiveData<T> {
    protected T value;
    protected final ArrayList<Observer<T>> observers;

    public LiveData() {
        observers = new ArrayList<>();
    }

    public List<Observer<T>> getObservers() {
        return observers;
    }

    public void observe(Observer<T> observer) {
        observers.add(observer);
    }

    public boolean removeObserve(Observer<T> observer) {
        return observers.remove(observer);
    }

    public T getValue() {
        return value;
    }

}
