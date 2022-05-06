package com.example.oems.lifecycle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <code>LiveData<code/>是一种可以被观察的数据持有类。
 * <p>
 * 当数据发生改变时将会通知<code>Observer</code>
 *
 * @param <T> 持有数据的类型
 * @version 1.0.3
 * @see Observer
 * @see LifecycleOwner
 * @see MutableLiveData
 */
public abstract class LiveData<T> {
    protected final HashMap<LifecycleOwner, Observer<T>> observers;
    protected final List<Observer<T>> observersForever;
    protected T value;

    public LiveData() {
        observers = new HashMap<>();
        observersForever = new ArrayList<>();
    }

    public Map<LifecycleOwner, Observer<T>> getObservers() {
        return observers;
    }

    public List<Observer<T>> getObserversForever() {
        return observersForever;
    }

    public void observe(LifecycleOwner owner, Observer<T> observer) {
        observers.put(owner, observer);
    }

    public void observeForever(Observer<T> observer) {
        observersForever.add(observer);
    }

    public boolean removeObserver(Observer<T> observer) {
        for (Map.Entry<LifecycleOwner, Observer<T>> entry : observers.entrySet()) {
            if (entry.getValue() == observer) {
                observers.remove(entry.getKey());
                return true;
            }
        }
        return observersForever.remove(observer);
    }

    public boolean removeObserver(LifecycleOwner owner) {
        return observers.remove(owner) != null;
    }

    public T getValue() {
        return value;
    }

    public LiveData<T> asLiveData() {
        return this;
    }
}
