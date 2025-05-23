package com.myorganisation.traceboard.dto;

import com.myorganisation.traceboard.model.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String name;
    private Long phone;
    private String email;
    private UserRole role;
}
