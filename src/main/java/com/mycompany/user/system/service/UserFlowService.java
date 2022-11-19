package com.mycompany.user.system.service;

import com.mycompany.user.system.dto.UserInfo;
import com.mycompany.user.system.dto.UserRequest;
import com.mycompany.user.system.model.*;
import com.mycompany.user.system.repository.FlowRepository;
import com.mycompany.user.system.repository.UserRepository;
import com.mycompany.user.system.util.UserBuilder;
import com.mycompany.user.system.util.ValidationUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.mycompany.user.system.util.UserExceptionHandler.getFailureResponse;

@Service
public class UserFlowService {

    private final FlowRepository flowRepository;

    private final UserRepository userRepository;

    public UserFlowService(FlowRepository flowRepository, UserRepository userRepository) {
        this.flowRepository = flowRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> submitRequest(UserFlowRequest flowRequest) {
        if (!ValidationUtil.validateAttachmentList(flowRequest.getAttachmentList())) {
            return getFailureResponse(ErrorMessageEnum.INVALID_ATTACHMENTS_NUMBER.getErrorCode(), ErrorMessageEnum.INVALID_ATTACHMENTS_NUMBER.getMessage(), ErrorMessageEnum.INVALID_ATTACHMENTS_NUMBER.getReason(), ErrorMessageEnum.INVALID_ATTACHMENTS_NUMBER.getHttpStatus());
        }

        UserInfo userInfo = userRepository.findByCivilId(flowRequest.getUser().getCivilId());

        if (userInfo == null) {
            return getFailureResponse(ErrorMessageEnum.USER_NOT_FOUND.getErrorCode(), ErrorMessageEnum.USER_NOT_FOUND.getMessage(), ErrorMessageEnum.USER_NOT_FOUND.getReason(), ErrorMessageEnum.USER_NOT_FOUND.getHttpStatus());
        }

        if (ValidationUtil.isExpiredDate(userInfo.getExpiryDate())) {
            return getFailureResponse(ErrorMessageEnum.EXPIRED_DATE.getErrorCode(), ErrorMessageEnum.EXPIRED_DATE.getMessage(), ErrorMessageEnum.EXPIRED_DATE.getReason(), ErrorMessageEnum.EXPIRED_DATE.getHttpStatus());
        }

        UserRequest userRequest1 = UserBuilder.buildUserRequestDTO(flowRequest);
        UserRequest savedUserRequest = flowRepository.save(userRequest1);
        return new ResponseEntity<>(savedUserRequest, HttpStatus.CREATED);
    }

}
