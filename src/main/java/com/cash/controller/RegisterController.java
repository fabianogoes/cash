package com.cash.controller;

import com.cash.model.DashAlert;
import com.cash.model.DashMessage;
import com.cash.model.Register;
import com.cash.model.User;
import com.cash.service.RegisterService;
import com.cash.util.RegisterPropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormatSymbols;
import java.util.List;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private RegisterService service;

    @Autowired
    private RegisterPropertiesUtil registerPropertiesUtil;

    @Autowired
    private List<DashMessage> messages;

    @Autowired
    private List<DashAlert> alerts;

    @Autowired
    private User userLoggedIn;

    @RequestMapping
    public ModelAndView list(){
        ModelAndView index = new ModelAndView("index");
        index.addObject("title", registerPropertiesUtil.getTitle());
        index.addObject("moduleName", registerPropertiesUtil.getModuleName());
        index.addObject("moduleDescription", registerPropertiesUtil.getModuleDescription());
        index.addObject("statusRegister", registerPropertiesUtil.getStatusRegister());
        index.addObject("months", new DateFormatSymbols().getMonths());
        index.addObject("messages", messages);
        index.addObject("alerts", alerts);
        index.addObject("userLoggedIn", userLoggedIn);
        index.addObject("registers", service.findAll());

        System.out.println("***********************************************************************");
        service.findAll().forEach(System.out::println);
        System.out.println("***********************************************************************");


        return index;
    }

    @RequestMapping("/form")
    public ModelAndView form(){
        ModelAndView index = new ModelAndView("index");
        index.addObject("title", registerPropertiesUtil.getTitle());
        index.addObject("register", new Register());
        return index;
    }



}
