package com.myorganisation.traceboard.service;

import com.myorganisation.traceboard.dto.UserRequestDTO;
import com.myorganisation.traceboard.dto.UserResponseDTO;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    UserResponseDTO registerUser(UserRequestDTO userRequestDTO, MultipartFile photo);
    UserResponseDTO getUser(Long id);
    String getUserPhoto(Long id);
}
