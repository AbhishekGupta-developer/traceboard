package com.myorganisation.traceboard.service;

import com.myorganisation.traceboard.dto.UserRequestDTO;
import com.myorganisation.traceboard.dto.UserResponseDTO;
import com.myorganisation.traceboard.model.User;
import com.myorganisation.traceboard.model.UserPhoto;
import com.myorganisation.traceboard.repository.UserPhotoRepository;
import com.myorganisation.traceboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserPhotoRepository userPhotoRepository;

    @Override
    @Transactional
    public UserResponseDTO registerUser(UserRequestDTO userRequestDTO, MultipartFile photo) {
        User user = new User();

        user.setName(userRequestDTO.getName());
        user.setPhone(userRequestDTO.getPhone());
        user.setEmail(userRequestDTO.getEmail());
        user.setRole(userRequestDTO.getRole());

        userRepository.save(user);

        if(photo != null) {

            UserPhoto userPhoto = new UserPhoto();

            try {
                userPhoto.setPhoto(photo.getBytes());
                userPhoto = userPhotoRepository.save(userPhoto);
                user.setPhotoId(userPhoto.getId());
                userRepository.save(user);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        UserResponseDTO userResponseDTO = new UserResponseDTO();

        userResponseDTO.setId(user.getId());
        userResponseDTO.setName(user.getName());
        userResponseDTO.setPhone(user.getPhone());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setRole(user.getRole());
        userResponseDTO.setPhotoId(user.getPhotoId());

        return userResponseDTO;
    }

    @Override
    public UserResponseDTO getUser(Long id) {
        User user = userRepository.findById(id).orElse(null);

        UserResponseDTO userResponseDTO = new UserResponseDTO();

        userResponseDTO.setId(user.getId());
        userResponseDTO.setName(user.getName());
        userResponseDTO.setPhone(user.getPhone());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setRole(user.getRole());
        userResponseDTO.setPhotoId(user.getPhotoId());

        return userResponseDTO;
    }

    @Override
    public String getUserPhoto(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if(user == null) {
            return "User doesn't exist";
        }
        Long photoId = user.getPhotoId();

        if(photoId != null) {
            UserPhoto userPhoto = userPhotoRepository.findById(photoId).orElse(null);
            return Base64.getEncoder().encodeToString(userPhoto.getPhoto());
        }

        return "Profile picture doesn't exist!";
    }
}
