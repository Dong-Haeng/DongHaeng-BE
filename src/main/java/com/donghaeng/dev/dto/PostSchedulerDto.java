package com.donghaeng.dev.dto;

import com.donghaeng.dev.domain.Scheduler;
import com.donghaeng.dev.util.DayOfWeek;
import lombok.Getter;

@Getter
public class PostSchedulerDto {

    private DayOfWeek weekday;
    private String startTime;
    private String endTime;
    private String name;

    public PostSchedulerDto(Scheduler scheduler) {
        this.weekday = DayOfWeek.fromValue(Integer.parseInt(scheduler.getDay()));
        double start = scheduler.getStarttime();
        double end = scheduler.getEndtime();

        Integer startIntValue = (int) start;
        Integer startDecimalValue = (int) ((start - startIntValue) * 60);
        Integer endIntValue = (int) end;
        Integer endDecimalValue = (int) ((end - endIntValue) * 60);

        this.startTime = startIntValue.toString() + "시 " + startDecimalValue.toString() + "분";
        this.endTime = endIntValue.toString() + "시 " + endDecimalValue.toString() + "분";
        this.name = scheduler.getName();
    }
}
