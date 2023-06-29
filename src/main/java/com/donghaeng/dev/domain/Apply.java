package com.donghaeng.dev.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Apply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "apply_id")
    private Long id;

    //@Column(length = 45)
    //private String studentId;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crew_id")
    private Crew crew;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "apply")
    private List<Answer> answers = new ArrayList<>();

    public Apply(User user, Crew crew) {
        this.user = user;
        this.crew = crew;
        this.status = Status.PENDING;
    }

    public Apply(User user, Crew crew, List<String> answers) {
        this.user = user;
        this.crew = crew;
        this.answers = answers.stream()
                .map(content -> new Answer(this, content))
                .collect(Collectors.toList());
    }


}
