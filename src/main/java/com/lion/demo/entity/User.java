package com.lion.demo.entity;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class User {
    private String uid;
    private String pwd;
    private String uname;
    private String email;
    private LocalDate registerDate;
    private String role;



}
