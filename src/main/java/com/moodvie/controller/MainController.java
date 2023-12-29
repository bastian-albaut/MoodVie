package com.moodvie.controller;


import java.util.ArrayList;

import com.moodvie.business.facade.TypeSubscribeFacade;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;

public class MainController {
    @FXML
    private StackPane contentArea;

    public void initialize() {
        // Create TypeOfSubscribe instances
        createTypeOfSubscribeInstances();
        
        NavigationController.getInstance().setMainContentArea(contentArea);
        // Chargez la premiere vue
        NavigationController.getInstance().loadLoginView();
    }

    // Create all types of subscribe 
    private void createTypeOfSubscribeInstances() {
        TypeSubscribeFacade typeSubscribeFacade = TypeSubscribeFacade.getInstance();

        ArrayList<String> featuresBaseSubscribe = new ArrayList<>();
        featuresBaseSubscribe.add("Fonctionnalité 1");
        featuresBaseSubscribe.add("Fonctionnalité 2");
        featuresBaseSubscribe.add("Fonctionnalité 3");

        ArrayList<String> featuresMonthlySubscribe = new ArrayList<>();
        featuresMonthlySubscribe.add("Fonctionnalité 1");
        featuresMonthlySubscribe.add("Fonctionnalité 2");
        featuresMonthlySubscribe.add("Fonctionnalité 3");

        ArrayList<String> featuresAnnualSubscribe = new ArrayList<>();
        featuresAnnualSubscribe.add("Fonctionnalité 1");
        featuresAnnualSubscribe.add("Fonctionnalité 2");
        featuresAnnualSubscribe.add("Fonctionnalité 3");

        typeSubscribeFacade.createTypeOfSubscribe("Basique", 0, Integer.MAX_VALUE, featuresBaseSubscribe);
        typeSubscribeFacade.createTypeOfSubscribe("Mensuel", 4.99, 30, featuresMonthlySubscribe);
        typeSubscribeFacade.createTypeOfSubscribe("Annuel", 29.99, 365, featuresAnnualSubscribe);
    }
}
