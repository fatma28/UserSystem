package com.mycompany.user.system.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mycompany.user.system.dto.Attachment;
import com.mycompany.user.system.model.AttachmentModel;
import com.mycompany.user.system.model.RequestStatus;
import com.mycompany.user.system.model.User;
import com.mycompany.user.system.model.UserFlowRequest;
import com.mycompany.user.system.service.UserFlowService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserFlowControllerTest {
    @InjectMocks
    private UserFlowController userFlowController;
    private MockMvc mockMvc;
    private ObjectWriter objectWriter;
    @Mock
    private UserFlowService userFlowService;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userFlowController).build();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        objectWriter = mapper.writer().withDefaultPrettyPrinter();

    }

    @Test
    void test_submitUserRequest_emptyAttachment() throws Exception {
        User user = new User();
        user.setName("name");
        UserFlowRequest userFlowRequest = new UserFlowRequest();
        userFlowRequest.setAttachmentList(new ArrayList<>());
        userFlowRequest.setUser(user);
        String requestJson = objectWriter.writeValueAsString(userFlowRequest);
        mockMvc.perform(post("/request/submit").contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void test_submitUserRequest_successfulResponse() throws Exception {
        User user = new User();
        user.setName("name");
        UserFlowRequest userFlowRequest = new UserFlowRequest();
        AttachmentModel attachment = new AttachmentModel();
        attachment.setName("doc1");
        List<AttachmentModel> attachmentList = new ArrayList<>();
        attachmentList.add(attachment);
        userFlowRequest.setAttachmentList(attachmentList);
        userFlowRequest.setStatus(RequestStatus.ACCEPTED);
        userFlowRequest.setUser(user);
        String requestJson = objectWriter.writeValueAsString(userFlowRequest);
        mockMvc.perform(post("/request/submit").contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk());
    }
}
