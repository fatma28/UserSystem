package com.mycompany.user.system.service;

import com.mycompany.user.system.dto.UserInfo;
import com.mycompany.user.system.model.AttachmentModel;
import com.mycompany.user.system.model.Response;
import com.mycompany.user.system.model.User;
import com.mycompany.user.system.model.UserFlowRequest;
import com.mycompany.user.system.repository.FlowRepository;
import com.mycompany.user.system.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserFlowServiceTest {

    private UserFlowService userFlowService;
    private FlowRepository flowRepository = mock(FlowRepository.class);
    private UserRepository userRepository = mock(UserRepository.class);

    @BeforeEach
    public void setup() {
        userFlowService = spy(new UserFlowService(flowRepository, userRepository));
    }

    @Test
    void test_submitRequest_emptyAttachment_returnFailure() {
        UserFlowRequest userFlowRequest = new UserFlowRequest();
        userFlowRequest.setAttachmentList(new ArrayList<>());
        ResponseEntity<?> responseEntity = userFlowService.submitRequest(userFlowRequest);
        Response failureResponse = (Response) responseEntity.getBody();
        assertEquals(1001, failureResponse.getCode());
        assertEquals("Invalid number of attachments!", failureResponse.getReason());
        assertEquals("Invalid number of attachments!", failureResponse.getMessage());
    }

    @Test
    void test_submitRequest_oneAttachment_returnFailure() {
        UserFlowRequest userFlowRequest = new UserFlowRequest();
        AttachmentModel attachment = new AttachmentModel();
        attachment.setName("doc1");
        List<AttachmentModel> attachmentList = new ArrayList<>();
        attachmentList.add(attachment);

        userFlowRequest.setAttachmentList(attachmentList);
        ResponseEntity<?> responseEntity = userFlowService.submitRequest(userFlowRequest);
        Response failureResponse = (Response) responseEntity.getBody();
        assertEquals(1001, failureResponse.getCode());
        assertEquals("Invalid number of attachments!", failureResponse.getReason());
        assertEquals("Invalid number of attachments!", failureResponse.getMessage());
    }

    @Test
    void test_submitRequest_userNotFound_returnFailure() {
        UserFlowRequest userFlowRequest = new UserFlowRequest();
        List<AttachmentModel> attachmentList = getValidAttachmentList();
        userFlowRequest.setAttachmentList(attachmentList);

        User user = new User(11, "", LocalDateTime.now(), new ArrayList<>());
        userFlowRequest.setUser(user);
        when(userRepository.findById(user.getCivilId())).thenReturn(Optional.empty());

        ResponseEntity<?> responseEntity = userFlowService.submitRequest(userFlowRequest);
        Response failureResponse = (Response) responseEntity.getBody();
        assertEquals(1002, failureResponse.getCode());
        assertEquals("User is not found!", failureResponse.getReason());
        assertEquals("User is not found!", failureResponse.getMessage());
    }

    @Test
    void test_submitRequest_expiredDate_returnFailure() {
        UserFlowRequest userFlowRequest = new UserFlowRequest();
        List<AttachmentModel> attachmentList = getValidAttachmentList();
        userFlowRequest.setAttachmentList(attachmentList);

        UserInfo userInfo = new UserInfo();
        userInfo.setCivilId(11);
        userInfo.setExpiryDate(LocalDateTime.parse("2010-10-21T10:00:00"));

        User user = new User(11, "", LocalDateTime.now(), new ArrayList<>());
        userFlowRequest.setUser(user);

        when(userRepository.findByCivilId(userInfo.getCivilId())).thenReturn(userInfo);

        ResponseEntity<?> responseEntity = userFlowService.submitRequest(userFlowRequest);
        Response failureResponse = (Response) responseEntity.getBody();
        assertEquals(1003, failureResponse.getCode());
        assertEquals("Your date is expired!", failureResponse.getReason());
        assertEquals("Request can not be submitted due to expiry date!", failureResponse.getMessage());
    }

    private static List<AttachmentModel> getValidAttachmentList() {
        List<AttachmentModel> attachmentList = new ArrayList<>();

        AttachmentModel attachment = new AttachmentModel();
        attachment.setName("doc1");
        attachmentList.add(attachment);

        AttachmentModel attachment2 = new AttachmentModel();
        attachment2.setName("doc2");
        attachmentList.add(attachment2);
        return attachmentList;
    }


}
