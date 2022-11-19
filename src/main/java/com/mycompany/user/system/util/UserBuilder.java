package com.mycompany.user.system.util;

import com.mycompany.user.system.dto.Attachment;
import com.mycompany.user.system.dto.UserInfo;
import com.mycompany.user.system.model.AttachmentModel;
import com.mycompany.user.system.model.User;
import com.mycompany.user.system.model.UserFlowRequest;
import com.mycompany.user.system.dto.UserRequest;

import java.util.ArrayList;
import java.util.List;

public class UserBuilder {

    public static UserRequest buildUserRequestDTO(UserFlowRequest userFlowRequest) {
        UserRequest userRequest = new UserRequest();
        userRequest.setUserID(userFlowRequest.getUser().getCivilId());
        userRequest.setName(userFlowRequest.getName());
        userRequest.setAttachmentList(getAttachmentList(userFlowRequest));
        userRequest.setStatus(userFlowRequest.getStatus());
        return userRequest;
    }

    private static List<Attachment> getAttachmentList(UserFlowRequest userFlowRequest) {
        List<Attachment> attachmentList = new ArrayList<>();
        for (AttachmentModel model: userFlowRequest.getAttachmentList()) {
            Attachment attachment = new Attachment();
            attachment.setName(model.getName());
            attachmentList.add(attachment);
        }
        return attachmentList;
    }

    public static UserInfo mapToUserInfoDTO(User user) {
        UserInfo userInfo = new UserInfo();
        userInfo.setName(user.getName());
        userInfo.setCivilId(user.getCivilId());
        userInfo.setExpiryDate(user.getExpiryDate());
        return userInfo;
    }

    public static User mapToUserModel(UserInfo userInfo) {
        User user = new User();
        user.setName(userInfo.getName());
        user.setCivilId(userInfo.getCivilId());
        user.setExpiryDate(userInfo.getExpiryDate());
        user.setRequest(convertToUserFlowRequest(userInfo));
        return user;
    }

    private static List<UserFlowRequest> convertToUserFlowRequest(UserInfo userInfo) {
        List<UserFlowRequest> userFlowRequestList = new ArrayList<>();
//        List<UserRequest> request = userInfo.getRequest();
//        userInfo.getRequest().get(0);
//        for (UserRequest userRequest : userInfo.getRequest()) {
//            userFlowRequestList.add(new UserFlowRequest(userRequest.getName(),
//                    userRequest.getStatus(), userRequest.getAttachmentList(),
//                    mapToUser(userRequest.getUser())));
//        }
        return userFlowRequestList;
    }
}
