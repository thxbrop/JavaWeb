package com.example.oems.base;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class CharacterRequest extends HttpServletRequestWrapper {
    private boolean isEncoded = false;

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request the {@link HttpServletRequest} to be wrapped.
     * @throws IllegalArgumentException if the request is null
     */
    public CharacterRequest(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
        return getParameterMap().get(name)[0];
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> map = super.getParameterMap();
        if (isEncoded) return map;
        map.forEach((key, value) -> {
            for (int j = 0; j < value.length; j++) {
                value[j] = new String(value[j].getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            }
        });
        isEncoded = true;
        return map;
    }
}
