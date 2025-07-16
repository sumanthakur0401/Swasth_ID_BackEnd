package com.org.swasth_id_backend.service;

import com.org.swasth_id_backend.dto.UserDto;
import com.org.swasth_id_backend.entity.User;

import java.util.List;

public interface UserService {

    User registerUser(UserDto userDto);

    User getUserById(Long id);

    User getUserByEmail(String email);

    List<User> getAllUsers();

    User updateUser(Long id, UserDto userDto);

    void deleteUser(Long id);
}
