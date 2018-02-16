package com.cash.service;

import com.cash.model.Register;
import com.cash.repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class RegisterService {

    @Autowired
    private RegisterRepository repository;

    public List<Register> findAll() {
        return repository.findAll();
    }

    public Register findOne(String id) {
        return repository.findOne(id);
    }

    public Register findByTitle(String title) {
        return repository.findByTitle(title);
    }

    public Register save(Register register) {
        if(register.getId() == null) {
            register.setCreatedDate(Calendar.getInstance().getTime());
        }
        register.setLastModifiedDate(Calendar.getInstance().getTime());
        return repository.save(register);
    }

    public void delete(String id){
        repository.delete(id);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public void paid(String id) {
        Register register = repository.findOne(id);
        String status = register.getStatus();
        status = (status.equalsIgnoreCase("Pending") || status.equalsIgnoreCase("Delayed")) ?
                    "Paid" : "Pending";
        register.setStatus(status);
        repository.save(register);
    }
}
