package by.bntu.fitrschedule.domain.dto;

import by.bntu.fitrschedule.domain.schedule.Schedule;

public class ScheduleDtoOut {
    private Schedule data;

    public ScheduleDtoOut() {
    }

    public ScheduleDtoOut(Schedule data) {
        this.data = data;
    }

    public Schedule getData() {
        return data;
    }

    public void setData(Schedule data) {
        this.data = data;
    }
}
