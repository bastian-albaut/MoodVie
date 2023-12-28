package com.moodvie.controller;


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

        typeSubscribeFacade.createTypeOfSubscribe("Basique", 0, Integer.MAX_VALUE);
        typeSubscribeFacade.createTypeOfSubscribe("Mensuel", 4.99, 30);
        typeSubscribeFacade.createTypeOfSubscribe("Annuel", 29.99, 365);
    }
}
