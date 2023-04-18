package com.coco.controller;

import com.coco.CocoPractiseApplication;
import com.coco.common.Encryption;
import com.coco.common.OrderNumber;
import com.coco.common.UserHolder;
import com.coco.config.Config;
import com.coco.pojo.UserPojo;
import com.coco.service.UserService;
import com.coco.util.SpringContextUtil;
import com.coco.view.HistoryView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;


@FXMLController

public class LoginController{

    @FXML
    public TextField usernameTextField;
    @FXML
    public TextField passwordTextField;
    @FXML
    public TextField errorField;
    @FXML
    private PasswordField hiddenPasswordTextField;
    @FXML
    private CheckBox showPassword;

    @FXML
    private TableView<UserPojo> tableView;

    HashMap<String, String> loginInfo = new HashMap<>();

    Encryption encryptor = new Encryption();

    byte[] encryptionKey = {65, 12, 12, 12, 12, 12, 12, 12, 12,
            12, 12, 12, 12, 12, 12, 12 };

    @FXML
    void changeVisibility(ActionEvent event) {
        if (showPassword.isSelected()) {
            passwordTextField.setText(hiddenPasswordTextField.getText());
            passwordTextField.setVisible(true);
            hiddenPasswordTextField.setVisible(false);
            return;
        }
        hiddenPasswordTextField.setText(passwordTextField.getText());
        hiddenPasswordTextField.setVisible(true);
        passwordTextField.setVisible(false);
    }

    @FXML
    void loginHandler(ActionEvent event) throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException{
        String username = usernameTextField.getText();
        String password = getPassword();
        UserService userService = SpringContextUtil.getBean(UserService.class);
        List<UserPojo> userInfo = userService.findOne(username);
        System.out.println(userInfo);
        String encryptedPassword = userInfo.get(0).getPassword();
        int userId = userInfo.get(0).getUserId();
        String userName = userInfo.get(0).getUserName();
        UserHolder userHolder = UserHolder.getInstance();
        userHolder.setUserId(userId);
        userHolder.setUserName(userName);
        userHolder.setEmail(userInfo.get(0).getEmail());
        userHolder.setPhone(userInfo.get(0).getPhone());

        if(password.equals(encryptor.decrypt(encryptedPassword,encryptionKey))){
            Stage stage = CocoPractiseApplication.getStage();
            stage.close();

            Config.loginUser =  userInfo.get(0);
            CocoPractiseApplication.showView(HistoryView.class);

        } else {
            errorField.setVisible(true);
        }
    }

    @FXML
    void createAccount(ActionEvent event) throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        UserService userService = SpringContextUtil.getBean(UserService.class);
        String username = usernameTextField.getText();
        String password = getPassword();
        String encrypPassword = encryptor.encrypt(password,encryptionKey);
//        String email = emailField.getText();
//        String phone = phoneField.getText();
        int userId =OrderNumber.getOrderNumber(9);

        userService.userInsert(userId,username,null,null,encrypPassword);
    }

    @FXML
    void updateAccount(ActionEvent event) {
        UserService userService = SpringContextUtil.getBean(UserService.class);
        String username = usernameTextField.getText();
        String password = getPassword();
//        String email = emailField.getText();
//        String phone = phoneField.getText();
        int userId =OrderNumber.getOrderNumber(9);

        userService.userUpdate(userId,username,null,null,password);
    }


    private String getPassword(){
        if(passwordTextField.isVisible()){
            return passwordTextField.getText();
        } else {
            return hiddenPasswordTextField.getText();
        }
    }


}
