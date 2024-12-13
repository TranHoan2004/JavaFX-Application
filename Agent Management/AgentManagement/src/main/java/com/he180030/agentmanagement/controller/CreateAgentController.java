package com.he180030.agentmanagement.controller;

import com.he180030.agentmanagement.ApplicationInitialization;
import com.he180030.agentmanagement.dto.AgentDTO;
import com.he180030.agentmanagement.model.Status;
import com.he180030.agentmanagement.service.AgentService;
import com.he180030.agentmanagement.service.impl.AgentServiceImpl;
import com.he180030.agentmanagement.utils.DateValidation;
import com.he180030.agentmanagement.utils.InputValidation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateAgentController implements Initializable {
    private static Stage stage;
    @FXML
    public Button saveBtn;
    @FXML
    private TextField name;
    @FXML
    private TextField email;
    @FXML
    private TextField address;
    @FXML
    private TextField balance;
    @FXML
    private TextField enrolledDate;
    @FXML
    private Label message;
    @FXML
    private ComboBox<String> status;
    @FXML
    private Label saluteMessage;
    @FXML
    private Button logoutBtn;
    private final AgentService agentService;

    public CreateAgentController() {
        this.agentService = new AgentServiceImpl();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SessionManager sessionManager = new SessionManager();
        String username = sessionManager.getUsername();
        saluteMessage.setText("Hello <" + username + ">");

        // log out button
        logoutBtn.setOnMouseClicked(_ -> {
            sessionManager.logout();
            stage.close();
            LoginController loginController = new LoginController();
            loginController.start();
        });

        // status on action

        // add agent module
        saveBtn.setOnMouseClicked(_ -> createNewAgent());

        // create combo box data
        setUpComboBox();
    }

    private void createNewAgent() {
        // get data
        String name = this.name.getText();
        String email = this.email.getText();
        String address = this.address.getText();
        String balanceRaw = this.balance.getText();
        String enrolledDateRaw = this.enrolledDate.getText();
        String status = this.status.getValue();

        try {
            // check type of data
            InputValidation.isRightEmailFormat(name, "[A-Za-z]+", "Name does not contain digits or special characters");
            InputValidation.isRightEmailFormat(email, "^[A-Za-z](.*)([@]{1})(.{2,})(\\.)(.{2,})", "Must follow the format <account name>@<domain>");
            InputValidation.isRightEmailFormat(balanceRaw, "[0-9]*\\.?[0-9]+", "Must be a number");

            // get data
            float balance = Float.parseFloat(balanceRaw);
            Date enrolledDate = DateValidation.convertToDate(enrolledDateRaw);


            // verify email
            AgentDTO agent = agentService.getAgentByEmail(email);
            if (agent.getName() != null) {
                throw new Exception("This email already exists");
            }
            agent = AgentDTO.builder()
                    .accountBalance(balance)
                    .address(address)
                    .name(name)
                    .status(status.equals("NEW") ? Status.NEW : Status.NORMAL)
                    .email(email)
                    .enrolledDate(enrolledDate)
                    .build();
            agentService.addAgent(agent);
        } catch (Exception e) {
            Logger.getLogger(CreateAgentController.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            message.setText(e.getMessage());
        }
    }

    private void setUpComboBox() {
        status = new ComboBox<>();
        status.setPromptText("select an option");
        status.getItems().addAll("NEW", "NORMAL");
    }

    public void start() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ApplicationInitialization.class.getResource("create-agent.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            setStage(stage);
            stage.setTitle("Create Agent");
            stage.show();
        } catch (IOException e) {
            Logger.getLogger(CreateAgentController.class.getName()).log(Level.ALL, null, e);
        }
    }

    public void setStage(Stage stage) {
        CreateAgentController.stage = stage;
    }

}
