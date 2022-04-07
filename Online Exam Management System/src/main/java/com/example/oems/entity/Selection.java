package com.example.oems.entity;

import com.google.gson.Gson;

public class Selection {

    private String a, b, c, d;

    public Selection() {
    }

    public Selection(String a, String b, String c, String d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public String getA() {
        return a;
    }

    public String getB() {
        return b;
    }

    public String getC() {
        return c;
    }

    public String getD() {
        return d;
    }

    @Override
    public String toString() {
        return "[" +
                "A:'" + a + '\'' +
                ", B:'" + b + '\'' +
                ", C:'" + c + '\'' +
                ", D:'" + d + '\'' +
                ']';
    }

    public String toJson() {
        return new Gson().toJson(this);
    }

    public static Selection parseJson(String json) {
        return new Gson().fromJson(json, Selection.class);
    }
}