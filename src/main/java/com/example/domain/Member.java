package com.example.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "member_tb")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
            @Column(name = "MEMBER_ID")
    private Long member_id;

    @Column(name = "MEMBER_LOGIN_ID")
    private String loginId;
    @Column(name = "MEMBER_NAME")
    private String mbName;
    @Column(name = "MEMBER_PASSWORD")
    private String mbPw;
    @Column(name = "MEMBER_EMAIL")
    private String email;
    @Column(name = "MEMBER_ROLE")
    private String mbGrade;
    @Column(name = "ISRT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date isrtDate;
    @Column(name = "UPDT_DATE")
    private String updtDate;

    @PrePersist
    protected void onCreate() {
        isrtDate = new Date();
    }

}
