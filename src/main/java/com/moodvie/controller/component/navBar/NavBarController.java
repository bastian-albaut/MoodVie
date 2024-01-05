package com.moodvie.controller.component.navBar;

import com.moodvie.business.facade.UserFacade;
import javafx.beans.InvalidationListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class NavBarController implements InvalidationListener {

    @FXML
    private TabController tabController;

    // This method is called after all @FXML annotated members have been injected
    @FXML
    private HBox navBarContainer; // Assurez-vous que votre HBox dans le FXML a un fx:id="navBarContainer"

    private boolean isUserLoggedIn = false; // État de connexion de l'utilisateur

    public void initialize() {
        UserFacade.getInstance().addListener(this);
        updateTabsBasedOnLoginStatus();
    }


    private void updateTabsBasedOnLoginStatus() {
        navBarContainer.getChildren().clear(); // Nettoyer les onglets existants
        addTab("Accueil", "loadHomeView");
        addTab("Rechercher", "loadFilmSearchView");

        if (isUserLoggedIn) {
            addTab("Profil", "loadProfilView");
            addTab("Abonnement", "loadSubscribeView");
            addTab("Mes films", "loadWatchLaterView");
            addTab("Log", "loadLogView");
            addTab("Déconnexion", "logout");
        } else {
            addTab("Connexion", "loadLoginView");
        }

    }


    private void addTab(String buttonText, String actionCommand) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/component/navBar/tab.fxml"));
            HBox tab = loader.load();
            TabController tabController = loader.getController();
            tabController.setButtonText(buttonText);
            tabController.setActionCommand(actionCommand);
            navBarContainer.getChildren().add(tab);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void invalidated(javafx.beans.Observable observable) {
        // Cette méthode est appelée lorsque l'état de l'utilisateur change
        isUserLoggedIn = UserFacade.getInstance().getUser() != null;
        updateTabsBasedOnLoginStatus();
    }
}
