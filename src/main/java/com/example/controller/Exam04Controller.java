package com.example.controller;

import com.example.domain.User;
import com.example.form.UserForm;
import jakarta.servlet.ServletContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exam04")
public class Exam04Controller {

    @GetMapping("")
    public String index(Model model, UserForm form) {
        return "exam04";
    }

    @PostMapping("/show-profile")
    public String showProfile(@Validated UserForm form, BindingResult result, Model model) {

        if (result.hasErrors()){
            return index(model, form);
        }

        User user = new User();
        BeanUtils.copyProperties(form, user);
        user.setAge(Integer.parseInt(form.getAge()));
        model.addAttribute("user", user);
        return "exam04-result";
    }
}
