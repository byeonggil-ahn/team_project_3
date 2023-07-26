package com.example.perfume01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller @RequestMapping("/sell")
public class SellController {

    @GetMapping("/sell/list")
    public void sellList() {

    }

}
