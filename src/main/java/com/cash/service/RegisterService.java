package com.cash.service;

import com.cash.model.Category;
import com.cash.model.Register;
import com.cash.model.User;
import com.cash.repository.RegisterRepository;
import com.cash.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.List;

@Service
@SessionScope
public class RegisterService {

    @Value("${cash.userLoggedInKey}")
    private String userLoggedIn;

    private User user;

    @Autowired
    private RegisterRepository repository;

    @Autowired
    private CategoryService categoryService;

    public RegisterService(){
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        user = (User)session.getAttribute(userLoggedIn);
    }

    public List<Register> findAll() {
        return repository.findByUserAndMonth(user, DateTimeUtil.getCurrentMonthName());
    }

    public Register findOne(String id) {
        return repository.findOne(id);
    }

    public Register save(Register register) {
        if(register.getId() == null) {
            register.setCreatedDate(Calendar.getInstance().getTime());
        }
        register.setMonth(DateTimeUtil.getCurrentMonthName());
        register.setUser(user);
        register.setLastModifiedDate(Calendar.getInstance().getTime());
        return repository.save(register);
    }

    public void delete(String id){
        repository.delete(id);
    }

    public void paid(String id) {
        Register register = repository.findOne(id);
        String status = register.getStatus();
        status = (status.equalsIgnoreCase("Pending") || status.equalsIgnoreCase("Delayed")) ?
                    "Paid" : "Pending";
        register.setStatus(status);
        repository.save(register);
    }

    public List<Category> getCategories() {
        return categoryService.findAll();
    }

    public Category saveCategory(Category category) {
        return categoryService.save(category);
    }
}
