package com.myorganisation.traceboard.service;

import com.myorganisation.traceboard.dto.UserRequestDTO;
import com.myorganisation.traceboard.dto.UserResponseDTO;
import com.myorganisation.traceboard.exceptions.UserDoesNotExist;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    UserResponseDTO registerUser(UserRequestDTO userRequestDTO, MultipartFile photo);
    UserResponseDTO getUser(Long id) throws UserDoesNotExist;
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO);
    String removeUser(Long id);
    Page<UserResponseDTO> getUserPage(Integer page, Integer size, String sortBy, String orderBy);
    String getUserPhoto(Long id);
}
