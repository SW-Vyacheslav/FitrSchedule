package by.bntu.fitrschedule.controllers;

import by.bntu.fitrschedule.domain.dto.ResponseDto;
import by.bntu.fitrschedule.domain.dto.ScheduleDtoOut;
import by.bntu.fitrschedule.services.ScheduleService;
import by.bntu.fitrschedule.tools.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {
    private ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto getSchedule(@RequestParam(value = "course", required = false, defaultValue = "0")int course,
                                   @RequestParam(value = "group", required = false, defaultValue = "")String group) {
        ScheduleDtoOut scheduleDtoOut = null;

        if (!group.isEmpty()) scheduleDtoOut = scheduleService.getScheduleByGroup(group);
        else if (course == 0) scheduleDtoOut = scheduleService.getAllSchedule();
        else scheduleDtoOut = scheduleService.getScheduleByCourse(course);

        return ResponseWrapper.wrap(scheduleDtoOut);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, value = "/courses")
    public ResponseDto getCourses() {
        return ResponseWrapper.wrap(scheduleService.getAllCourses());
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, value = "/groups")
    public ResponseDto getGroupsByCourse(@RequestParam(value = "course", required = true)int course) {
        return ResponseWrapper.wrap(scheduleService.getGroupsByCourse(course));
    }
}
