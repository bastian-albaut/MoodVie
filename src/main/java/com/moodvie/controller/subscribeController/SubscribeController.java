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
    private VBox boxBaseSubscribe;

    @FXML
    private VBox boxMonthlySubscribe;

    @FXML
    private VBox boxAnnualSubscribe;

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
    private VBox boxTitleBaseSubscribe;

    @FXML
    private VBox boxTitleMonthlySubscribe;

    @FXML
    private VBox boxTitleAnnualSubscribe;

    @FXML
    private VBox boxFeaturesBaseSubscribe;

    @FXML
    private VBox boxFeaturesMonthlySubscribe;

    @FXML
    private VBox boxFeaturesAnnualSubscribe;

    @FXML
    private VBox boxActionBaseSubscribe;

    @FXML
    private VBox boxActionMonthlySubscribe;

    @FXML
    private VBox boxActionAnnualSubscribe;

    @FXML
    public void initialize() {
        hBox.setFillHeight(false);

        // Adjust the size of the base subscribe box and its children
        boxBaseSubscribe.prefWidthProperty().bind(hBox.widthProperty().multiply(0.25));
        boxBaseSubscribe.prefHeightProperty().bind(hBox.heightProperty().multiply(0.9));
        boxTitleBaseSubscribe.prefHeightProperty().bind(boxBaseSubscribe.heightProperty().multiply(0.25));
        boxFeaturesBaseSubscribe.prefHeightProperty().bind(boxBaseSubscribe.heightProperty().multiply(0.5));
        boxActionBaseSubscribe.prefHeightProperty().bind(boxBaseSubscribe.heightProperty().multiply(0.25));

        // Adjust the size of the monthly subscribe box and its children
        boxMonthlySubscribe.prefWidthProperty().bind(hBox.widthProperty().multiply(0.25));
        boxMonthlySubscribe.prefHeightProperty().bind(hBox.heightProperty().multiply(0.9));
        boxTitleMonthlySubscribe.prefHeightProperty().bind(boxMonthlySubscribe.heightProperty().multiply(0.25));
        boxFeaturesMonthlySubscribe.prefHeightProperty().bind(boxMonthlySubscribe.heightProperty().multiply(0.5));
        boxActionMonthlySubscribe.prefHeightProperty().bind(boxMonthlySubscribe.heightProperty().multiply(0.25));

        // Adjust the size of the annual subscribe box and its children
        boxAnnualSubscribe.prefWidthProperty().bind(hBox.widthProperty().multiply(0.25));
        boxAnnualSubscribe.prefHeightProperty().bind(hBox.heightProperty().multiply(0.9));
        boxTitleAnnualSubscribe.prefHeightProperty().bind(boxAnnualSubscribe.heightProperty().multiply(0.25));
        boxFeaturesAnnualSubscribe.prefHeightProperty().bind(boxAnnualSubscribe.heightProperty().multiply(0.5));
        boxActionAnnualSubscribe.prefHeightProperty().bind(boxAnnualSubscribe.heightProperty().multiply(0.25));


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
