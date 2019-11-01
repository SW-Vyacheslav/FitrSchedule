package by.bntu.fitrschedule.domain.schedule;

public class Subgroup {
    private int subgroupNumber;
    private String subject;
    private String tutor;
    private String lectureHall;
    private int universityHall;

    public int getSubgroupNumber() {
        return subgroupNumber;
    }

    public void setSubgroupNumber(int subgroupNumber) {
        this.subgroupNumber = subgroupNumber;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public String getLectureHall() {
        return lectureHall;
    }

    public void setLectureHall(String lectureHall) {
        this.lectureHall = lectureHall;
    }

    public int getUniversityHall() {
        return universityHall;
    }

    public void setUniversityHall(int universityHall) {
        this.universityHall = universityHall;
    }
}
