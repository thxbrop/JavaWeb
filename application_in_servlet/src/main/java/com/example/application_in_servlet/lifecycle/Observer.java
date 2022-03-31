package com.example.application_in_servlet.lifecycle;

/**
 * 观察者
 *
 * @param <T>
 */
public interface Observer<T> {
    void onChanged(T t);
}