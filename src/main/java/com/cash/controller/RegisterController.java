package com.cash.controller;

import com.cash.model.Register;
import com.cash.service.RegisterService;
import com.cash.util.RegisterPropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.DateFormatSymbols;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private RegisterService service;

    @Autowired
    private RegisterPropertiesUtil registerPropertiesUtil;

    @RequestMapping
    public ModelAndView list(HttpSession session){
        ModelAndView index = new ModelAndView("index");
        index.addObject("registers", service.findAll());
        return index;
    }

    @RequestMapping("/form")
    public ModelAndView form(){
        ModelAndView index = new ModelAndView("index");
        index.addObject("categoryRegister", registerPropertiesUtil.getCategoryRegister());
        index.addObject("typeRegister", registerPropertiesUtil.getTypeRegister());
        index.addObject("statusRegister", registerPropertiesUtil.getStatusRegister());
        index.addObject("months", new DateFormatSymbols().getMonths());
        index.addObject("register", new Register());
        return index;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save(@Valid Register register, final BindingResult bindingResult, final Model model){
        ModelAndView index = new ModelAndView("index");
        if(bindingResult.hasErrors()){
            index.addObject("categoryRegister", registerPropertiesUtil.getCategoryRegister());
            index.addObject("typeRegister", registerPropertiesUtil.getTypeRegister());
            index.addObject("statusRegister", registerPropertiesUtil.getStatusRegister());
            index.addObject("months", new DateFormatSymbols().getMonths());
            index.addObject("register", register);
            return index;
        }
        service.save(register);

        index.setViewName("redirect:/register");
        return index;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable String id){
        service.delete(id);
        return "redirect:/register";
    }
    @RequestMapping(value = "/paid/{id}", method = RequestMethod.GET)
    public String paid(@PathVariable String id){
        service.paid(id);
        return "redirect:/register";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable String id){
        ModelAndView index = new ModelAndView("index");
        index.addObject("categoryRegister", registerPropertiesUtil.getCategoryRegister());
        index.addObject("typeRegister", registerPropertiesUtil.getTypeRegister());
        index.addObject("statusRegister", registerPropertiesUtil.getStatusRegister());
        index.addObject("months", new DateFormatSymbols().getMonths());
        index.addObject("register", service.findOne(id));
        return index;
    }

}
