package com.mycompany.user.system.controller;

import com.mycompany.user.system.model.UserFlowRequest;
import com.mycompany.user.system.service.UserFlowService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/request")
public class UserFlowController {

    private final UserFlowService userFlowService;

    public UserFlowController(UserFlowService userFlowService) {
        this.userFlowService = userFlowService;
    }

    @PostMapping(value = "/submit", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> submitUserRequest(@Validated @RequestBody UserFlowRequest userFlowRequest) {
        return userFlowService.submitRequest(userFlowRequest);
    }

}
