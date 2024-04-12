package com.emp.controller;

import com.emp.entity.Employee;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping(value = "/")
    public String index() {
        return "<h2>Welcome</h2";
    }

    @GetMapping(value = "/admin")
    public String admin() {
        return "<h2>Welcome admin</h2";
    }

    @GetMapping(value = "/user")
    public String user() {
        return "<h2>Welcome to user</h2";
    }

    @GetMapping(value = "/adviser")
    public String adviser() {
        return "<h2>Welcome to adviser</h2";
    }

    @PostMapping(value = "/adviser-info")
    public String adviserInfo() {
        Employee emp = Employee.builder().email("email").lastName("last name").build();
        return new GsonBuilder().create().toJson(emp);
    }
}
