package com.example.gws.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class TestController {
    @GetMapping("/r")
    public String index() {
        return "r";
    }
    @GetMapping("/a")
    public String index2() {
        return "forAdmin";
    }
    @GetMapping("/c")
    public String index3() {
        return "forUser";
    }

    @GetMapping("/login")
    public String index4() {
        return "login";
    }
}
