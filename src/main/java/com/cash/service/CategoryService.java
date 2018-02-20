package com.cash.service;

import com.cash.model.Category;
import com.cash.repository.CategoryRepository;
import org.apache.commons.lang3.StringUtils;
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

    public Category saveByName(String name) {
        Category category = new Category(name);
        return repository.save(category);
    }

    public List<Category> findAll() {
        return repository.findAll();
    }

    public Category save(Category category) {
        if(StringUtils.isEmpty(category.getId())) {
            return saveByName(category.getName());
        }
        return repository.save(category);
    }

    public void delete(String id) {
        repository.delete(id);
    }

    public Category findOne(String id) {
        return repository.findOne(id);
    }
}
