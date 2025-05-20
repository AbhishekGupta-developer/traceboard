package com.myorganisation.traceboard.dto;

import com.myorganisation.traceboard.model.enums.UserRole;
import lombok.Data;

@Data
public class UserResponseDTO {
    private Long id;
    private String name;
    private Long phone;
    private String email;
    private UserRole role;
    private Long photoId;
}
