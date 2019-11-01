package by.bntu.fitrschedule.domain.schedule;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private String name;
    private List<WeekDay> weekDays;

    public Group() {
        weekDays = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<WeekDay> getWeekDays() {
        return weekDays;
    }

    public void addWeekDay(WeekDay weekDay) {
        weekDays.add(weekDay);
    }
}
