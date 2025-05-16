package com.myorganisation.traceboard.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_photo")
@Data
public class UserPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private byte[] photo;

}
