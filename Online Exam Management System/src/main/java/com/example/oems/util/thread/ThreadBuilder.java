package com.example.oems.util.thread;

public class ThreadBuilder {
    private final Thread thread;

    public ThreadBuilder(Thread thread) {
        this.thread = thread;
    }

    public Thread build() {
        return thread;
    }

    public ThreadBuilder withName(String name) {
        thread.setName(name);
        return this;
    }

    public ThreadBuilder priority(int priority) {
        thread.setPriority(priority);
        return this;
    }
}
