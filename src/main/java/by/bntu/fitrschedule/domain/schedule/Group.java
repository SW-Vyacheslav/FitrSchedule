package by.bntu.fitrschedule.domain.schedule;

import java.util.HashSet;
import java.util.Set;

public class Group {
    private String name;
    private Set<WeekDay> weekDays;

    public Group() {
        weekDays = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<WeekDay> getWeekDays() {
        return weekDays;
    }

    public void addWeekDay(WeekDay weekDay) {
        weekDays.add(weekDay);
    }
}
