package com.donghaeng.dev.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Column(length = 45)
    private String name;

    @Column(length = 45)
    private String email;

    private String password;

    @Column(length = 45)
    private String phone;

    @Column(length = 45)
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;

    @OneToMany(mappedBy = "user")
    private List<Crew> crews = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Apply> applys = new ArrayList<>();
}
