package com.he180030.agentmanagement.controller;

import com.he180030.agentmanagement.ApplicationInitialization;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdateAgentController {
    private static Stage stage;

    public void start() {
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationInitialization.class.getResource("/resources/com/he180030/agentmanagement/update-agent.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Update Agent");
            setStage(stage);
            stage.show();
        } catch (IOException e) {
            Logger.getLogger(UpdateAgentController.class.getName()).log(Level.ALL, null, e);
        }
    }

    private void setStage(Stage stage) {
        UpdateAgentController.stage = stage;
    }
}
