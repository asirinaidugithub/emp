package com.usr.util;

import lombok.Getter;

import java.util.Map;
@Getter
public class AppException extends RuntimeException {
    private String message;
    private Map<String, String> errMap;

    public AppException(String message) {
        this.message = message;
    }

    public AppException(Map<String, String> map) {
        this.errMap = map;
    }
}
