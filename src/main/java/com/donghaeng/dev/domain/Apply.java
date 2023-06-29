package com.donghaeng.dev.domain;

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
@Entity
public class Apply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "apply_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crew_id")
    private Crew crew;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Long userId;

    @OneToMany(mappedBy = "apply", cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<>();

    public Apply(Long userId, Crew crew) {
        this.userId = userId;
        this.crew = crew;
        this.status = Status.PENDING;
    }

    public Apply(Long userId, Crew crew, List<String> answers) {
        this.userId = userId;
        this.crew = crew;
        this.answers = answers.stream()
                .map(content -> new Answer(this, content))
                .collect(Collectors.toList());
    }

}
