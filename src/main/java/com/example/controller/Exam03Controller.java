package com.example.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exam03")
public class Exam03Controller {

    @Autowired
    private ServletContext application;

    @GetMapping("")
    public String index() {
        return "exam03";
    }

    @PostMapping("/calc-price")
    public String calcPrice(Integer price1, Integer price2, Integer price3) {
        int total = price1 + price2 + price3;
        int totalTaxIn = (int)(total * 1.1);
        application.setAttribute("total", total);
        application.setAttribute("totalTaxIn", totalTaxIn);
        return "exam03-result";
    }
}