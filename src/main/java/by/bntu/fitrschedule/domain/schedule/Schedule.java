package by.bntu.fitrschedule.domain.schedule;

import java.util.ArrayList;
import java.util.List;

public class Schedule {
    private List<Course> courses;

    public Schedule() {
        courses = new ArrayList<>();
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }
}
