package com.revature.demo.service;

import com.revature.demo.model.Users;
import com.revature.demo.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository personRepository) {
        this.userRepository = personRepository;
    }

    public List<Users> getAllPeople() {
        return userRepository.findAll();
    }

    public Users saveUser(Users users) {

        return userRepository.save(users);
    }
}
