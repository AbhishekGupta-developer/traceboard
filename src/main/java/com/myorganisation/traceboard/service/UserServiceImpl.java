package com.myorganisation.traceboard.service;

import com.myorganisation.traceboard.config.SecurityConfig;
import com.myorganisation.traceboard.dto.request.UserRequestDTO;
import com.myorganisation.traceboard.dto.response.UserResponseDTO;
import com.myorganisation.traceboard.exceptions.UserDoesNotExist;
import com.myorganisation.traceboard.model.User;
import com.myorganisation.traceboard.model.UserPhoto;
import com.myorganisation.traceboard.repository.UserPhotoRepository;
import com.myorganisation.traceboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserPhotoRepository userPhotoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserResponseDTO registerUser(UserRequestDTO userRequestDTO, MultipartFile photo) {
        User user = copyUserRequestDTOToUser(userRequestDTO, new User());

        user = userRepository.save(user);

        if(photo != null) {
            UserPhoto userPhoto = new UserPhoto();
            try {
                userPhoto.setPhoto(photo.getBytes());
                userPhoto = userPhotoRepository.save(userPhoto);
                user.setPhotoId(userPhoto.getId());
                user = userRepository.save(user);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        UserResponseDTO userResponseDTO = convertUserToUserResponseDTO(user);

        return userResponseDTO;
    }

    @Override
    public UserResponseDTO getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserDoesNotExist("User id: " + id + " not found."));
        UserResponseDTO userResponseDTO = convertUserToUserResponseDTO(user);

        return userResponseDTO;
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<User> userList = userRepository.findAll();
        List<UserResponseDTO> userResponseDTOList = convertListOfUserToListOfUserResponseDTO(userList);

        return userResponseDTOList;
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserDoesNotExist("User id: " + id + " not found."));

        user = copyUserRequestDTOToUser(userRequestDTO, user);

        user = userRepository.save(user);

        UserResponseDTO userResponseDTO = convertUserToUserResponseDTO(user);

        return userResponseDTO;
    }

    @Override
    public String removeUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserDoesNotExist("User id: " + id + " not found."));

        String name = user.getName();
        Long photoId = user.getPhotoId();

        if (photoId != null) {
            userPhotoRepository.deleteById(photoId);
        }
        userRepository.deleteById(id);

        return "User: " + name + " (" + id + "), deleted successfully!";
    }

    @Override
    public Page<UserResponseDTO> getUserPage(Integer page, Integer size, String sortBy, String orderBy) {
        Sort sort = orderBy.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<User> userPage = userRepository.findAll(pageable);
        Page<UserResponseDTO> userResponseDTOPage = userPage.map(user -> convertUserToUserResponseDTO(user));

        return userResponseDTOPage;
    }

    @Override
    public String getUserPhoto(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserDoesNotExist("User id: " + id + " not found."));
        Long photoId = user.getPhotoId();

        if(photoId != null) {
            UserPhoto userPhoto = userPhotoRepository.findById(photoId).orElse(null);
            return Base64.getEncoder().encodeToString(userPhoto.getPhoto());
        }

        return "Profile picture doesn't exist!";
    }

    //Helper method to copy UserRequestDTO to User
    public User copyUserRequestDTOToUser(UserRequestDTO userRequestDTO, User user) {
        user.setName(userRequestDTO.getName());
        user.setPhone(userRequestDTO.getPhone());
        user.setEmail(userRequestDTO.getEmail());
        user.setRole(userRequestDTO.getRole());
        user.setUsername(userRequestDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));

        return user;
    }

    //Helper method to convert User to UserResponseDTO
    public UserResponseDTO convertUserToUserResponseDTO(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .phone(user.getPhone())
                .email(user.getEmail())
                .role(user.getRole())
                .username(user.getUsername())
                .build();
    }

    //Helper method to convert List<User> to List<UserResponseDTO>
    public List<UserResponseDTO> convertListOfUserToListOfUserResponseDTO(List<User> userList) {
        List<UserResponseDTO> userResponseDTOList = new ArrayList<>();

        for(User user : userList) {
            UserResponseDTO userResponseDTO = convertUserToUserResponseDTO(user);

            userResponseDTOList.add(userResponseDTO);
        }

        return userResponseDTOList;
    }

}
