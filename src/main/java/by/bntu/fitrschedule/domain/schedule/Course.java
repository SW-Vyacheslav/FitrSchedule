package by.bntu.fitrschedule.domain.schedule;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private int courseNumber;
    private List<Group> groups;

    public Course() {
        groups = new ArrayList<>();
    }

    public int getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(int courseNumber) {
        this.courseNumber = courseNumber;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void addGroup(Group group) {
        groups.add(group);
    }
}
