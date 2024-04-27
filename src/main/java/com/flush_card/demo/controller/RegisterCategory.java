package com.flush_card.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.flush_card.demo.entity.Categories;
import com.flush_card.demo.repository.CategoryRepository;

@Controller
@RequestMapping("/register")
public class RegisterCategory {

    private final CategoryRepository categoryRepository;

    public RegisterCategory(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/category")
    public String getMethodName(Model model) {
        List<Categories> categoriesList = categoryRepository.selectCategories();
        model.addAttribute("categories", categoriesList);
        return "/register/category";
    }

    @GetMapping("/category/{id}")
    public String getMethodName(Model model, @PathVariable("id") String id) {
        List<Categories> categoriesList = categoryRepository.selectUserCategories(Integer.parseInt(id));
        model.addAttribute("categoriesLength", categoriesList.size());
        model.addAttribute("categories", categoriesList);
        return "/register/category";
    }

    @PostMapping("/category/{id}")
    public String postMethodName(@PathVariable("id") String id, @ModelAttribute Categories categories) {
        categories.setUserId(Integer.parseInt(id));
        return "/get/category";
    }

}