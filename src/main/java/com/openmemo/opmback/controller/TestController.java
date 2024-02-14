package com.openmemo.opmback.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    @GetMapping("/api/normaltest")
    @ResponseBody
    public String normaltest() {
        return "normaltest";
    }

    @GetMapping("/api/authtest")
    @ResponseBody
    public String authtest() {
        return "authtest";
    }

    @GetMapping("/api/usertest")
    @ResponseBody
    public String usertest() {
        return "usertest";
    }

    @PostMapping("/api/userposttest")
    @ResponseBody
    public String userposttest() {
        return "userposttest";
    }

    @GetMapping("/api/admintest")
    @ResponseBody
    public String admintest() {
        return "admintest";
    }

    @GetMapping("/api/anytest")
    @ResponseBody
    public String anytest() {
        return "anytest";
    }
}
