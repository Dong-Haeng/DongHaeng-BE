package com.donghaeng.dev.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "answer_id")
    private Long id;

    @Column
    private String content;

    @ManyToOne
    @JoinColumn(name = "apply_id")
    private Apply apply;

    public Answer(String s) {
        this.content = s;
    }

    public Answer(Apply apply, String content) {
    }
}
