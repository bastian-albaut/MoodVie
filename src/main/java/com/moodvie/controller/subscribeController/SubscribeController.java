package com.moodvie.controller.subscribeController;

import java.util.ArrayList;

import com.moodvie.business.facade.SubscribeFacade;
import com.moodvie.business.facade.TypeSubscribeFacade;
import com.moodvie.persistance.model.Subscribe;
import com.moodvie.persistance.model.TypeOfSubscribe;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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

        // Set up the layout of the subscribe box
        setupSubscribeBox(boxBaseSubscribe, boxTitleBaseSubscribe, boxFeaturesBaseSubscribe, boxActionBaseSubscribe);
        setupSubscribeBox(boxMonthlySubscribe, boxTitleMonthlySubscribe, boxFeaturesMonthlySubscribe, boxActionMonthlySubscribe);
        setupSubscribeBox(boxAnnualSubscribe, boxTitleAnnualSubscribe, boxFeaturesAnnualSubscribe, boxActionAnnualSubscribe);
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
                    displayAction(typeOfSubscribe, boxActionBaseSubscribe);
                    break;
                case "Mensuel":
                    titleTypeOfSubscribeMonthly.setText("Abonnement " + label);
                    priceTypeOfSubscribeMonthly.setText(typeOfSubscribe.getPrice() + "€" + "/mois");
                    displayFeatures(typeOfSubscribe.getFeatures(), boxFeaturesMonthlySubscribe);
                    displayAction(typeOfSubscribe, boxActionMonthlySubscribe);
                    break;
                case "Annuel":
                    titleTypeOfSubscribeAnnual.setText("Abonnement " + label);
                    priceTypeOfSubscribeAnnual.setText(typeOfSubscribe.getPrice() + "€" + "/an");
                    displayFeatures(typeOfSubscribe.getFeatures(), boxFeaturesAnnualSubscribe);
                    displayAction(typeOfSubscribe, boxActionAnnualSubscribe);
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

    // Display action in the specified actionBox
    private void displayAction(TypeOfSubscribe typeOfSubscribe, VBox actionBox) {
        // Clear existing content
        actionBox.getChildren().clear(); 

        // Get the label of the current subscribe
        String currentLabel = getLabelCurrentSubscribe();

        // Get the label of the type of subscribe
        String label = typeOfSubscribe.getLabel();

        // Get the id of the type of subscribe
        int typeOfSubscribeId = typeOfSubscribe.getId();

        // If the current subscribe is the same as the type of subscribe, display a message
        if (currentLabel.equals(label)) {
            Label actionLabel = new Label("Déjà possédé");
            actionBox.getChildren().add(actionLabel);
        } else {
            Button actionButton = new Button("Souscrire");
            actionButton.setOnAction(event -> {
                handleChangeTypeOfSubscribe(typeOfSubscribeId);
            });
            actionBox.getChildren().add(actionButton);
        }
    }

    // Get the label of the current subscribe of the user
    public String getLabelCurrentSubscribe() {
        // Get the current subscribe
        Subscribe subscribe = subscribeFacade.getSubscribe();

        // If the current subscribe is null, return null
        if (subscribe == null) {
            System.out.println("Subscribe is null");
            return null;
        }

        // Get the type of subscribe
        TypeOfSubscribe typeOfSubscribe = subscribeFacade.getTypeOfSubscribe();

        // If the type of subscribe is null, return null
        if (typeOfSubscribe == null) {
            System.out.println("Type of subscribe is null");
            return null;
        }

        // Get the label of the type of subscribe
        String label = typeOfSubscribe.getLabel();

        return label;
    }

    // Set up the layout of the subscribe box
    private void setupSubscribeBox(VBox box, VBox titleBox, VBox featuresBox, VBox actionBox) {
        box.prefWidthProperty().bind(hBox.widthProperty().multiply(0.25));
        box.prefHeightProperty().bind(hBox.heightProperty().multiply(0.9));
        titleBox.prefHeightProperty().bind(box.heightProperty().multiply(0.25));
        featuresBox.prefHeightProperty().bind(box.heightProperty().multiply(0.5));
        actionBox.prefHeightProperty().bind(box.heightProperty().multiply(0.25));
    }

    // Handle the change of the type of subscribe
    private void handleChangeTypeOfSubscribe(int typeOfSubscribeId) {
        // Change the type of subscribe
        boolean isSubscribeChange = subscribeFacade.changeSubscribe(typeOfSubscribeId);

        // If the type of subscribe has been changed, display a message
        if (isSubscribeChange) {
            
            // Refresh the page
            initialize();
            
            // Afficher un message de succès
            String label = getLabelCurrentSubscribe();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Changement d'abonnement");
            alert.setHeaderText(null);
            alert.setContentText("Vous avez maintenant l'abonnement " + label + " !");
            alert.showAndWait();

        } else {
            // Afficher un message d'erreur
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Changement d'abonnement");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Une erreur est survenue lors du changement d'abonnement !");
            errorAlert.showAndWait();
        }
    }
}