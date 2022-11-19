package com.mycompany.user.system.util;

import com.mycompany.user.system.dto.Attachment;
import com.mycompany.user.system.model.AttachmentModel;
import com.mycompany.user.system.model.User;
import com.mycompany.user.system.util.ValidationUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidationUtilTest {

    @Test
    void test_validateAttachmentList_emptyArray_falseAssertion() {
        assertFalse(ValidationUtil.validateAttachmentList(new ArrayList<>()));
    }

    @Test
    void test_validateAttachmentList_oneElement_falseAssertion() {
        List<AttachmentModel> attachmentList = new ArrayList<>();
        AttachmentModel attachment = new AttachmentModel();
        attachment.setName("doc1");
        attachmentList.add(attachment);
        assertFalse(ValidationUtil.validateAttachmentList(attachmentList));
    }

    @Test
    void test_validateAttachmentList_twoElement_trueAssertion() {
        List<AttachmentModel> attachmentList = new ArrayList<>();
        AttachmentModel attachment1 = new AttachmentModel();
        attachment1.setName("doc1");
        attachmentList.add(attachment1);
        AttachmentModel attachment2 = new AttachmentModel();
        attachment2.setName("doc2");
        attachmentList.add(attachment2);
        assertTrue(ValidationUtil.validateAttachmentList(attachmentList));
    }


    @Test
    void test_isExpiredDate_expiredDate() {
        assertTrue(ValidationUtil.isExpiredDate(LocalDateTime.parse("2018-05-05T11:50:55")));
    }

    @Test
    void test_isExpiredDate_notExpiredDate() {
        assertFalse(ValidationUtil.isExpiredDate(LocalDateTime.parse("4044-10-21T10:00:00")));
    }

    @Test
    void test_isExpiredDate_expiredDate_DifferentFormat() {
        assertTrue(ValidationUtil.isExpiredDate(LocalDateTime.parse("2010-12-18T07:53:34")));
    }

}
