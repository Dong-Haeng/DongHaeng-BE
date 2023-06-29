package com.donghaeng.dev.domain;

import com.donghaeng.dev.dto.CrewRegisterRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
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
    private University university;

    @Column(length = 45)
    @Enumerated(EnumType.STRING)
    private Division division;

    @Column(length = 45)
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "crew")
    private List<Apply> applys = new ArrayList<>();

    @OneToMany(mappedBy = "crew")
    private List<Question> questions = new ArrayList<>();

    public Crew(User user, CrewRegisterRequestDto crewRegisterRequestDto) {
        log.info("user = {}", user.getEmail());
        this.name = crewRegisterRequestDto.getName();
        this.description = crewRegisterRequestDto.getDescription();
        this.isRecruiting = crewRegisterRequestDto.isRecruiting();
        this.university = user.getUniversity();
        this.division = crewRegisterRequestDto.getDivision();
        this.status = Status.PENDING;
        this.user = user;
    }
}
