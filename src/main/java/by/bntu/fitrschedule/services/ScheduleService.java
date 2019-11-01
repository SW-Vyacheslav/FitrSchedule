package by.bntu.fitrschedule.services;

import by.bntu.fitrschedule.domain.dto.ScheduleDtoOut;

import java.util.List;

public interface ScheduleService {
    ScheduleDtoOut getAllSchedule();
    ScheduleDtoOut getScheduleByCourse(int course);
    ScheduleDtoOut getScheduleByGroup(String group);
    List<Integer> getAllCourses();
    List<String> getGroupsByCourse(int course);
}
