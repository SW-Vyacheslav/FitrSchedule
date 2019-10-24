package by.bntu.fitrschedule.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleData {
    private int course;
    private String group;
    private String subjectName;
    private String weekDay;
    private int weekNumber;
    private String hours;
    private String lectureHall;
    private String tutor;
    private String universityBuilding;
}
