package com.coco.controller;

import com.coco.CocoPractiseApplication;
import com.coco.common.Encryption;
import com.coco.common.OrderNumber;
import com.coco.service.UserService;
import com.coco.util.SpringContextUtil;
import com.coco.view.LoginView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@FXMLController
public class SignUpController {

    @FXML
    public TextField usernameTextField;
    @FXML
    public TextField passwordTextField;

    @FXML
    public TextField emailTextField;
    @FXML
    public TextField phoneTextField;

    @FXML
    private PasswordField hiddenPasswordTextField;
    @FXML
    private CheckBox showPassword;

    Encryption encryptor = new Encryption();

    byte[] encryptionKey = {65, 12, 12, 12, 12, 12, 12, 12, 12,
            12, 12, 12, 12, 12, 12, 12 };

    @FXML
    void createAccount(ActionEvent event) throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        UserService userService = SpringContextUtil.getBean(UserService.class);
        String username = usernameTextField.getText();
        String password = getPassword();
        String encrypPassword = encryptor.encrypt(password,encryptionKey);
        String email = emailTextField.getText();
        String phone = phoneTextField.getText();
        int userId = OrderNumber.getOrderNumber(9);

        userService.userInsert(userId,username,email,phone,encrypPassword);

        Stage stage = CocoPractiseApplication.getStage();
        stage.close();
        CocoPractiseApplication.showView(LoginView.class);
    }

    private String getPassword(){
        if(passwordTextField.isVisible()){
            return passwordTextField.getText();
        } else {
            return hiddenPasswordTextField.getText();
        }
    }

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
}
