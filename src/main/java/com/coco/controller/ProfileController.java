package com.coco.controller;

import com.coco.CocoPractiseApplication;
import com.coco.common.UserHolder;
import com.coco.pojo.UserPojo;
import com.coco.service.UserService;
import com.coco.util.SpringContextUtil;
import com.coco.view.HistoryView;
import com.coco.view.LoginView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@FXMLController
public class ProfileController implements Initializable{


    @FXML
    public Button updateButton;


    @FXML
    public TextField emailField;

    @FXML
    public TextField phoneField;
    public TextField phoneFiled;
    public Button updateButton2;
    public TextField emailFiled;
    public Button updateButton1;
    @FXML
    public Button home;

    @FXML
    void onLogout(ActionEvent event) {
        Stage stage = CocoPractiseApplication.getStage();
        stage.close();
        CocoPractiseApplication.showView(LoginView.class);

    }
    @FXML
    void onHome(ActionEvent event) {
        Stage stage = CocoPractiseApplication.getStage();
        stage.close();
        CocoPractiseApplication.showView(HistoryView.class);

    }


    @FXML
    void onUpdate(ActionEvent event) {
        UserService userService = SpringContextUtil.getBean(UserService.class);
        UserHolder userHolder = UserHolder.getInstance();
        int userId =userHolder.getUserId();
        String userName = userHolder.getUserName();
        List<UserPojo> userInfo = userService.findOne(userName);
        String encryptedPassword = userInfo.get(0).getPassword();
        String email = emailField.getText();
        String phone = phoneField.getText();

        userService.userUpdate(userId,userName,email,phone,encryptedPassword);

        reFreshTable();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){

        UserHolder holder = UserHolder.getInstance();
        String userName = holder.getUserName();
        String email = holder.getEmail();
        String phone = holder.getPhone();
        emailField.setText(email);
        phoneField.setText(phone);

        reFreshTable();
    }

    public void reFreshTable(){
        UserService userService = SpringContextUtil.getBean(UserService.class);
        UserHolder userHolder = UserHolder.getInstance();
        int userId =userHolder.getUserId();
        String userName = userHolder.getUserName();
        List<UserPojo> userInfo = userService.findOne(userName);
        String encryptedPassword = userInfo.get(0).getPassword();
        String email = emailField.getText();
        String phone = phoneField.getText();
        emailField.setText(email);
        phoneField.setText(phone);
    }
}
