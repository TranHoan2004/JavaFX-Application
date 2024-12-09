package com.he180030.agentmanagement.controller;

import com.he180030.agentmanagement.dto.AccountDTO;
import com.he180030.agentmanagement.service.AccountService;
import com.he180030.agentmanagement.service.impl.AccountServiceImpl;
import com.he180030.agentmanagement.utils.InputValidation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController implements Initializable {
    @FXML
    private Label message;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private Button loginBtn;
    private final AccountService accountService;

    public LoginController() {
        this.accountService = new AccountServiceImpl();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginBtn.setOnAction(event -> {
            try {
                // ensure all the fields are inputted and are in right format
                checkEmptyTextField(email);
                checkEmptyTextField(password);
                InputValidation.isRightEmailFormat(email.getText());

                // verify account
                AccountDTO account = new AccountDTO();

            } catch (Exception e) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, e.getMessage());
                message.setText(e.getMessage());
            }
        });
    }

    private void checkEmptyTextField(TextField textField) throws Exception {
        if (textField.getText() == null || textField.getText().isEmpty()) {
            throw new Exception("Must enter all the fields");
        }
    }
}