package com.revature.todo.service;

import com.revature.todo.dao.UserDao;
import com.revature.todo.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserDao userDaoMock;
    private UserService userService;

    //@BeforeEach: We create a mock UserDao and pass it into a new UserService before every test.
    @BeforeEach
    void setUp() {
        // Create a mock instance of UserDao
        userDaoMock = Mockito.mock(UserDao.class);
        // Inject the mock into the UserService
        userService = new UserService(userDaoMock);
    }

    @Test
    void registerUser_ShouldReturnNewUser_WhenUsernameNotExists() {
        // Arrange
        String username = "alice";
        String password = "secret";
        User userToReturn = new User();
        userToReturn.setId(1);
        userToReturn.setUsername(username);
        userToReturn.setPasswordHash("HASHED_" + password);
        userToReturn.setDateOfBirth(LocalDate.parse("01-01-1999"));

        //when(...).thenReturn(...): Tells Mockito what to return when a specific method on the mock is called.
        //Example: when(userDaoMock.getUserByUsername("alice")).thenReturn(null); means getUserByUsername("alice") returns null.
        // We mock getUserByUsername to return null => user doesn't exist yet. So we are simulating that username does not already exist.
        when(userDaoMock.getUserByUsername(username)).thenReturn(null);

        // We mock createUser to return our user with an assigned ID
        // any(...): this is a Mockito matcher that says “I don’t care about the exact argument.”
        when(userDaoMock.createUser(any(User.class))).thenReturn(userToReturn);

        // Act
        User createdUser = userService.registerUser(username, password, LocalDate.parse("01-01-1999"));

        // Assert
        assertNotNull(createdUser);
        assertEquals(1, createdUser.getId());
        assertEquals(username, createdUser.getUsername());
        assertEquals("HASHED_secret", createdUser.getPasswordHash());

        //verify(...): Ensures a mock method was called or not called.
        //Verify userDao calls
        verify(userDaoMock).getUserByUsername(username);
        verify(userDaoMock).createUser(any(User.class));
    }

    @Test
    void registerUser_ShouldReturnNull_WhenUsernameAlreadyExists() {
        // Arrange
        String username = "existingUser";
        String password = "anyPass";

        // If userDaoMock.getUserByUsername(...) returns a user, the service should return null
        User existingUser = new User();
        existingUser.setUsername(username);
        when(userDaoMock.getUserByUsername(username)).thenReturn(existingUser);

        // Act
        User result = userService.registerUser(username, password, LocalDate.parse("01-01-1999"));

        // Assert
        assertNull(result, "Expected null when username already exists");
        verify(userDaoMock).getUserByUsername(username);
        // Notice we do NOT expect createUser(...) to be called if user already exists
        verify(userDaoMock, never()).createUser(any(User.class));

    }

    @Test
    void loginUser_ShouldReturnTrue_WhenCredentialsMatch() {
        // Arrange
        String username = "bob";
        String rawPassword = "pass123";
        String hashedPassword = "HASHED_pass123";

        User existingUser = new User();
        existingUser.setUsername(username);
        existingUser.setPasswordHash(hashedPassword);

        when(userDaoMock.getUserByUsername(username)).thenReturn(existingUser);

        // Act
        boolean success = userService.loginUser(username, rawPassword);

        // Assert
        assertTrue(success, "Login should succeed when passwords match");
        verify(userDaoMock).getUserByUsername(username);
    }

    @Test
    void loginUser_ShouldReturnFalse_WhenUserNotFound() {
        // Arrange
        String username = "nonExistentUser";
        when(userDaoMock.getUserByUsername(username)).thenReturn(null);

        // Act
        boolean success = userService.loginUser(username, "somePass");

        // Assert
        assertFalse(success, "Login should fail if user doesn't exist");
        verify(userDaoMock).getUserByUsername(username);
    }

    @Test
    void loginUser_ShouldReturnFalse_WhenPasswordDoesNotMatch() {
        // Arrange
        String username = "charlie";
        String rawPassword = "wrongPassword";
        String storedHashed = "HASHED_correctPassword";

        User existingUser = new User();
        existingUser.setUsername(username);
        existingUser.setPasswordHash(storedHashed);

        when(userDaoMock.getUserByUsername(username)).thenReturn(existingUser);

        // Act
        boolean success = userService.loginUser(username, rawPassword);

        // Assert
        assertFalse(success, "Login should fail if password doesn't match");
        verify(userDaoMock).getUserByUsername(username);
    }

    @Test
    void getAllUsers_ShouldReturnListOfUsers() {
        // Arrange
        List<User> mockUsers = Arrays.asList(
                new User(1, "john", "HASHED_123"),
                new User(2, "jane", "HASHED_abc")
        );
        when(userDaoMock.getAllUsers()).thenReturn(mockUsers);

        // Act
        List<User> result = userService.getAllUsers();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("john", result.get(0).getUsername());
        assertEquals("jane", result.get(1).getUsername());

        verify(userDaoMock).getAllUsers();
    }
}
