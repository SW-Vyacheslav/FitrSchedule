package by.bntu.fitrschedule.controllers;

import by.bntu.fitrschedule.domain.dto.ResponseDto;
import by.bntu.fitrschedule.domain.dto.ScheduleDtoOut;
import by.bntu.fitrschedule.services.ScheduleService;
import by.bntu.fitrschedule.tools.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/schedule")
public class ScheduleController {
    private ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping
    public ResponseDto getSchedule(@RequestParam(value = "course", required = false, defaultValue = "0")int course,
                                   @RequestParam(value = "group", required = false, defaultValue = "")String group,
                                   Model model) {
        ScheduleDtoOut scheduleDtoOut = null;

        if (course == 0) scheduleDtoOut = scheduleService.getAllSchedule();
        else if (group.isEmpty()) scheduleDtoOut = scheduleService.getScheduleByCourse(course);
        else scheduleDtoOut = scheduleService.getScheduleByGroup(group);

        if (scheduleDtoOut == null) return ResponseWrapper.wrap();
        else return ResponseWrapper.wrap(scheduleDtoOut);
    }
}
