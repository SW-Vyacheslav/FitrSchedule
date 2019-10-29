package by.bntu.fitrschedule.services;

import by.bntu.fitrschedule.domain.dto.ScheduleDtoOut;

import java.util.Set;

public interface ScheduleService {
    ScheduleDtoOut getAllSchedule();
    ScheduleDtoOut getScheduleByCourse(int course);
    ScheduleDtoOut getScheduleByGroup(String group);
    Set<Long> getAllCourses();
    Set<String> getGroupsByCourse(int course);
}
