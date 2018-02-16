package com.cash.controller;

import com.cash.model.Login;
import com.cash.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping
    public String form(Model model){
        model.addAttribute("login", new Login());
        return "login";
    }

    @RequestMapping(value = "/in", method = RequestMethod.POST)
    public String in(Login login, HttpSession session, RedirectAttributes attributes){
        if(!loginService.valid(login)){
            attributes.addFlashAttribute("login", login);
            attributes.addFlashAttribute("message", "Email or password invalid.");
            return "redirect:/login";
        }
        session.setAttribute("userLoggedIn", loginService.getUser(login));
        return "redirect:/";
    }

    @RequestMapping("/out")
    public String out(HttpSession session){
        session.setAttribute("userLoggedIn", null);
        return "redirect:/login";
    }


}
