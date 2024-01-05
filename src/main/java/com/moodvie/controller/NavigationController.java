package com.moodvie.controller;

import com.moodvie.business.facade.UserFacade;
import com.moodvie.controller.filmController.FilmDetailsController;
import com.moodvie.persistance.model.Film;
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
     * Load the log page view
     */
    public void loadLogPageView() {
        loadView("/app/logView/log-view.fxml", null);
    }

    /**
     * Load the film view
     */
    public void loadFilmView() {
        loadView("/app/filmView/searchFilms-view.fxml", null);
    }

    public void loadFilmDetailView(Film film) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/filmView/detailFilm-view.fxml"));
            Parent view = loader.load();

            // Ici, nous obtenons le contrôleur de la vue des détails du film
            FilmDetailsController controller = loader.getController();
            controller.setFilm(film); // Méthode à créer dans FilmDetailsController

            mainContentArea.getChildren().setAll(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadWatchLaterView() {
        loadView("/app/watchLaterView/watchLater-view.fxml",null);
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
