package com.donghaeng.dev.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Column(length = 45)
    private String name;

    @Column(length = 45)
    private String email;

    @Column(length = 45)
    private String password;

    @Column(length = 45)
    private String phone;

    @Column(length = 45)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(length = 45)
    @Enumerated(EnumType.STRING)
    private University university;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Crew> crews = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Apply> applys = new ArrayList<>();


    @Builder
    public User(Long id, String name, String email, String password, String phone, Status status, University university, List<Crew> crews, List<Apply> applys) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.status = status;
        this.university = university;
        this.crews = crews;
        this.applys = applys;
    }
}
