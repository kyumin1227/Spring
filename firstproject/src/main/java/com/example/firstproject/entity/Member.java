package com.example.firstproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@ToString
public class Member {

    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String email;
    @Column
    private String password;

//    public Member(Long id, String email, String password) {
//        this.id = id;
//        this.email = email;
//        this.password = password;
//    }
}
