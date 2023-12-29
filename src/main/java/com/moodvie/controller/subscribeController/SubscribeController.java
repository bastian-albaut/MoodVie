package com.moodvie.controller.subscribeController;

import java.util.ArrayList;

import com.moodvie.business.facade.SubscribeFacade;
import com.moodvie.business.facade.TypeSubscribeFacade;
import com.moodvie.persistance.model.Subscribe;
import com.moodvie.persistance.model.TypeOfSubscribe;


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
    private Label titleTypeOfSubscribeBase, titleTypeOfSubscribeMonthly, titleTypeOfSubscribeAnnual;

    @FXML
    private Label priceTypeOfSubscribeBase, priceTypeOfSubscribeMonthly, priceTypeOfSubscribeAnnual;

    @FXML
    private Button button1, button2;

    @FXML
    private VBox boxTitleBaseSubscribe, boxTitleMonthlySubscribe, boxTitleAnnualSubscribe;

    @FXML
    private VBox boxFeaturesBaseSubscribe, boxFeaturesMonthlySubscribe, boxFeaturesAnnualSubscribe;

    @FXML
    private VBox boxActionBaseSubscribe, boxActionMonthlySubscribe, boxActionAnnualSubscribe;

    // Inject the subscribe facade and the type of subscribe facade
    private final SubscribeFacade subscribeFacade = SubscribeFacade.getInstance();
    private final TypeSubscribeFacade typeSubscribeFacade = TypeSubscribeFacade.getInstance();

    @FXML
    public void initialize() {
        hBox.setFillHeight(false);

        // Get informations from facades
        handleTypeSubscribeFacade();

        setupSubscribeBox(boxBaseSubscribe, boxTitleBaseSubscribe, boxFeaturesBaseSubscribe, boxActionBaseSubscribe);
        setupSubscribeBox(boxMonthlySubscribe, boxTitleMonthlySubscribe, boxFeaturesMonthlySubscribe, boxActionMonthlySubscribe);
        setupSubscribeBox(boxAnnualSubscribe, boxTitleAnnualSubscribe, boxFeaturesAnnualSubscribe, boxActionAnnualSubscribe);

        setupButtonEventHandler(button1, "Button 1 clicked!");
        setupButtonEventHandler(button2, "Button 2 clicked!");
    }

    // Get all the types of subscribe
    public void handleTypeSubscribeFacade() {

        // Get all the types of subscribe
        ArrayList<TypeOfSubscribe> typeOfSubscribeList = typeSubscribeFacade.getListTypeOfSubscribe();

        for(TypeOfSubscribe typeOfSubscribe : typeOfSubscribeList) {
            String label = typeOfSubscribe.getLabel();
            switch (label) {
                case "Basique":
                    titleTypeOfSubscribeBase.setText("Abonnement " + label);
                    priceTypeOfSubscribeBase.setText("Gratuit");
                    displayFeatures(typeOfSubscribe.getFeatures(), boxFeaturesBaseSubscribe);
                    break;
                case "Mensuel":
                    titleTypeOfSubscribeMonthly.setText("Abonnement " + label);
                    priceTypeOfSubscribeMonthly.setText(typeOfSubscribe.getPrice() + "€" + "/mois");
                    displayFeatures(typeOfSubscribe.getFeatures(), boxFeaturesMonthlySubscribe);
                    break;
                case "Annuel":
                    titleTypeOfSubscribeAnnual.setText("Abonnement " + label);
                    priceTypeOfSubscribeAnnual.setText(typeOfSubscribe.getPrice() + "€" + "/an");
                    displayFeatures(typeOfSubscribe.getFeatures(), boxFeaturesAnnualSubscribe);
                    break;
            }
        }
    }

    // Display features in the specified featuresBox
    private void displayFeatures(ArrayList<String> features, VBox featuresBox) {
        // Clear existing content
        featuresBox.getChildren().clear(); 

        // Add labels to the VBox
        for (String feature : features) {
            Label featureLabel = new Label(feature);
            featuresBox.getChildren().add(featureLabel);
        }
    }

    // Set up the layout of the subscribe box
    private void setupSubscribeBox(VBox box, VBox titleBox, VBox featuresBox, VBox actionBox) {
        box.prefWidthProperty().bind(hBox.widthProperty().multiply(0.25));
        box.prefHeightProperty().bind(hBox.heightProperty().multiply(0.9));
        titleBox.prefHeightProperty().bind(box.heightProperty().multiply(0.25));
        featuresBox.prefHeightProperty().bind(box.heightProperty().multiply(0.5));
        actionBox.prefHeightProperty().bind(box.heightProperty().multiply(0.25));
    }

    // Set up the event handler for the button
    private void setupButtonEventHandler(Button button, String message) {
        button.setOnAction(event -> handleButtonClick(message));
    }

    // Handle the button click event
    private void handleButtonClick(String message) {
        System.out.println(message);
    }
}