package com.ericlai.express.model;

/**
 * Created by ERIC_LAI on 2015/12/9.
 */
public class QueryModel {

    /**
     * 快递号
     */
    private String packageNo;

    /**
     * 寄件人手机号
     */
    private String sendPhone;

    /**
     * 收件人手机号
     */
    private String receivePhone;

    public String getPackageNo() {
        return packageNo;
    }

    public void setPackageNo(String packageNo) {
        this.packageNo = packageNo;
    }

    public String getSendPhone() {
        return sendPhone;
    }

    public void setSendPhone(String sendPhone) {
        this.sendPhone = sendPhone;
    }

    public String getReceivePhone() {
        return receivePhone;
    }

    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone;
    }
}
