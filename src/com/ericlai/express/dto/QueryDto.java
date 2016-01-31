package com.ericlai.express.dto;

/**
 * Created by Administrator on 2016/1/30.
 */
public class QueryDto {

    private String pacId;

    private String senderName;

    private String sendPhone;

    private String recverName;

    private String recverPhone;

    private String posterName;

    private String posterPhone;

    private String pacStatus;

    public String getPacId() {
        return pacId;
    }

    public void setPacId(String pacId) {
        this.pacId = pacId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSendPhone() {
        return sendPhone;
    }

    public void setSendPhone(String sendPhone) {
        this.sendPhone = sendPhone;
    }

    public String getRecverName() {
        return recverName;
    }

    public void setRecverName(String recverName) {
        this.recverName = recverName;
    }

    public String getRecverPhone() {
        return recverPhone;
    }

    public void setRecverPhone(String recverPhone) {
        this.recverPhone = recverPhone;
    }

    public String getPosterName() {
        return posterName;
    }

    public void setPosterName(String posterName) {
        this.posterName = posterName;
    }

    public String getPosterPhone() {
        return posterPhone;
    }

    public void setPosterPhone(String posterPhone) {
        this.posterPhone = posterPhone;
    }

    public String getPacStatus() {
        return pacStatus;
    }

    public void setPacStatus(String pacStatus) {
        this.pacStatus = pacStatus;
    }
}
