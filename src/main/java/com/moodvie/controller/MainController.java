package com.moodvie.controller;


import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;

public class MainController {
    @FXML
    private StackPane contentArea;

    public void initialize() {
        NavigationController.getInstance().setMainContentArea(contentArea);
        // Chargez la premiere vue
        NavigationController.getInstance().loadLogPageView();
    }
}
