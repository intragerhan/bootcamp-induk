package com.bootcamp.induk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @GetMapping("/replier")
    public String replies() {
        return "board/reply";
    }

    @GetMapping("/test")
    public String tests() {
        return "test";
    }
}
