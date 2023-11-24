package com.example.catch_clone.reservation.entity;

public enum ReservationStatus {
    IN_PROGRESS("In Progress"),
    NO_SHOW("No Show"),
    CANCELED("Canceled"),
    COMPLETED("Completed");

    private final String status;

    ReservationStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
