package com.moodvie.controller.watchLaterController;

import com.moodvie.business.facade.FilmFacade;
import com.moodvie.business.facade.UserFacade;
import com.moodvie.business.facade.WatchLaterFacade;
import com.moodvie.controller.NavigationController;
import com.moodvie.persistance.model.Film;
import com.moodvie.persistance.model.WatchLater;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class WatchLaterController {
    private static WatchLaterFacade watchLaterFacade = WatchLaterFacade.getInstance();

    private static UserFacade userFacade = UserFacade.getInstance();

    private static FilmFacade filmFacade = FilmFacade.getInstance();

    @FXML
    private FlowPane watchLaterFlowPane;

    public void initialize() {
        List<Film> watchLaterFilms = getWatchLaterFilms();
        displayFilms(watchLaterFilms);
    }

    private List<Film> getWatchLaterFilms() {
        List<Film> watchLaterFilms = new ArrayList<>();
        List<WatchLater> watchLaterList = watchLaterFacade.getAll(userFacade.getUser().getId());
        for (WatchLater watchLater : watchLaterList) {
            watchLaterFilms.add(filmFacade.getFilm(watchLater.getIdFilm()));
        }
        return watchLaterFilms;
    }

    private void displayFilms(List<Film> films) {
        watchLaterFlowPane.getChildren().clear();
        for (Film film : films) {
            VBox filmCard = createFilmCard(film);
            watchLaterFlowPane.getChildren().add(filmCard);
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

        card.getChildren().addAll(poster, title);
        card.setOnMouseClicked(event -> NavigationController.getInstance().loadFilmDetailView(filmFacade.getFilm(film.getImdbID())));;
        return card;
    }

}
