package org.launchcode.cheesemvc.controllers;


import org.launchcode.cheesemvc.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping("user")
public class UserValidation {

    // Hold the user objects
    public ArrayList<User> usersdata = new ArrayList<User>();

    // Default mapping to the page
    @RequestMapping(value="")
    public String index(Model model){
        model.addAttribute("title","welcome");
        model.addAttribute("users",usersdata);
        return "user/index";
    }

    // Displays the page
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAdd(Model model){
        model.addAttribute("title","Add User");
        return "user/add";
    }

    // This will process the form once the user
    @RequestMapping(value="add")
    public String Add(Model model, @ModelAttribute User user, String verify){
        model.addAttribute("title","Add User");

        String userPassword = user.getPassword();

        if(!userPassword.equals(verify)){
            return "user/add";
        }

        usersdata.add(user);
        // if the validation is successful redirect user to the index page
        return "redirect:";
    }

}
