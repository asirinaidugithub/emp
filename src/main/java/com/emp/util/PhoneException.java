package com.emp.util;

import lombok.Getter;

import java.util.Map;

@Getter
public class PhoneException extends RuntimeException {
    private String message;
    private Map<String, String> errMap;

    public PhoneException(String message) {
        this.message = message;
    }

    public PhoneException(Map<String, String> map) {
        this.errMap = map;
    }
}
