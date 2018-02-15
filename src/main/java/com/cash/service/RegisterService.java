package com.cash.service;

import com.cash.model.Register;
import com.cash.repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return repository.save(register);
    }

    public void delete(String id){
        repository.delete(id);
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
