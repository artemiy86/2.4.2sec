package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.UserServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserServiceImpl service;

    public AdminController(){}

    @Autowired
    public AdminController(UserServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public String getAdminPage(ModelMap model){
        model.addAttribute("users",service.getAllUsers());
        return "adminPanel";
    }

    @DeleteMapping("/delete")
    public String deleteUser(@RequestParam(value = "id") long id){
        service.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/add")
    public String addUserForm(ModelMap model){
        User user = new User();
        model.addAttribute("action","add");
        model.addAttribute("user", user);
        model.addAttribute("roles",new Role());
        return "form";
    }

    @GetMapping("/edit")
    public String editUserForm(@RequestParam(value = "id") long id,
                               ModelMap model){
        model.addAttribute("action","edit");
        User user = service.getUserById(id);
        model.addAttribute("user",user);
        model.addAttribute("roles", new Role());
        return "form";
    }

    @PostMapping("/edit")
    public String editUser(@RequestParam(value = "id") long id,
                           @RequestParam(value = "name") String name,
                           @RequestParam(value = "surname") String surname,
                           @RequestParam(value = "age") int age,
                           @RequestParam(value = "email") String email,
                           @RequestParam(value = "username") String username,
                           @RequestParam(value = "password") String password){
        service.editUser(id, name, surname, age, email,username,password);
        return "redirect:/admin";
    }

    @PostMapping("/add")
    public String createUser(@RequestParam(value = "name") String name,
                             @RequestParam(value = "surname") String surname,
                             @RequestParam(value = "age") int age,
                             @RequestParam(value = "email") String email,
                             @RequestParam(value = "username") String username,
                             @RequestParam(value = "password") String password){
        service.createUser(name, surname, age, email,username,password);
        return "redirect:/admin";
    }

}