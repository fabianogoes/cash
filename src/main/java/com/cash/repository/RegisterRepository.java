package com.cash.repository;

import com.cash.model.Register;
import com.cash.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RegisterRepository extends MongoRepository<Register, String> {

    List<Register> findByUserAndPeriod(User user, String period);

    List<Register> findByTypeAndPeriod(String typeCredit, String period);
}
