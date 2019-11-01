package by.bntu.fitrschedule.domain.schedule;

import java.util.ArrayList;
import java.util.List;

public class Week {
    private int weekNumber;
    private List<Subgroup> subgroups;

    public Week() {
        subgroups = new ArrayList<>();
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }

    public List<Subgroup> getSubgroups() {
        return subgroups;
    }

    public void addSubgroup(Subgroup subgroup) {
        subgroups.add(subgroup);
    }
}
