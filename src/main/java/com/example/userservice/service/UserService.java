package com.example.userservice.service;

import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
   @Autowired
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder encoder){
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(String userId, User updatedUser) {
        ObjectId objectId = new ObjectId(userId);
        User existingUser = userRepository.findById(objectId).orElse(null);
        if (existingUser != null) {
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setEmail(updatedUser.getEmail());

            return userRepository.save(existingUser);
        }
        return null; // User not found
    }

    public void deleteUser(String userId) {
        ObjectId objectId = new ObjectId(userId);
        userRepository.deleteById(objectId);
    }

    public User getUserById(String userId) {
        try {
            ObjectId objectId = new ObjectId(userId);
            return userRepository.findById(objectId).orElse(null);
        } catch (IllegalArgumentException e) {
            return null; // Invalid ObjectId format
        }
        //return userRepository.findById(userId).orElse(null);
    }

    public User loginUser(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (((Optional<?>) userOptional).isPresent()) {
            User user = userOptional.get();
            if (encoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null; // Invalid credentials
    }
}
