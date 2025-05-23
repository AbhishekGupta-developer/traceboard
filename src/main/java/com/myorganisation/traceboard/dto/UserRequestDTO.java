package com.myorganisation.traceboard.dto;

import com.myorganisation.traceboard.model.enums.UserRole;
import lombok.Data;

@Data
public class UserRequestDTO {
    private String name;
    private Long phone;
    private String email;
    private UserRole role;
}
