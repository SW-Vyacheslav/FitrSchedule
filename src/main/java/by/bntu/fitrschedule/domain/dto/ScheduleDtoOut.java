package by.bntu.fitrschedule.domain.dto;

import by.bntu.fitrschedule.domain.schedule.Schedule;

public class ScheduleDtoOut {
    private Schedule schedule;

    public ScheduleDtoOut() {
    }

    public ScheduleDtoOut(Schedule schedule) {
        this.schedule = schedule;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
