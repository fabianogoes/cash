package com.cash.controller;

import com.cash.model.Register;
import com.cash.service.RegisterService;
import com.cash.util.RegisterPropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public ModelAndView list(){
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
    public ModelAndView save(@Valid Register register, final BindingResult bindingResult, RedirectAttributes attributes){
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
        attributes.addFlashAttribute("message", "Register salved successfully");
        index.setViewName("redirect:/register");
        return index;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable String id){
        service.delete(id);
        return "redirect:/register";
    }
    @RequestMapping(value = "/paid/{id}", method = RequestMethod.GET)
    public String paid(@PathVariable String id, RedirectAttributes attributes){
        service.paid(id);
        attributes.addFlashAttribute("message", "Register marked as payment successfully");
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
