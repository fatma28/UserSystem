package com.mycompany.user.system.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentModel {

    private int attachmentId;
    private String name;
    private int requestId;

}
