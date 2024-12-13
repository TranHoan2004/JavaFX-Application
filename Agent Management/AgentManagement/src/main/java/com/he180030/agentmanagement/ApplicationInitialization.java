package com.he180030.agentmanagement;

import com.he180030.agentmanagement.controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ApplicationInitialization extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        LoginController loginController = new LoginController();
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationInitialization.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Login");
        stage.setScene(scene);
        loginController.setStage(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}