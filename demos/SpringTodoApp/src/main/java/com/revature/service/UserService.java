package com.revature.service;

import com.revature.model.User;
import com.revature.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAllUsers();
    Optional<User> findUserById(Long id);
    User createUser(User user);
    Optional<User> updateUser(Long id, User user);
    boolean deleteUser(Long id);

    Optional<User> validateUser(String email, String password);
}
