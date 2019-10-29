package by.bntu.fitrschedule.domain.schedule;

import java.util.HashSet;
import java.util.Set;

public class Pair {
    private String hours;
    private Set<Week> weeks;

    public Pair() {
        weeks = new HashSet<>();
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public Set<Week> getWeeks() {
        return weeks;
    }

    public void addWeek(Week week) {
        weeks.add(week);
    }
}
