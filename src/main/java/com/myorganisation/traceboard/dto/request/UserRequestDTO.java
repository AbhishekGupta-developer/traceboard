package com.myorganisation.traceboard.dto.request;

import com.myorganisation.traceboard.model.enums.UserRole;
import lombok.Data;

@Data
public class UserRequestDTO {
    private String name;
    private String phone;
    private String email;
    private UserRole role;
    private String username;
    private String password;
}
