package com.openmemo.opmback.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    @GetMapping("/normaltest")
    @ResponseBody
    public String test() {
        return "normaltest";
    }

    @GetMapping("/authtest")
    @ResponseBody
    public String test2() {
        return "authtest";
    }
}
