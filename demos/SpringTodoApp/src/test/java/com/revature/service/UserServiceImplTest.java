package com.revature.service;

import com.revature.model.Role;
import com.revature.model.User;
import com.revature.repository.RoleRepository;
import com.revature.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Enables Mockito annotations in JUnit 5
public class UserServiceImplTest {

    // Create a mock instance of UserRepository.
    @Mock
    private UserRepository userRepository;

    // Create a mock instance of RoleRepository.
    @Mock
    private RoleRepository roleRepository;

    // Create an instance of UserServiceImpl and inject the mocks.
    @InjectMocks
    private UserServiceImpl userServiceImpl;

    // Test objects used for the tests.
    private User testUser;
    private Role testRole;

    @BeforeEach
    public void setUp() {
        // Set up a test Role.
        testRole = new Role();
        testRole.setRoleId(1L);
        testRole.setRoleName("ADMIN");

        // Set up a test User and associate it with the test Role.
        testUser = new User();
        testUser.setUserId(1L);
        testUser.setEmail("test@example.com");
        testUser.setPassword("password");
        testUser.setRole(testRole);
    }

    @Test
    public void testFindAllUsers() {
        // Arrange: When findAll() is called, return a list containing testUser.
        when(userRepository.findAll()).thenReturn(List.of(testUser));

        // Act: Call the service method.
        List<User> users = userServiceImpl.findAllUsers();

        // Assert: Verify that one user is returned and the email matches.
        assertEquals(1, users.size());
        assertEquals("test@example.com", users.get(0).getEmail());
        // Verify that findAll() on the repository was called exactly once.
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testFindUserById() {
        // Arrange: When findById() is called with id=1L, return an Optional containing testUser.
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));

        // Act: Call the service method.
        Optional<User> result = userServiceImpl.findUserById(1L);

        // Assert: Verify that the user is present and the email is as expected.
        assertTrue(result.isPresent());
        assertEquals("test@example.com", result.get().getEmail());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateUser() {
        // Arrange: Create a new user with a transient Role (only roleId is set).
        User newUser = new User();
        newUser.setEmail("test@example.com");
        newUser.setPassword("password");
        Role transientRole = new Role();
        transientRole.setRoleId(1L); // Only id is provided.
        newUser.setRole(transientRole);

        // When createUser is called, first the service loads the persistent role.
        when(roleRepository.findById(1L)).thenReturn(Optional.of(testRole));
        // Then, the repository's save method returns testUser.
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        // Act: Call createUser on the service.
        User result = userServiceImpl.createUser(newUser);

        // Assert: Verify the result is not null and email is correct.
        assertNotNull(result);
        assertEquals("test@example.com", result.getEmail());
        // Ensure the role repository and save methods are called appropriately.
        verify(roleRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(newUser);
    }

    @Test
    public void testValidateUser() {
        // Arrange: When findByEmailAndPassword is called, return testUser.
        when(userRepository.findByEmailAndPassword("test@example.com", "password"))
                .thenReturn(Optional.of(testUser));

        // Act: Call validateUser with email and password.
        Optional<User> result = userServiceImpl.validateUser("test@example.com", "password");

        // Assert: Verify that a user is returned.
        assertTrue(result.isPresent());
        verify(userRepository, times(1))
                .findByEmailAndPassword("test@example.com", "password");
    }

    @Test
    public void testUpdateUser() {
        // Arrange: Create a user with updated details.
        User updateDetails = new User();
        updateDetails.setEmail("updated@example.com");
        updateDetails.setPassword("newpassword");

        // When findById() is called with id=1L, return testUser.
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));
        // When save() is called, simply return the updated user.
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User u = invocation.getArgument(0);
            return u;
        });

        // Act: Update the user.
        Optional<User> result = userServiceImpl.updateUser(1L, updateDetails);

        // Assert: Verify that the email and password have been updated.
        assertTrue(result.isPresent());
        assertEquals("updated@example.com", result.get().getEmail());
        assertEquals("newpassword", result.get().getPassword());
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(testUser);
    }

    @Test
    public void testDeleteUser() {
        // Arrange: When findById() is called with id=1L, return testUser.
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));

        // Act: Call deleteUser.
        boolean deleted = userServiceImpl.deleteUser(1L);

        // Assert: Verify that deletion occurred.
        assertTrue(deleted);
        verify(userRepository, times(1)).delete(testUser);
    }
}
