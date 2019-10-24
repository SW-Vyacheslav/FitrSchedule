package by.bntu.fitrschedule.services;

import by.bntu.fitrschedule.domain.ScheduleData;
import java.util.List;

public interface ScheduleService {
    List<ScheduleData> getScheduleByCourse(int course);
    List<ScheduleData> getScheduleByGroup(String group);
}
