package com.example.userservice;

import com.example.userservice.controllers.UserController;
import com.example.userservice.model.User;
import com.example.userservice.payload.request.LoginRequest;
import com.example.userservice.payload.request.SignupRequest;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.UserService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    //@MockBean
    //private UserRepository userRepository;

    private final UserService userService = Mockito.mock(UserService.class);
    private final UserRepository userRepository = Mockito.mock(UserRepository.class);
    private final PasswordEncoder encoder = Mockito.mock(PasswordEncoder.class);

    private final UserController userController = new UserController(userService, userRepository, encoder);


    @Test
    public void testRegisterUser() {
        SignupRequest signupRequest = new SignupRequest("testuser", "test@example.com", "password");

        when(userRepository.existsByUsername("testuser")).thenReturn(false);
        when(userRepository.existsByEmail("test@example.com")).thenReturn(false);
        when(encoder.encode("password")).thenReturn("encodedPassword");

        ResponseEntity<?> response = userController.registerUser(signupRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

//    @Test
//    public void testLoginUser() {
//        // Create test data
//        String username = "testUser";
//        String password = "password";
//        LoginRequest loginRequest = new LoginRequest(username, password);
//        String encodedPassword = encoder.encode(password);
//        User user = new User(username, "test@example.com", encodedPassword);
//
//        // Stubbing repository method
//        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
//
//        // Test controller method
//        ResponseEntity<User> response = userController.loginUser(loginRequest);
//
//        // Assertions
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//
//        // Verify that the returned user matches the test user
//        User returnedUser = response.getBody();
//        assertNotNull(returnedUser);
//        assertEquals(username, returnedUser.getUsername());
//        // You might want to verify other user attributes as well
//
//        // Verify that the password matches
//        assertTrue(encoder.matches(password, returnedUser.getPassword()));
//    }
}
