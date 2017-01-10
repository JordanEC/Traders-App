package com.jordanec.tradersapp.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jordanec.tradersapp.model.Category;
import com.jordanec.tradersapp.repository.CategoryRepository;


@RestController
@RequestMapping("api/v2/")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping(value = "categories", method = RequestMethod.GET)
    public List<Category> list() {
        return categoryRepository.findAll();
    }

    @RequestMapping(value = "categories", method = RequestMethod.POST)
    public Category create(@RequestBody Category category) {
        return categoryRepository.saveAndFlush(category);
    }

    @RequestMapping(value = "categories/{id}", method = RequestMethod.GET)
    public Category get(@PathVariable Long id) {
        return categoryRepository.findOne(id);
    }

    @RequestMapping(value = "categories/{id}", method = RequestMethod.PUT)
    public Category update(@PathVariable Long id, @RequestBody Category category) {
        Category existingCategory = categoryRepository.findOne(id);
        BeanUtils.copyProperties(category, existingCategory);
        return categoryRepository.saveAndFlush(existingCategory);
    }

    @RequestMapping(value = "categories/{id}", method = RequestMethod.DELETE)
    public Category delete(@PathVariable Long id) {
        Category existingCategory = categoryRepository.findOne(id);
        categoryRepository.delete(existingCategory);
        return existingCategory;
    }
}
