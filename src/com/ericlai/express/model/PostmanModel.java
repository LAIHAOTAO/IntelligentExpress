package com.ericlai.express.model;

/**
 * Created by ERIC_LAI on 15/11/27.
 */
public class PostmanModel {

    /**
     * 快递员id
     */
    private String id;

    /**
     * 快递员名字
     */
    private String name;

    /**
     * 快递员登陆名
     */
    private String userName;

    /**
     * 快递员性别
     */
    private String gender;

    /**
     * 快递员电话
     */
    private String phone;

    /**
     * 快递员携带的包裹
     */
    private String carry;

    /**
     * 快递员登入系统口令
     */
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCarry() {
        return carry;
    }

    public void setCarry(String carry) {
        this.carry = carry;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
