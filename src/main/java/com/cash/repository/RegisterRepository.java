package com.cash.repository;

import com.cash.model.Register;
import com.cash.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RegisterRepository extends MongoRepository<Register, String> {

    Register findByTitle(String title);

    List<Register> findByUser(User user);

    List<Register> findByUserAndMonth(User user, String month);
}
