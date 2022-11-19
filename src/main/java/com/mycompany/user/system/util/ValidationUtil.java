package com.mycompany.user.system.util;

import com.mycompany.user.system.dto.Attachment;
import com.mycompany.user.system.model.AttachmentModel;
import com.mycompany.user.system.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ValidationUtil {

    private ValidationUtil() {
    }

    public static boolean validateAttachmentList(List<AttachmentModel> attachmentList) {
        return Optional.ofNullable(attachmentList)
                .filter(Objects::nonNull)
                .filter(attachments -> attachments.size() >= 2)
                .isPresent();

    }

    public static boolean isExpiredDate(LocalDateTime date) {
        return Optional.ofNullable(date)
                .filter(expiryDate -> LocalDateTime.now().isAfter(expiryDate))
                .isPresent();
    }
}