package com.openmemo.opmback.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    @GetMapping("/normaltest")
    @ResponseBody
    public String normaltest() {
        return "normaltest";
    }

    @GetMapping("/authtest")
    @ResponseBody
    public String authtest() {
        return "authtest";
    }

    @GetMapping("/usertest")
    @ResponseBody
    public String usertest() {
        return "usertest";
    }

    @PostMapping("/userposttest")
    @ResponseBody
    public String userposttest() {
        return "userposttest";
    }

    @GetMapping("/admintest")
    @ResponseBody
    public String admintest() {
        return "admintest";
    }

    @GetMapping("/anytest")
    @ResponseBody
    public String anytest() {
        return "anytest";
    }
}
