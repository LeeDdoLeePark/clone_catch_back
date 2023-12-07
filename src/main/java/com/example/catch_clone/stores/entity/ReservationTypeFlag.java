package com.example.catch_clone.stores.entity;

public enum ReservationTypeFlag {
    ONE_MONTH("One Month"),
    TWO_MONTH("Two Month"),
    SPECIFIC_DATE("Specific Date");

    private final String status;

    ReservationTypeFlag(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
