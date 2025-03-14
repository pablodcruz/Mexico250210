package com.revature.service;

import com.revature.model.Role;
import com.revature.model.User;
import com.revature.repository.RoleRepository;
import com.revature.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
//                .map(user -> {
//                    User userToReturn = new User();
//                   userToReturn.setUserId(user.getUserId());
//                   userToReturn.setRole(user.getRole());
//                    return userToReturn;
//                });
    }
    @Override
    public User createUser(User user) {
        System.out.println("===============================================================================");
        System.out.println(user.toString());
        Long roleId = user.getRole().getRoleId();
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found with id " + roleId));
        user.setRole(role);
        return userRepository.save(user);
    }

    @Override
    public Optional<User> validateUser(String email, String password) {
        // For simplicity, we assume passwords are stored in plain text.
        // In production, you'd use a PasswordEncoder/BCrypt to compare hashed passwords.
        return userRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public Optional<User> updateUser(Long id, User userDetails) {
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setEmail(userDetails.getEmail());
            existingUser.setPassword(userDetails.getPassword());
            // Update additional fields as necessary
            return userRepository.save(existingUser);
        });
    }

    @Override
    public boolean deleteUser(Long id) {
        return userRepository.findById(id).map(user -> {
            userRepository.delete(user);
            return true;
        }).orElse(false);
    }
}
