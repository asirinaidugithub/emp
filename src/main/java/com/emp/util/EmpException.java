package com.emp.util;

import lombok.Getter;

import java.util.Map;

@Getter
public class EmpException extends RuntimeException {
    private String message;
    private Map<String, String> errMap;

    public EmpException(String message) {
        this.message = message;
    }

    public EmpException(Map<String, String> map) {
        this.errMap = map;
    }
}
