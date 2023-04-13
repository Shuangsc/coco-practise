package com.coco.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import javafx.beans.property.*;
import lombok.Data;

@TableName("user_info")
@Data
public class UserPojo {

    @TableField(exist = false)
    private IntegerProperty userIdProperty = new SimpleIntegerProperty();
    @TableField(exist = false)
    private StringProperty userNameProperty = new SimpleStringProperty();
    @TableField(exist = false)
    private StringProperty emailProperty = new SimpleStringProperty();
    @TableField(exist = false)
    private StringProperty phoneProperty = new SimpleStringProperty();
    @TableField(exist = false)
    private StringProperty passwordProperty = new SimpleStringProperty();

    @TableId(value = "userId",type= IdType.ASSIGN_ID)

    private int userId;
    @TableField(value = "userName")
    private String userName;

    @TableField(value = "email")
    private String email;

    @TableField(value = "phone")
    private String phone;
    @TableField(value = "password")
    private String password;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserPojo(int userId, String userName,String email, String phone, String password){

        setUserId(userId);
        setUserName(userName);
        setEmail(email);
        setPhone(phone);
        setPassword(password);

        userIdProperty.set(userId);
        userNameProperty.set(userName);
        emailProperty.set(email);
        phoneProperty.set(phone);
        passwordProperty.set(password);
    }

}
