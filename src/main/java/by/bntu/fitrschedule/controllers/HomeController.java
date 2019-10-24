package by.bntu.fitrschedule.controllers;

import by.bntu.fitrschedule.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    private ScheduleService scheduleService;

    @Autowired
    public HomeController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/home")
    public String home(@RequestParam(value = "course", required = false, defaultValue = "0")int course, Model model) {
        model.addAttribute("schedule", scheduleService.getScheduleByCourse(course));
        return "home";
    }

}
