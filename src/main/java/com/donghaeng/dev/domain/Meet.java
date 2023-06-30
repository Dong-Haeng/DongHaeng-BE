package com.donghaeng.dev.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Meet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "meet_id")
    private Long id;

    @Column(length = 25)
    private String name;

    @OneToMany(mappedBy = "meet", fetch = FetchType.LAZY)
    private List<Member> members;

    public Meet(String name) {
        this.name = name;
    }
}
