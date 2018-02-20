package com.cash.controller;

import com.cash.model.Category;
import com.cash.service.CategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @RequestMapping
    public ModelAndView list(){
        ModelAndView index = new ModelAndView("index");
        index.addObject("categories", service.findAll());
        return index;
    }

    @RequestMapping("/form")
    public ModelAndView form(@RequestParam(value = "next_url", required = false) String next_url){
        ModelAndView index = new ModelAndView("index");
        index.addObject("category", new Category());
        index.addObject("next_url", next_url);
        return index;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save(Category category, RedirectAttributes attributes, @RequestParam(value = "next_url", required = false) String next_url){
        ModelAndView index = new ModelAndView("index");
        service.save(category);
        attributes.addFlashAttribute("message", "Category salved successfully");

        if(!StringUtils.isEmpty(next_url)){
            index.setViewName("redirect:" + next_url);
            return index;
        }

        index.setViewName("redirect:/category/form/");
        return index;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable String id){
        service.delete(id);
        return "redirect:/category";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable String id){
        ModelAndView index = new ModelAndView("index");
        index.addObject("category", service.findOne(id));
        return index;
    }

}
