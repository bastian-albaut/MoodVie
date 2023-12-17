package com.moodvie.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class MainController {
    @FXML
    private StackPane contentArea;

    public void initialize() {
        loadLoginView();
    }

    public void loadLoginView() {
        loadView("/app/login-view.fxml");
    }

    public void loadRegisterView() {
        loadView("/app/register-view.fxml");
    }

    private void loadView(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent view = loader.load();
            AbstractController controller = loader.getController();
            controller.setMainController(this);
            contentArea.getChildren().setAll(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
