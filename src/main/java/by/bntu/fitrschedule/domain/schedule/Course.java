package by.bntu.fitrschedule.domain.schedule;

import java.util.HashSet;
import java.util.Set;

public class Course {
    private Long courseNumber;
    private Set<Group> groups;

    public Course() {
        groups = new HashSet<>();
    }

    public Long getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(Long courseNumber) {
        this.courseNumber = courseNumber;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void addGroup(Group group) {
        groups.add(group);
    }
}
