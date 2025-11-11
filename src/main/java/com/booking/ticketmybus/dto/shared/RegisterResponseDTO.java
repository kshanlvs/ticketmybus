package com.booking.ticketmybus.dto.shared;

public class RegisterResponseDTO {
    private String name;
    private String email;
    private String message;
    private boolean success;

    // Constructors
    public RegisterResponseDTO() {}

    public RegisterResponseDTO(String name, String email, String message, boolean success) {
        this.name = name;
        this.email = email;
        this.message = message;
        this.success = success;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}