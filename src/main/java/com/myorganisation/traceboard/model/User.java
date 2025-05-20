package com.myorganisation.traceboard.model;

import com.myorganisation.traceboard.model.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Long phone;
    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private Long photoId;
}
