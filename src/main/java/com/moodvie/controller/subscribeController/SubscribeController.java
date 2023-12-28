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
    private VBox boxBaseSubscribe, boxMonthlySubscribe, boxAnnualSubscribe;

    @FXML
    private Label text1, text2, text3;

    @FXML
    private Button button1, button2;

    @FXML
    private VBox boxTitleBaseSubscribe, boxTitleMonthlySubscribe, boxTitleAnnualSubscribe;

    @FXML
    private VBox boxFeaturesBaseSubscribe, boxFeaturesMonthlySubscribe, boxFeaturesAnnualSubscribe;

    @FXML
    private VBox boxActionBaseSubscribe, boxActionMonthlySubscribe, boxActionAnnualSubscribe;

    @FXML
    public void initialize() {
        hBox.setFillHeight(false);

        setupSubscribeBox(boxBaseSubscribe, boxTitleBaseSubscribe, boxFeaturesBaseSubscribe, boxActionBaseSubscribe);
        setupSubscribeBox(boxMonthlySubscribe, boxTitleMonthlySubscribe, boxFeaturesMonthlySubscribe, boxActionMonthlySubscribe);
        setupSubscribeBox(boxAnnualSubscribe, boxTitleAnnualSubscribe, boxFeaturesAnnualSubscribe, boxActionAnnualSubscribe);

        setupButtonEventHandler(button1, "Button 1 clicked!");
        setupButtonEventHandler(button2, "Button 2 clicked!");
    }

    private void setupSubscribeBox(VBox box, VBox titleBox, VBox featuresBox, VBox actionBox) {
        box.prefWidthProperty().bind(hBox.widthProperty().multiply(0.25));
        box.prefHeightProperty().bind(hBox.heightProperty().multiply(0.9));
        titleBox.prefHeightProperty().bind(box.heightProperty().multiply(0.25));
        featuresBox.prefHeightProperty().bind(box.heightProperty().multiply(0.5));
        actionBox.prefHeightProperty().bind(box.heightProperty().multiply(0.25));
    }

    private void setupButtonEventHandler(Button button, String message) {
        button.setOnAction(event -> handleButtonClick(message));
    }

    private void handleButtonClick(String message) {
        System.out.println(message);
    }
}