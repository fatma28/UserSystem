package com.mycompany.user.system.service;

import com.mycompany.user.system.dto.UserInfo;
import com.mycompany.user.system.model.User;
import com.mycompany.user.system.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    private UserRepository userRepository = mock(UserRepository.class);
    private UserService userService;

    @BeforeEach
    public void setup() {
        userService = spy(new UserService(userRepository));
    }

    @Test
    void test_saveUser_userExists() {
        User user = new User(11, "name", LocalDateTime.now(), new ArrayList<>());
        UserInfo userInfo = new UserInfo();
        userInfo.setCivilId(123);
        when(userRepository.findByCivilId(user.getCivilId())).thenReturn(userInfo);
        when(userRepository.save(any())).thenReturn(Optional.of(userInfo));
        assertEquals("User is saved Successfully!", userService.saveUser(user).getBody().getMessage());
        assertEquals(1005, userService.saveUser(user).getBody().getCode());
    }

    @Test
    void test_getUserInfo(){
        UserInfo userInfo = new UserInfo();
        userInfo.setCivilId(11);
        userInfo.setName("name");
        when(userRepository.findById(1)).thenReturn(Optional.of(userInfo));
        User user = (User) userService.getUserInfo(1).getBody();
        assertEquals(11, user.getCivilId());
        assertEquals("name", user.getName());
    }

    @Test
    void test_getAllUsers() {
        UserInfo userInfo = new UserInfo();
        userInfo.setCivilId(11);
        userInfo.setName("name");
        when(userRepository.findAll()).thenReturn(List.of(userInfo));
        Iterable<User> userIterable = userService.getAllUsers();
        userIterable.forEach(user -> assertEquals("name", user.getName()));
    }
}