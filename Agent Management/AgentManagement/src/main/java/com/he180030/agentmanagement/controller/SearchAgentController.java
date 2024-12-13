package com.he180030.agentmanagement.controller;

import com.he180030.agentmanagement.ApplicationInitialization;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SearchAgentController {
    private static Stage stage;

    public void start() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ApplicationInitialization.class.getResource("search-agent.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            setStage(stage);
            stage.setTitle("Search Agent");
            stage.show();
        } catch (IOException e) {
            Logger.getLogger(SearchAgentController.class.getName()).log(Level.ALL, null, e);
        }
    }

    public void setStage(Stage stage) {
        SearchAgentController.stage = stage;
    }
}
