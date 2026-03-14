package com.projectla.deliveryapp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projectla.deliveryapp.Enum.Role;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users") //avoid reserve keyword issue
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String phoneNumber;
    
    @JsonIgnore
    private String password;

    @Enumerated(EnumType.STRING)
     private Role role;
}