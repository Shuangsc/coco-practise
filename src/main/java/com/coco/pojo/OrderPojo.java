package com.coco.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import javafx.beans.property.*;
import lombok.Data;

import java.util.Date;

@TableName("order_info")
@Data
public class OrderPojo {

    @TableField(exist = false)
    private IntegerProperty orderIdProperty = new SimpleIntegerProperty();
    @TableField(exist = false)
    private IntegerProperty userIdProperty = new SimpleIntegerProperty();
    @TableField(exist = false)
    private StringProperty typeProperty = new SimpleStringProperty();
    @TableField(exist = false)
    private DoubleProperty amountProperty = new SimpleDoubleProperty();
    @TableField(exist = false)
    private StringProperty dateProperty = new SimpleStringProperty();
    @TableField(exist = false)
    private StringProperty descriptionProperty = new SimpleStringProperty();


   // @TableId(value = "orderId",type= IdType.ASSIGN_ID)
    @TableId(value = "orderId",type= IdType.ASSIGN_ID)

    private int orderId;
    @TableField(value = "userId")
    private int userId;

    @TableField(value = "type")
    private String type;

    @TableField(value = "amount")
    private double amount;
    @TableField(value = "date")
    private String date;
    @TableField(value = "description")
    private String description;

    public int getOrderIdProperty() {
        return orderIdProperty.get();
    }

    public IntegerProperty orderIdPropertyProperty() {
        return orderIdProperty;
    }

    public void setOrderIdProperty(int orderIdProperty) {
        this.orderIdProperty.set(orderIdProperty);
    }

    public int getUserIdProperty() {
        return userIdProperty.get();
    }

    public IntegerProperty userNamePropertyProperty() {
        return userIdProperty;
    }

    public void setUserIdProperty(int userIdProperty) {
        this.userIdProperty.set(userIdProperty);
    }

    public String getTypeProperty() {
        return typeProperty.get();
    }

    public StringProperty typePropertyProperty() {
        return typeProperty;
    }

    public void setTypeProperty(String typeProperty) {
        this.typeProperty.set(typeProperty);
    }

    public double getAmountProperty() {
        return amountProperty.get();
    }

    public DoubleProperty amountPropertyProperty() {
        return amountProperty;
    }

    public void setAmountProperty(double amountProperty) {
        this.amountProperty.set(amountProperty);
    }

    public String getDateProperty() {
        return dateProperty.get();
    }

    public StringProperty datePropertyProperty() {
        return dateProperty;
    }

    public void setDateProperty(String dateProperty) {
        this.dateProperty.set(dateProperty);
    }

    public String getDescriptionProperty() {
        return descriptionProperty.get();
    }

    public StringProperty descriptionPropertyProperty() {
        return descriptionProperty;
    }

    public void setDescriptionProperty(String descriptionProperty) {
        this.descriptionProperty.set(descriptionProperty);
    }
    
    public void setOrderId(int orderId) {
    	this.orderId = orderId;
    }
    
    public void setUserId(int userId) {
    	this.userId = userId;
    }
    
    public void setType(String type) {
    	this.type = type;
    }
    
    public void setAmount(double amount) {
    	this.amount = amount;
    }
    
    public void setDate(String date) {
    	this.date = date;
    }
    
    public void setDescription(String description) {
    	this.description = description;
    }

    public OrderPojo(int orderId, int userId, String type, double amount, String date, String description){

        setOrderId(orderId);
        setUserId(userId);
        setType(type);
        setAmount(amount);
        setDate(date);
        setDescription(description);
        orderIdProperty.set(orderId);
        userIdProperty.set(userId);
        typeProperty.set(type);
        amountProperty.set(amount);
        dateProperty.set(date);
        descriptionProperty.set(description);
    }
}
