package com.example.oems.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CookieUtil {
    public static String getCookie(HttpServletRequest request, String name) {
        String res = null;
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals(name)) res = cookie.getValue();
        }
        return res;
    }

    public static void saveCookie(
            HttpServletResponse response,
            String name,
            String value,
            int maxAge,
            String path
    ) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath(path);
        response.addCookie(cookie);
    }
}
