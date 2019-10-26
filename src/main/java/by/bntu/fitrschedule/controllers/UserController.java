package by.bntu.fitrschedule.controllers;

import by.bntu.fitrschedule.domain.UserDtoOut;
import by.bntu.fitrschedule.domain.UserLoginDtoIn;
import by.bntu.fitrschedule.domain.UserRegistrationDtoIn;
import by.bntu.fitrschedule.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(UserRegistrationDtoIn user, Model model) {
        UserDtoOut userDtoOut = userService.registration(user);
        if (userDtoOut.hasError()) model.addAttribute("message", userDtoOut.getMessage());
        return "redirect:home";
    }
}
