package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.Category;
import org.launchcode.cheesemvc.models.data.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("category")
public class CategoryController {

    // This will create an instance of the is class
    @Autowired
    private CategoryDao categoryDao;


    // Index handler
    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("categories",categoryDao.findAll());
        model.addAttribute("title","Categories Works");
        return "category/index";
    }

    // Handles adding categories
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model){
        model.addAttribute("title","Add Category");
        model.addAttribute(new Category());
        return "category/add";
    }

    // Handles posting the categories
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Category category, Errors errors){

        // if there are errors the return to the add category page
        if(errors.hasErrors()){
            model.addAttribute("title","Add Category");
            return "category/add";
        }

        // if there are no error send it back to the index page
        categoryDao.save(category);
        return "redirect:";
    }

}
