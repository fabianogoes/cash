package com.cash.repository;

import com.cash.model.Register;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RegisterRepository extends MongoRepository<Register, String> {
    Register findByTitle(String title);
}
