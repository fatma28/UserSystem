package com.mycompany.user.system.model;

import org.springframework.http.HttpStatus;

public enum ErrorMessageEnum {
    INVALID_ATTACHMENTS_NUMBER(1001, "Invalid number of attachments!", "Invalid number of attachments!", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1002, "User is not found!", "User is not found!", HttpStatus.NOT_FOUND),
    EXPIRED_DATE(1003, "Request can not be submitted due to expiry date!", "Your date is expired!", HttpStatus.FORBIDDEN),

    FAILED_TO_SAVE_USER(1006, "Failed to save User!", "Failed to save User!", HttpStatus.INTERNAL_SERVER_ERROR),
    MISSING_OR_INVALID_CIVIL_ID(1007, "missing or invalid CivilId value!", "missing or invalid CivilId value!", HttpStatus.BAD_REQUEST),

    FAILED_TO_GET_USER(1006, "Failed to get User!", "Failed to get User!", HttpStatus.INTERNAL_SERVER_ERROR);
    private int errorCode;
    private String message;
    private String reason;
    private HttpStatus httpStatus;

    ErrorMessageEnum(int errorCode, String message, String reason, HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.message = message;
        this.reason = reason;
        this.httpStatus = httpStatus;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
