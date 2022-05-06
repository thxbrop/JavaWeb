package com.example.oems;

import java.io.CharArrayWriter;
import java.text.SimpleDateFormat;
import java.util.Collection;

public class Logger {
    private static final String DEFAULT_TAG = "Logger";
    private static final SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");

    public static <T> void v(T t) {
        System.out.println(t);
    }

    public static <T> void t(T t) {
        t(DEFAULT_TAG, t);
    }

    public static <T> void t(String tag, T t) {
        System.out.println('[' + tag + ' ' + currentTime() + "] " + t);
    }

    public static <T> void v(Collection<T> collection) {
        collection.forEach(Logger::v);
    }

    public static <T> void e(T t) {
        System.err.println(t);
    }

    public static <T> void et(T t) {
        et(DEFAULT_TAG, t);
    }

    public static <T> void et(String tag, T t) {
        System.err.println('[' + tag + ' ' + currentTime() + "] " + t);
    }

    private static String currentTime() {
        return format.format(System.currentTimeMillis());
    }

    public static void main(String[] args) {
        CharArrayWriter writer = new CharArrayWriter().append("你哈");
        System.out.println(writer.toString());
    }
}
