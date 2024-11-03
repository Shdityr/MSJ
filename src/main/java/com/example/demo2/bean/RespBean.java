package com.example.demo2.bean;

public record RespBean(String status, String msg, Object data) {
    public RespBean(){this(null, null, null);}
}

