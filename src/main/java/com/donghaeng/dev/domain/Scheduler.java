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
    private String name;

    @Column
    private String day;

    @Column
    private String time;

    @Column
    private String location;

    public Scheduler(String name, String day, String timeValue, String location) {
        this.name = name;
        this.day = day;
        this.time = timeValue;
        this.location = location;
    }
}
