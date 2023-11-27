package com.emp.modal;

import java.util.HashMap;
import java.util.Map;

public class Response {
    private boolean status = Boolean.TRUE;
    private String message;
    private Map<String, String> errMap = new HashMap<>();
}
