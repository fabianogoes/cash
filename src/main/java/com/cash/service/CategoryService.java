package com.cash.service;

import com.cash.model.Category;
import com.cash.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    public Category findByName(String name) {
        return repository.findByName(name);
    }

    public Category save(String name) {
        Category category = new Category(name);
        return repository.save(category);
    }

    public List<Category> findAll() {
        return repository.findAll();
    }
}
