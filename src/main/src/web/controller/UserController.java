package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserServiceImpl;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl service;

    @GetMapping
    public String getRoot(@AuthenticationPrincipal User user, ModelMap model){
        model.addAttribute("user", user);
        return "userInformation";
    }


}
