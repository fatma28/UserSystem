package com.mycompany.user.system.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Attachment {

    @Id
    @GeneratedValue
    @JsonProperty(value = "attachment_id")
    private int attachmentId;

    //    @Column(unique = true)
    private String name;
}
