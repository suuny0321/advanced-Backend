package com.lion.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name ="Users")
//User객체를 테이블로 사용하기 위한(클래스가 데이터베이스 테이블과 매핑)

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //User.builder()로 사용이 가능하다.
//@RequiredArgsConstructor
public class User {
    @Id //id->pk로 설정
    private String uid;

    private String pwd;
    private String uname;
    private String email;
    private LocalDate regDate;
    private String role;



}
