package com.cash.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class WelcomeController {

    @RequestMapping("/")
    public String index(){
        return "redirect:/register";
    }

}
