package com.mycompany.user.system.service;

import com.mycompany.user.system.dto.UserInfo;
import com.mycompany.user.system.model.ErrorMessageEnum;
import com.mycompany.user.system.model.Response;
import com.mycompany.user.system.model.User;
import com.mycompany.user.system.repository.UserRepository;
import com.mycompany.user.system.util.UserBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static com.mycompany.user.system.util.UserBuilder.mapToUserModel;
import static com.mycompany.user.system.util.UserBuilder.mapToUserInfoDTO;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<Response> saveUser(User user) {
        if (user.getCivilId() == 0) {
            new ResponseEntity<>(new Response(ErrorMessageEnum.MISSING_OR_INVALID_CIVIL_ID.getErrorCode(), ErrorMessageEnum.MISSING_OR_INVALID_CIVIL_ID.getMessage(), ErrorMessageEnum.MISSING_OR_INVALID_CIVIL_ID.getReason()), ErrorMessageEnum.MISSING_OR_INVALID_CIVIL_ID.getHttpStatus());
        }
        UserInfo userInfo = userRepository.findByCivilId(user.getCivilId());
        if (userInfo == null) {
            mapToUserModel(userRepository.save(userInfo));
        }
        return Optional.ofNullable(user)
                .map(savedUser -> new Response(1005, "User is saved Successfully!", "User is saved Successfully!"))
                .map(successResponse -> new ResponseEntity<>(successResponse, HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(new Response(ErrorMessageEnum.FAILED_TO_SAVE_USER.getErrorCode(), ErrorMessageEnum.FAILED_TO_SAVE_USER.getMessage(), ErrorMessageEnum.FAILED_TO_SAVE_USER.getReason()), ErrorMessageEnum.FAILED_TO_SAVE_USER.getHttpStatus()));
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    public ResponseEntity<?> getUserInfo(int id) {
        Optional<ResponseEntity<?>> userResponseEntity = userRepository.findById(id)
                .map(UserBuilder::mapToUserModel)
                .map(successResponse -> new ResponseEntity<>(successResponse, HttpStatus.CREATED));

        if (userResponseEntity.isPresent()) {
            return userResponseEntity.get();
        } else {
            return new ResponseEntity<>(new Response(ErrorMessageEnum.FAILED_TO_GET_USER.getErrorCode(), ErrorMessageEnum.FAILED_TO_GET_USER.getMessage(), ErrorMessageEnum.FAILED_TO_GET_USER.getReason()), ErrorMessageEnum.FAILED_TO_GET_USER.getHttpStatus());
        }
    }

    public Iterable<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        Iterator<UserInfo> userInfoIterable = userRepository.findAll().iterator();
        while (userInfoIterable.hasNext()) {
            userList.add(mapToUserModel(userInfoIterable.next()));
        }
        return userList;
    }
}
