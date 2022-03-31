package com.example.application_in_servlet.util;

@SuppressWarnings("unused")
public class StringUtil {
    public static boolean isNull(String s) {
        return s == null;
    }

    public static boolean isNotNull(String s) {
        return !isNull(s);
    }

    public static boolean isEmpty(String s) {
        return s.equals("");
    }

    public static boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }

    public static boolean isNullOrEmpty(String s) {
        return isNull(s) || isEmpty(s);
    }
}
