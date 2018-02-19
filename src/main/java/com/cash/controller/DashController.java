package com.cash.controller;

import com.cash.model.Register;
import com.cash.model.Totalizer;
import com.cash.service.RegisterService;
import com.cash.service.TotalizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class DashController {

    @Autowired
    private TotalizerService totalizerService;

    @Autowired
    private RegisterService registerService;

    @Value("${cash.userLoggedInKey}")
    private String userLoggedIn;

    @RequestMapping("/")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("moduleName", "dash");

        List<Register> registers = registerService.findAll();
        Totalizer totalizer = totalizerService.getTotalizer(registers);
        mv.addObject("amountCredit", totalizer.getCredit());
        mv.addObject("amountDebit", totalizer.getDebit());
        mv.addObject("amountBalance", totalizer.getBalance());
        mv.addObject("amountPending", totalizer.getPending());
        mv.addObject("amountMovimentation", totalizer.getMovimentation());

        return mv;
    }

}
