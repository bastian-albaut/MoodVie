package com.moodvie.controller.filmController;

import com.moodvie.business.facade.FilmFacade;
import com.moodvie.business.facade.UserFacade;
import com.moodvie.business.facade.WatchLaterFacade;
import com.moodvie.controller.NavigationController;
import com.moodvie.persistance.model.Film;
import com.moodvie.persistance.model.User;
import com.moodvie.persistance.model.WatchLater;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.List;

public class FilmSearchController {

    FilmFacade filmFacade = FilmFacade.getInstance();

    WatchLaterFacade watchLaterFacade = WatchLaterFacade.getInstance();

    UserFacade userFacade = UserFacade.getInstance();

    @FXML
    private TextField searchField;

    @FXML
    private Button searchButton;

    @FXML
    private FlowPane filmsFlowPane;

    public void onSearch() {
        // Logique pour rechercher des films et les afficher
        List<Film> films = filmFacade.searchFilms(searchField.getText());
        displayFilms(films);
    }

    private void displayFilms(List<Film> films) {
        filmsFlowPane.getChildren().clear();
        filmsFlowPane.setHgap(10); // Espacement horizontal entre les cartes
        filmsFlowPane.setVgap(10);
        for (Film film : films) {
            VBox filmCard = createFilmCard(film);
            filmsFlowPane.getChildren().add(filmCard);
        }
    }

    private void addFilmToWatchLater(Film film) {
        User currentUser = userFacade.getUser();

        if (currentUser != null) {
            watchLaterFacade.add(new WatchLater(currentUser.getId(), film.getImdbID()));
            System.out.println("Le film a été ajouté à la liste");
        }else {
            System.out.println("Erreur : Aucun utilisateur connecté.");
        }
    }

    private void removeFilmFromWatchLater(Film film) {
        User currentUser = userFacade.getUser();

        if (currentUser != null) {
            WatchLater watchLater = watchLaterFacade.get(film.getImdbID(), currentUser.getId());

            if (watchLater != null && watchLater.getIdFilm() != null) {
                watchLaterFacade.delete(watchLater.getIdWatchLater());
                System.out.println("Le film a été retiré de la liste");
            }
        } else {
            System.out.println("Erreur : Aucun utilisateur connecté.");
        }
    }

    private void updateWatchLaterButton(Button button, Film film) {
        User currentUser = userFacade.getUser();

        if (currentUser != null) {
            WatchLater watchLater = watchLaterFacade.get(film.getImdbID(), currentUser.getId());

            if (watchLater != null && watchLater.getIdFilm() != null) {
                // Le film est déjà dans la liste
                button.setText("Retirer");
                button.setOnAction(event -> removeFilmFromWatchLater(film));
            } else {
                // Le film n'est pas dans la liste
                button.setText("Ajouter");
                button.setOnAction(event -> addFilmToWatchLater(film));
            }
        } else {
            button.setText("Ajouter");
            button.setOnAction(event -> System.out.println("Erreur : Aucun utilisateur connecté."));
        }
    }



    private VBox createFilmCard(Film film) {
        VBox card = new VBox(10);
        card.setPrefSize(200, 300); // Taille préférée de la carte
        card.getStyleClass().add("film-card");
        ImageView poster;
        try {
            poster = new ImageView(film.getPoster());
            poster.setFitWidth(180); // Largeur de l'image
            poster.setFitHeight(250); // Hauteur de l'image
            poster.setPreserveRatio(true);
        } catch (IllegalArgumentException e) {
            // Log l'erreur et/ou utilisez une image par défaut
            poster = new ImageView("https://via.placeholder.com/300x450.png?text=No+poster+available");
            poster.setFitWidth(180); // Largeur de l'image
            poster.setFitHeight(250); // Hauteur de l'image
            poster.setPreserveRatio(true);
        }
        Label title = new Label(film.getTitle());
        title.getStyleClass().add("film-title");

        Button watchLaterButton = new Button();
        watchLaterButton.getStyleClass().add("watch-later-button");

        updateWatchLaterButton(watchLaterButton, film);


        // Utiliser un StackPane pour superposer le bouton sur l'ImageView
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(poster, watchLaterButton);
        StackPane.setAlignment(watchLaterButton, Pos.BOTTOM_RIGHT); // Positionne le bouton en bas à droite

        card.getChildren().addAll(stackPane, title);
        card.setOnMouseClicked(event -> NavigationController.getInstance().loadFilmDetailView(filmFacade.getFilm(film.getImdbID())));;
        return card;
    }
}
