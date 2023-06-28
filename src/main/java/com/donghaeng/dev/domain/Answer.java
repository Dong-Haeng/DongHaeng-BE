package com.donghaeng.dev.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "answer_id")
    private Long id;

    @OneToOne
    private Question question;

    @Column
    private String content;

    @ManyToOne
    @JoinColumn(name = "apply_id")
    private Apply apply;
}
