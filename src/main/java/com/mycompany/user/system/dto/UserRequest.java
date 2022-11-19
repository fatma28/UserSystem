package com.mycompany.user.system.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mycompany.user.system.model.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @Id
    @GeneratedValue
    @JsonProperty(value = "request_id")
    private int requestId;
    private String name;
    //    @NotBlank(message = "status can not be null or empty")
    private RequestStatus status;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "request_id")
    private List<Attachment> attachmentList;

    @JsonProperty(value = "civilId")
    private int userID;

    public UserRequest(String name, RequestStatus status, List<Attachment> attachmentList, int userID) {
        this.name = name;
        this.status = status;
        this.attachmentList = attachmentList;
        this.userID = userID;
    }
}
