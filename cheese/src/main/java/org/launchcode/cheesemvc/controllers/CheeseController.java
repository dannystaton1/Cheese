package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.Category;
import org.launchcode.cheesemvc.models.Cheese;
import org.launchcode.cheesemvc.models.data.CategoryDao;
import org.launchcode.cheesemvc.models.data.CheeseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("cheese")
public class CheeseController {

    // Spring Magic!
    @Autowired
    // Auto wire says that the framework is going to create an instance of the class
    private CheeseDao cheeseDao;

    @Autowired
    private CategoryDao categoryDao;

    // Request path: /cheese
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("cheeses", cheeseDao.findAll()); // cheeseDao will return all the cheeses
        model.addAttribute("title", "My Cheeses");
        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        model.addAttribute(new Cheese()); // We are passing a skeleton object into the add view
        model.addAttribute("categories", categoryDao.findAll()); // Return an array of cheese types form the enum classr
        return "cheese/add";
    }

    // Adds new cheese to the data class
    @RequestMapping(value = "add", method = RequestMethod.POST)
    // This is using the cheese class to manage the post request, by matching up the name in the HTML
    public String processAddCheeseForm(@ModelAttribute @Valid Cheese newCheese, Errors errors, @RequestParam int categoryId, Model model) {
        //@Valid is specified in the Cheese class
        // Error is made available if there is an error and is made when there is an error

        //Bool value
        if(errors.hasErrors()){
            model.addAttribute("title","Add Cheese");
            return "cheese/add"; // return to the same page if the user gets it wrong
        }

        /* Whats going on?
        * Cheese newCheese = new Cheese
        * newCheese.setName(RequestParam("name"))
        * The request param is looking for a matching name in the class to assign itself to
        *
        */

        //Process the form an assign the category object to the ID to the cheese
        Category cat = categoryDao.findOne(categoryId);
        newCheese.setCategory(cat);
        cheeseDao.save(newCheese); // Saves new objects into the database based off the fields
        return "redirect:";
    }

    // Displays the cheese from the object that you can remove
    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("cheeses", cheeseDao.findAll());
        model.addAttribute("title", "Remove Cheese");
        return "cheese/remove";
    }

    // Removes cheese based off the value of the cheese
    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam int[] cheeseIds) {

        for (int cheeseId : cheeseIds) {
            cheeseDao.delete(cheeseId);
        }

        return "redirect:";
    }

    @RequestMapping(value ="category", method = RequestMethod.GET)
    public String category(Model model, @RequestParam int id){

        Category cat = categoryDao.findOne(id);
        List<Cheese> cheeses = cat.getCheeses();
        model.addAttribute("cheeses",cheeses);
        model.addAttribute("title","Cheeses in Category " + cat.getName());
        return "cheeses/index";
    }

}
