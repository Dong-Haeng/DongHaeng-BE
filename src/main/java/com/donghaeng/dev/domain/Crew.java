package com.donghaeng.dev.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// TEST
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Crew extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "crew_id")
    private Long id;

    @Column(length = 45)
    private String name;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    private boolean isRecruiting;

    @Column(length = 45)
    @Enumerated(EnumType.STRING)
    private Division division;

    @Column(length = 45)
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 45)
    @Enumerated(EnumType.STRING)
    private University university;

    @OneToMany(mappedBy = "crew")
    private List<Apply> applys = new ArrayList<>();
}
