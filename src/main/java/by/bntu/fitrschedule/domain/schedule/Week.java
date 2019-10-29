package by.bntu.fitrschedule.domain.schedule;

import java.util.HashSet;
import java.util.Set;

public class Week {
    private Long weekNumber;
    private Set<Subgroup> subgroups;

    public Week() {
        subgroups = new HashSet<>();
    }

    public Long getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(Long weekNumber) {
        this.weekNumber = weekNumber;
    }

    public Set<Subgroup> getSubgroups() {
        return subgroups;
    }

    public void addSubgroup(Subgroup subgroup) {
        subgroups.add(subgroup);
    }
}
