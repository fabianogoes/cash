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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
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

        double credit = service.findAll().stream().filter(r -> r.getType().equalsIgnoreCase("Credit")).mapToDouble(r -> r.getAmount()).sum();
        double debit = service.findAll().stream().filter(r -> r.getType().equalsIgnoreCase("Debit")).mapToDouble(r -> r.getAmount()).sum();
        double pending = service.findAll().stream().filter(r -> r.getStatus().equalsIgnoreCase("Pending")).mapToDouble(r -> r.getAmount()).sum();
        double balance = credit - debit;
        index.addObject("totalCredit", credit);
        index.addObject("totalDebit", debit);
        index.addObject("totalBalance", balance);
        index.addObject("totalPending", pending);

        return index;
    }

    @RequestMapping("/form")
    public ModelAndView form(){
        ModelAndView index = new ModelAndView("index");
        index.addObject("title", registerPropertiesUtil.getTitle());
        index.addObject("moduleName", registerPropertiesUtil.getModuleName());
        index.addObject("moduleDescription", registerPropertiesUtil.getModuleDescription());
        index.addObject("categoryRegister", registerPropertiesUtil.getCategoryRegister());
        index.addObject("typeRegister", registerPropertiesUtil.getTypeRegister());
        index.addObject("statusRegister", registerPropertiesUtil.getStatusRegister());
        index.addObject("months", new DateFormatSymbols().getMonths());
        index.addObject("messages", messages);
        index.addObject("alerts", alerts);
        index.addObject("userLoggedIn", userLoggedIn);

        double credit = service.findAll().stream().filter(r -> r.getType().equalsIgnoreCase("Credit")).mapToDouble(r -> r.getAmount()).sum();
        double debit = service.findAll().stream().filter(r -> r.getType().equalsIgnoreCase("Debit")).mapToDouble(r -> r.getAmount()).sum();
        double pending = service.findAll().stream().filter(r -> r.getStatus().equalsIgnoreCase("Pending")).mapToDouble(r -> r.getAmount()).sum();
        double balance = credit - debit;
        index.addObject("totalCredit", credit);
        index.addObject("totalDebit", debit);
        index.addObject("totalBalance", balance);
        index.addObject("totalPending", pending);

        index.addObject("register", new Register());
        return index;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save(@Valid Register register, final BindingResult bindingResult, final Model model){
        ModelAndView index = new ModelAndView("index");
        if(bindingResult.hasErrors()){
            index.addObject("title", registerPropertiesUtil.getTitle());
            index.addObject("moduleName", registerPropertiesUtil.getModuleName());
            index.addObject("moduleDescription", registerPropertiesUtil.getModuleDescription());
            index.addObject("categoryRegister", registerPropertiesUtil.getCategoryRegister());
            index.addObject("typeRegister", registerPropertiesUtil.getTypeRegister());
            index.addObject("statusRegister", registerPropertiesUtil.getStatusRegister());
            index.addObject("months", new DateFormatSymbols().getMonths());
            index.addObject("messages", messages);
            index.addObject("alerts", alerts);
            index.addObject("userLoggedIn", userLoggedIn);

            double credit = service.findAll().stream().filter(r -> r.getType().equalsIgnoreCase("Credit")).mapToDouble(r -> r.getAmount()).sum();
            double debit = service.findAll().stream().filter(r -> r.getType().equalsIgnoreCase("Debit")).mapToDouble(r -> r.getAmount()).sum();
            double pending = service.findAll().stream().filter(r -> r.getStatus().equalsIgnoreCase("Pending")).mapToDouble(r -> r.getAmount()).sum();
            double balance = credit - debit;
            index.addObject("totalCredit", credit);
            index.addObject("totalDebit", debit);
            index.addObject("totalBalance", balance);
            index.addObject("totalPending", pending);

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
        index.addObject("title", registerPropertiesUtil.getTitle());
        index.addObject("moduleName", registerPropertiesUtil.getModuleName());
        index.addObject("moduleDescription", registerPropertiesUtil.getModuleDescription());
        index.addObject("categoryRegister", registerPropertiesUtil.getCategoryRegister());
        index.addObject("typeRegister", registerPropertiesUtil.getTypeRegister());
        index.addObject("statusRegister", registerPropertiesUtil.getStatusRegister());
        index.addObject("months", new DateFormatSymbols().getMonths());
        index.addObject("messages", messages);
        index.addObject("alerts", alerts);

        double credit = service.findAll().stream().filter(r -> r.getType().equalsIgnoreCase("Credit")).mapToDouble(r -> r.getAmount()).sum();
        double debit = service.findAll().stream().filter(r -> r.getType().equalsIgnoreCase("Debit")).mapToDouble(r -> r.getAmount()).sum();
        double pending = service.findAll().stream().filter(r -> r.getStatus().equalsIgnoreCase("Pending")).mapToDouble(r -> r.getAmount()).sum();
        double balance = credit - debit;
        index.addObject("totalCredit", credit);
        index.addObject("totalDebit", debit);
        index.addObject("totalBalance", balance);
        index.addObject("totalPending", pending);

        index.addObject("userLoggedIn", userLoggedIn);
        Register register = service.findOne(id);
        System.out.println("**********************************************");
        System.out.println(register);
        System.out.println("**********************************************");
        index.addObject("register", register);
        return index;
    }

}
