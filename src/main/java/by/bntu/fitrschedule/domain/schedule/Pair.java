package by.bntu.fitrschedule.domain.schedule;

import java.util.ArrayList;
import java.util.List;

public class Pair {
    private String hours;
    private List<Week> weeks;

    public Pair() {
        weeks = new ArrayList<>();
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public List<Week> getWeeks() {
        return weeks;
    }

    public void addWeek(Week week) {
        weeks.add(week);
    }
}
