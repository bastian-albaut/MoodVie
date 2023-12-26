package com.moodvie.controller;

import com.moodvie.business.facade.UserFacade;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.Objects;

public class NavigationController {

    private static NavigationController instance;

    private final UserFacade userFacade = UserFacade.getInstance();

    private StackPane mainContentArea;  // Supposons que c'est l'endroit où les vues sont chargées

    private NavigationController() { }

    public static synchronized NavigationController getInstance() {
        if (instance == null) {
            instance = new NavigationController();
        }
        return instance;
    }

    public void setMainContentArea(StackPane mainContentArea) {
        this.mainContentArea = mainContentArea;
    }

    /**
     * Load the login view
     */
    public void loadLoginView() {
        loadView("/app/login-view.fxml", null);
    }

    /**
     * Load the profil view
     */
    public void loadProfilView() {
        loadView("/app/profilView/profil-view.fxml", null);
    }

    /**
     * Load the register view
     */
    public void loadRegisterView() {
        loadView("/app/register-view.fxml", null);
    }

    /**
     * handle the disconnect
     */
    public void handleDisconnect() {
        userFacade.logout();
        loadLoginView();
    }

    /**
     * Load the subscribe view
     */
    public void loadSubscribeView() {
        loadView("/app/subscribeView/subscribe-view.fxml", "/app/subscribeView/style-subscribe.css");
    }

    /**
     * Load the view with the given fxmlPath
     */
    public void loadView(String fxmlPath, String cssPath) {
        try {
            Parent view = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlPath)));

            // Load the CSS if needed
            if (cssPath != null && !cssPath.isEmpty()) {
                String css = getClass().getResource(cssPath).toExternalForm();
                view.getStylesheets().add(css);
            }

            mainContentArea.getChildren().setAll(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
