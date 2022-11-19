package com.mycompany.user.system.model;

import java.util.Arrays;

public enum RequestStatus {
    PENDING("Pending"), ACCEPTED("ACCEPTED"), REJECTED("REJECTED");
    private String value;

    RequestStatus(String value) {
        this.value = value;
    }

    public String getCategory() {
        return value;
    }

    public static RequestStatus getRequestStatus(String value) {
        return Arrays.stream(values())
                .filter(val -> val.getCategory().equalsIgnoreCase(value))
                .findFirst()
                .orElse(null);
    }
}