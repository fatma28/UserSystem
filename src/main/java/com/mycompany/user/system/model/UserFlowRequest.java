package com.mycompany.user.system.model;

import com.mycompany.user.system.dto.Attachment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFlowRequest {

    private String name;
    @NotNull(message = "Status can not be null or empty")
    private RequestStatus status;
    @NotNull(message = "Attachment can not be null or empty")
    private List<AttachmentModel> attachmentList;
    @NotNull(message = "User civilId can not be null or empty")
    private User user;
}
