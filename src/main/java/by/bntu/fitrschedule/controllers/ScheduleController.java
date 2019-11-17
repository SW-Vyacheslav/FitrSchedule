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
    public ResponseDto getSchedule() {
        return ResponseWrapper.wrap(scheduleService.getAllSchedule());
    }

    @RequestMapping(value = "/course/{course}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto getScheduleByCourse(@PathVariable int course) {
        return ResponseWrapper.wrap(scheduleService.getScheduleByCourse(course));
    }

    @RequestMapping(value = "/course/{course}/group/{group}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto getScheduleByCourseAndGroup(@PathVariable int course, @PathVariable String group) {
        return ResponseWrapper.wrap(scheduleService.getScheduleByCourseAndGroup(course, group));
    }

    @RequestMapping(value = "/courses", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto getCourses() {
        return ResponseWrapper.wrap(scheduleService.getAllCourses());
    }

    @RequestMapping(value = "/course/{course}/groups", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto getGroupsByCourse(@PathVariable int course) {
        return ResponseWrapper.wrap(scheduleService.getGroupsByCourse(course));
    }
}
