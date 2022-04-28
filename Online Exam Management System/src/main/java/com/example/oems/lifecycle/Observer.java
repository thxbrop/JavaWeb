package com.example.oems.lifecycle;

/**
 * 观察者
 *
 * @param <T>
 */
public interface Observer<T> {
    void onChanged(T t);
}