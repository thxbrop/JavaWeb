package com.example.oems;

/**
 * 表示任务的执行结果
 * message - 提示信息
 * t - 任务返回的数据
 *
 * @param <T>
 */
public class Result<T> {
    public String message = null;
    public T t = null;

    public Result(String message) {
        this.message = message;
    }

    public Result(T t) {
        this.t = t;
    }
}