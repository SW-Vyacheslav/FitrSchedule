package by.bntu.fitrschedule.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "project")
public class ProjectConfig {
    private String firstCourseScheduleUrl;

    private String secondCourseScheduleUrl;

    private String thirdAndFourthCourseScheduleUrl;

    public void setFirstCourseScheduleUrl(String firstCourseScheduleUrl) {
        this.firstCourseScheduleUrl = firstCourseScheduleUrl;
    }

    public void setSecondCourseScheduleUrl(String secondCourseScheduleUrl) {
        this.secondCourseScheduleUrl = secondCourseScheduleUrl;
    }

    public void setThirdAndFourthCourseScheduleUrl(String thirdAndFourthCourseScheduleUrl) {
        this.thirdAndFourthCourseScheduleUrl = thirdAndFourthCourseScheduleUrl;
    }

    public String getFirstCourseScheduleUrl() {
        return firstCourseScheduleUrl;
    }

    public String getSecondCourseScheduleUrl() {
        return secondCourseScheduleUrl;
    }

    public String getThirdAndFourthCourseScheduleUrl() {
        return thirdAndFourthCourseScheduleUrl;
    }
}
