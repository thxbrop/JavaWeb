package com.example.oems.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

public class SensitiveUtil {
    private static final String[] FILE_NAME = {"sensitive-stop-words/advertisement", "sensitive-stop-words/politics", "sensitive-stop-words/pornographic"};
    private static final Set<String> advertisement = new HashSet<>();
    private static final Set<String> politics = new HashSet<>();
    private static final Set<String> pornographic = new HashSet<>();

    private static boolean isInitialized = false;

    public static void initialize() {
        for (String s : FILE_NAME) {
            InputStream stream = SensitiveUtil.class.getClassLoader().getResourceAsStream(s);
            if (stream == null) continue;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {
                String str;
                while ((str = reader.readLine()) != null) {
                    for (String c : str.split(",")) {
                        switch (s) {
                            case "sensitive-stop-words/advertisement":
                                advertisement.add(c);
                                break;
                            case "sensitive-stop-words/politics":
                                politics.add(c);
                                break;
                            case "sensitive-stop-words/pornographic":
                                pornographic.add(c);
                                break;
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        isInitialized = true;
    }

    public static String filterAdvertisement(String old) throws IOException {
        return filter(old, advertisement);
    }

    public static String filterPolitics(String old) throws IOException {
        return filter(old, politics);
    }

    public static String filterPornographic(String old) throws IOException {
        return filter(old, pornographic);
    }

    private static String filter(String old, Set<String> source) throws IOException {
        if (!isInitialized) throw new IOException("Resources has not been initialized");
        String res = old;
        for (String s : source) {
            res = old.replaceAll(s.trim(), "*");
        }
        return res;
    }

    public static String filterAll(String old) throws IOException {
        return filterPolitics(filterPornographic(filterAdvertisement(old)));
    }
}