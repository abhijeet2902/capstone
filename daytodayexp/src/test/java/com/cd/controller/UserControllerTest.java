package com.cd.controller;

import com.cd.dto.LoginDto;
import com.cd.dto.UserInputDto;
import com.cd.dto.UserOutputDto;
import com.cd.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUserShouldReturnUserInputDto() {
        UserInputDto userInputDto = new UserInputDto();
        when(userService.createUser (any(UserInputDto.class))).thenReturn(userInputDto);

        UserInputDto result = userController.createUser (userInputDto);

        assertNotNull(result);
        verify(userService, times(1)).createUser (userInputDto);
    }

    @Test
    void login_ShouldReturnUserOutputDto_WhenCredentialsAreValid() {
        LoginDto loginDto = new LoginDto();
        UserOutputDto userOutputDto = new UserOutputDto();
        when(userService.login(loginDto)).thenReturn(Optional.of(userOutputDto));

        ResponseEntity<UserOutputDto> response = userController.login(loginDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userOutputDto, response.getBody());
    }

    @Test
    void login_ShouldReturnUnauthorized_WhenCredentialsAreInvalid() {
        LoginDto loginDto = new LoginDto();
        when(userService.login(loginDto)).thenReturn(Optional.empty());

        ResponseEntity<UserOutputDto> response = userController.login(loginDto);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    void getAllUsers_ShouldReturnListOfUsers() {
        when(userService.getAllUsers()).thenReturn(Collections.emptyList());

        List<UserOutputDto> result = userController.getAllUsers();

        assertNotNull(result);
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void getUserById_ShouldReturnUserOutputDto_WhenUserExists() {
        UserOutputDto userOutputDto = new UserOutputDto();
        when(userService.getUserById(1)).thenReturn(Optional.of(userOutputDto));

        ResponseEntity<UserOutputDto> response = userController.getUserById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userOutputDto, response.getBody());
    }

    @Test
    void getUserById_ShouldReturnNotFound_WhenUserDoesNotExist() {
        when(userService.getUserById(1)).thenReturn(Optional.empty());

        ResponseEntity<UserOutputDto> response = userController.getUserById(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }



    @Test
    void deleteUser_ShouldReturnNoContent() {
        doNothing().when(userService).deleteUser (1);

        ResponseEntity<Void> response = userController.deleteUser (1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(userService, times(1)).deleteUser (1);
    }
}