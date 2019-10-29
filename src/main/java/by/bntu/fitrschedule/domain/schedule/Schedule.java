package by.bntu.fitrschedule.domain.schedule;

import java.util.HashSet;
import java.util.Set;

public class Schedule {
    private Set<Course> courses;

    public Schedule() {
        courses = new HashSet<>();
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }
}
