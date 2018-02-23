package com.cash.service;

import com.cash.model.Category;
import com.cash.model.Register;
import com.cash.model.User;
import com.cash.repository.RegisterRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

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
        String currentPeriod = this.getCurrentPeriod();
        return repository.findByUserAndPeriod(user, currentPeriod);
    }

    public List<Register> findAllByPeriod(String period) {
        return repository.findByUserAndPeriod(user, period);
    }

    public Register findOne(String id) {
        return repository.findOne(id);
    }

    public Register save(Register register) {
        boolean newRegister = register.getId() == null;
        boolean fixed = Register.FIXED.equalsIgnoreCase(register.getFixed());

        if(newRegister) {
            register.setCreatedDate(Calendar.getInstance().getTime());
            register.setPeriod(this.getCurrentPeriod());
            register.setUser(user);
        }
        register.setLastModifiedDate(Calendar.getInstance().getTime());
        repository.save(register);

        if(newRegister && fixed){
            this.saveNextMonths(register);
        }

        return register;
    }

    private void saveNextMonths(Register register){
        int nextMonth = Calendar.getInstance().get(Calendar.MONTH)+1;
        for(int i = nextMonth; i <= 11; i++){
            register.setPeriod(this.getPeriod(i));
            register.setId(null);
            repository.save(register);
        }

    }

    private String getCurrentPeriod(){
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        return getPeriod(month);
    }

    private String getPeriod(int month){
        Calendar calendar = Calendar.getInstance();
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String monthStr = StringUtils.leftPad(String.valueOf(month+1), 2, "0");
        String period = year.concat(monthStr);
        return period;
    }

    public void delete(String id){
        repository.delete(id);
    }

    public void paid(String id) {
        Register register = repository.findOne(id);
        String status = register.getStatus();
        status = (status.equalsIgnoreCase(Register.STATUS_PENDING) || status.equalsIgnoreCase(Register.STATUS_DELAYED)) ?
                    Register.STATUS_PAID : Register.STATUS_PENDING;
        register.setStatus(status);
        repository.save(register);
    }

    public List<Category> getCategories() {
        return categoryService.findAll();
    }

    public List<String> getAllPeriods(){
        return repository.findAll().stream().map(p -> p.getPeriod()).collect(Collectors.toList());
    }

    public List<Register> findAllCredit() {
        return repository.findByTypeAndPeriod(Register.TYPE_CREDIT, this.getCurrentPeriod());
    }

    public List<Register> findAllDebit() {
        return repository.findByTypeAndPeriod(Register.TYPE_DEBIT, this.getCurrentPeriod());
    }
}
