package com.donghaeng.dev.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Scheduler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String identifier;

    @Column
    private String day;

    @Column(length = 45)
    private String name;

    @Column
    private String time;

    @Column
    private double starttime;

    @Column
    private double endtime;

    @ManyToOne
    private Member member;

    public Scheduler(String identifier, String name, String day, String timeValue, double starttime, double endtime) {
        this.identifier = identifier;
        this.day = day;
        this.name = name;
        this.time = timeValue;
        this.starttime = starttime;
        this.endtime = endtime;
    }
}
