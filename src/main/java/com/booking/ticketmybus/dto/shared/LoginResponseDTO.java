package com.booking.ticketmybus.dto.shared;

public class LoginResponseDTO {
    private  String name;
    private String email;
    private String errorMsg;
    private String access_token;
    private boolean success;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public void setSuccess(boolean b) {
      this.success = b;
    }

    public boolean isSuccess() {
        return success;
    }
}
