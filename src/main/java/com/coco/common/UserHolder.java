package com.coco.common;

public final class UserHolder {

    private int userId;
    private String userName;

    private String email;
    private String phone;


    private final static UserHolder INSTANCE = new UserHolder();

    private UserHolder() {}

    public static UserHolder getInstance() {
        return INSTANCE;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone(){
        return this.phone;
    }

}
