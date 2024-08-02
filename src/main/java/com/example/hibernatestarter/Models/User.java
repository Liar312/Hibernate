package com.example.hibernatestarter.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;


import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users",schema = "public")
public class User {
    @Id
    @Column(name = "username",nullable = false,unique = true)
    private String username;
    @Column(name="firstname")
    private String firstname;
    @Column(name="lastname")
    private String lastname;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Column(name="age")
    private Integer age;
    @Enumerated(EnumType.STRING)//чтобы в бд наши роли заносились в строковом виде а не в виде цифр
    private Role role;
}