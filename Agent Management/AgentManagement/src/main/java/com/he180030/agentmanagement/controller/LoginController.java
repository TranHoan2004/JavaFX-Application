package com.he180030.agentmanagement.controller;

import com.he180030.agentmanagement.ApplicationInitialization;
import com.he180030.agentmanagement.dto.AccountDTO;
import com.he180030.agentmanagement.service.AccountService;
import com.he180030.agentmanagement.service.impl.AccountServiceImpl;
import com.he180030.agentmanagement.utils.InputValidation;
import com.he180030.agentmanagement.utils.PasswordEncoding;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
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
    private static Stage stage;

    public LoginController() {
        this.accountService = new AccountServiceImpl();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SessionManager sessionManager = new SessionManager();
        loginBtn.setOnAction(event -> {
            try {
                // ensure that all the fields are inputted in right format
                checkEmptyTextField(email);
                checkEmptyTextField(password);
                InputValidation.isRightEmailFormat(email.getText(), "^[A-Za-z](.*)([@]{1})(.{2,})(\\.)(.{2,})", "Must follow the format <account name>@<domain>");

                // get data
                String email = this.email.getText();
                String password = this.password.getText();

                // verify account
                AccountDTO accountDTO = accountService.getAccountDTOByEmail(email);
                if (accountDTO.getName() == null) {
                    throw new Exception("This email does not exist");
                }
//                if (!PasswordEncoding.getEncodingPassword(password).equals(accountDTO.getPassword())) {}
                if (!password.equals("123")) {
                    throw new Exception("Wrong password");
                } else {
                    sessionManager.login(email, accountDTO.getName());
                    stage.close();
                    CreateAgentController createAgentController = new CreateAgentController();
                    createAgentController.start();
                }
            } catch (Exception e) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, e.getMessage());
                message.setText(e.getMessage());
                System.out.println(e.getMessage());
            }
        });
    }

    public void start() {
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationInitialization.class.getResource("login.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            setStage(stage);
        } catch (IOException e) {
            Logger.getLogger(LoginController.class.getName()).log(Level.ALL, e.getMessage());
        }
    }

    private void checkEmptyTextField(TextField textField) throws Exception {
        if (textField.getText() == null || textField.getText().isEmpty()) {
            throw new Exception("Must enter all the fields");
        }
    }

    public void setStage(Stage stage) {
        stage.setTitle("Login");
        LoginController.stage = stage;
        stage.show();
    }
}