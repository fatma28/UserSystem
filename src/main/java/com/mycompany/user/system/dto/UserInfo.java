package com.mycompany.user.system.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "user_info")
@Data
public class UserInfo {

    @Id
    @GeneratedValue
    @JsonProperty(value = "user_id")
    private int userId;
    @Column(unique = true)
    private int civilId;
    private String name;
    private LocalDateTime expiryDate;
    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id")
    private List<UserRequest> request;
}
