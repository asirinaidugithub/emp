package com.usr.util;

import lombok.Getter;

import java.util.Map;

@Getter
public class UsrException extends RuntimeException {
    private String message;
    private Map<String, String> errMap;

    public UsrException(String message) {
        this.message = message;
    }

    public UsrException(Map<String, String> map) {
        this.errMap = map;
    }
}
