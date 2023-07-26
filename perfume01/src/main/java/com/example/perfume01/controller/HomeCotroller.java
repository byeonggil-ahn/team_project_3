package com.example.perfume01.controller;

import com.example.perfume01.dto.GraphDTO;
import com.example.perfume01.service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
public class HomeCotroller {

    @Autowired
    GraphService service;

    @GetMapping({"/", "/home"})
    // => void : 요청명.jsp 를 viewName 으로 처리할수있음 (home.jsp)
    public String home(Locale locale, Model model, GraphDTO dto) {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        String formattedDate = dateFormat.format(date);
        model.addAttribute("serverTime", formattedDate );

        // 그래프 관련 컨트롤러 코드
        List<GraphDTO> graph = service.productGraph(dto);
        model.addAttribute("graph", graph);

        return "home";
    } //home

    @GetMapping("/bcrypt")
    public String bcrypt() {
        return "redirect:home";
    }

}
