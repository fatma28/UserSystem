package com.mycompany.user.system.util;

import com.mycompany.user.system.dto.UserInfo;
import com.mycompany.user.system.dto.UserRequest;
import com.mycompany.user.system.model.RequestStatus;
import com.mycompany.user.system.model.User;
import com.mycompany.user.system.model.UserFlowRequest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

public class UserBuilderTest {

    @Test
    void test_buildUserRequest() {
        UserFlowRequest userFlowRequest = new UserFlowRequest();
        userFlowRequest.setUser(new User(11, "", LocalDateTime.now(), new ArrayList<>()));
        userFlowRequest.setName("name");
        userFlowRequest.setStatus(RequestStatus.ACCEPTED);
        userFlowRequest.setAttachmentList(new ArrayList<>());
        UserRequest userRequest = UserBuilder.buildUserRequestDTO(userFlowRequest);
        assertEquals(11, userRequest.getUserID());
        assertEquals("ACCEPTED", userRequest.getStatus().getCategory());
    }

    @Test
    void test_mapToUserInfoDTO() {
        User user = new User();
        user.setCivilId(11);
        user.setName("name");
        UserInfo userInfo = UserBuilder.mapToUserInfoDTO(user);
        assertEquals(11, userInfo.getCivilId());
        assertEquals("name", userInfo.getName());
    }
}
