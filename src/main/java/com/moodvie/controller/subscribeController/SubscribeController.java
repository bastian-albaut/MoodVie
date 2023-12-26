package com.moodvie.controller.subscribeController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SubscribeController {

    @FXML
    private HBox hBox;

    @FXML
    private VBox box1;

    @FXML
    private VBox box2;

    @FXML
    private VBox box3;

    @FXML
    private Label text1;

    @FXML
    private Label text2;

    @FXML
    private Label text3;

    @FXML
    private Button button1;
    
    @FXML
    private Button button2;

    @FXML
    public void initialize() {
        box1.prefWidthProperty().bind(hBox.widthProperty().multiply(0.2));
        box1.prefHeightProperty().bind(hBox.heightProperty().multiply(0.5));

        box2.prefWidthProperty().bind(hBox.widthProperty().multiply(0.2));
        box2.prefHeightProperty().bind(hBox.heightProperty().multiply(0.5));

        box3.prefWidthProperty().bind(hBox.widthProperty().multiply(0.2));
        box3.prefHeightProperty().bind(hBox.heightProperty().multiply(0.5));

        button1.setOnAction(event -> handleButton1());
        button2.setOnAction(event -> handleButton2());
    }

    // Event handler for Button1
    private void handleButton1() {
        System.out.println("Button 1 clicked!");
    }

    // Event handler for Button2
    private void handleButton2() {
        System.out.println("Button 2 clicked!");
    }
}
