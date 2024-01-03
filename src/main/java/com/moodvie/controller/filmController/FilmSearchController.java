package com.moodvie.controller.filmController;

import com.moodvie.business.facade.FilmFacade;
import com.moodvie.controller.NavigationController;
import com.moodvie.persistance.model.Film;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;

public class FilmSearchController {

    FilmFacade filmFacade = FilmFacade.getInstance();

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

    private void showFilmDetails(Film film) {
        Stage detailsStage = new Stage();
        detailsStage.setTitle(film.getTitle());

        VBox container = new VBox(10);
        // Ajoutez des composants pour afficher les détails du film

        Scene scene = new Scene(container, 300, 200); // Ajustez la taille selon les besoins
        detailsStage.setScene(scene);
        detailsStage.show();
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
