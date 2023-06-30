package com.donghaeng.dev.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id")
    private Long id;


    @ManyToOne
    @JoinColumn(name = "meet_id")
    private Meet meet;

    @Column
    private String name;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Scheduler> schedulers;

    public Member(Meet meet, String name, List<Scheduler> schedulers) {
        this.meet = meet;
        this.name = name;
        this.schedulers = schedulers;
    }
}
