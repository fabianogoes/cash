package com.cash.controller;

import com.cash.model.Category;
import com.cash.model.Register;
import com.cash.service.RegisterService;
import com.cash.util.DateTimeUtil;
import com.cash.util.RegisterPropertiesUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
        index.addObject("module", "register");
        return index;
    }

    @RequestMapping("/{period}")
    public ModelAndView listByPeriod(@PathVariable("period") String period){
        ModelAndView index = new ModelAndView("index");
        index.addObject("registers", service.findAllByPeriod(period));
        index.addObject("module", "register");
        index.addObject("period", period);
        return index;
    }

    @RequestMapping("/form/{type}")
    public ModelAndView form(@PathVariable String type, @RequestParam(value = "categoryName", required = false) String categoryName){
        ModelAndView index = new ModelAndView("index");
        index.addObject("module", "register");
        index.addObject("categoryRegister", service.getCategories());
        index.addObject("typeRegister", registerPropertiesUtil.getTypeRegister());
        index.addObject("statusRegister", registerPropertiesUtil.getStatusRegister());
        index.addObject("months", new DateFormatSymbols().getMonths());
        Register register = new Register(type);
        if(StringUtils.isNotBlank(categoryName) && StringUtils.isNotEmpty(categoryName) && !"null".equalsIgnoreCase(categoryName)){
            register.setCategory(new Category(categoryName));
            index.addObject("categoryName", categoryName);
        }
        index.addObject("register", new Register(type));
        return index;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save(@Valid Register register, final BindingResult bindingResult, RedirectAttributes attributes){
        ModelAndView index = new ModelAndView("index");
        if(bindingResult.hasErrors()){
            index.addObject("module", "register");
            index.addObject("categoryRegister", service.getCategories());
            index.addObject("typeRegister", registerPropertiesUtil.getTypeRegister());
            index.addObject("statusRegister", registerPropertiesUtil.getStatusRegister());
            index.addObject("months", new DateFormatSymbols().getMonths());
            index.addObject("register", register);
            return index;
        }
        service.save(register);
        attributes.addFlashAttribute("message", "Register salved successfully");
        index.setViewName("redirect:/register/form/"+register.getType());
        return index;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable String id, RedirectAttributes attributes){
        service.delete(id);
        attributes.addFlashAttribute("message", "Register deleted successfully");
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
        index.addObject("module", "register");
        index.addObject("categoryRegister", service.getCategories());
        index.addObject("typeRegister", registerPropertiesUtil.getTypeRegister());
        index.addObject("statusRegister", registerPropertiesUtil.getStatusRegister());
        index.addObject("months", new DateFormatSymbols().getMonths());
        index.addObject("register", service.findOne(id));
        return index;
    }


}
