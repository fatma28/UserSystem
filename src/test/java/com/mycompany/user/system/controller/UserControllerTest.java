package com.mycompany.user.system.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mycompany.user.system.model.User;
import com.mycompany.user.system.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest {
    @InjectMocks
    private UserController userController;
    private MockMvc mockMvc;
    private ObjectWriter objectWriter;
    @Mock
    private UserService userService;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.registerModule(new JavaTimeModule());
        objectWriter = mapper.writer().withDefaultPrettyPrinter();

    }

    @Test
    void test_getUserById_emptyId() throws Exception {
        mockMvc.perform(get("/user/get/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void test_getUserById_successfulResponse() throws Exception {
        mockMvc.perform(get("/user/get/")
                        .param("civilId", String.valueOf(11))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void test_getAllUsers_successfulResponse() throws Exception {
        mockMvc.perform(get("/user/getAll/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void test_deleteUser_emptyId() throws Exception {
        mockMvc.perform(get("/user/delete/")
                        .param("civilId", String.valueOf(11))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }

}
