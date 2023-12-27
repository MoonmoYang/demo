package com.example.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "member_tb")
public class Member {

    @Id
    @GeneratedValue
            @Column(name = "MEMBER_ID")
    Long member_id;

    @Column(name = "MEMBER_LOGIN_ID")
    String login_id;
    @Column(name = "MEMBER_NAME")
    String mb_name;
    @Column(name = "MEMBER_PASSWORD")
    String mb_pw;
    @Column(name = "MEMBER_EMAIL")
    String address;
    @Column(name = "MEMBER_ROLE")
    String mb_grade;
    @Column(name = "ISRT_DATE")
    String isrt_date;
    @Column(name = "UPDT_DATE")
    String updt_date;

}
