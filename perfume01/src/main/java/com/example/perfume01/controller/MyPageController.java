package com.example.perfume01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller @RequestMapping("/mypage")
public class MyPageController {

    @GetMapping("/myinfo")
    public void myPage() {}

}
