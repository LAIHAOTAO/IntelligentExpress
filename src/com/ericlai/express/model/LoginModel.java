package com.ericlai.express.model;

/**
 * Created by ERIC_LAI on 15/11/27.
 */
public class LoginModel {

    private String userName;

    private String password;

    private String role;

    private String result;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
