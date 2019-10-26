package by.bntu.fitrschedule.controllers;

import by.bntu.fitrschedule.domain.User;
import by.bntu.fitrschedule.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    private ScheduleService scheduleService;

    @Autowired
    public HomeController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/home")
    public String home(@RequestParam(value = "course", required = false, defaultValue = "0")int course,
                       @RequestParam(value = "group", required = false, defaultValue = "")String group,
                       Model model) {
        if (course == 0) model.addAttribute("schedule", scheduleService.getAllSchedule());
        else if (group.isEmpty()) model.addAttribute("schedule", scheduleService.getScheduleByCourse(course));
        else model.addAttribute("schedule", scheduleService.getScheduleByGroup(group));
        model.addAttribute("courses", scheduleService.getAllCourses());
        model.addAttribute("groups", scheduleService.getGroupsByCourse(course));
        return "home";
    }
}
