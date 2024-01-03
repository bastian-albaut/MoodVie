package com.moodvie.controller.filmController;

import com.moodvie.persistance.model.Film;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FilmDetailsController {

    @FXML
    private ImageView posterImageView;
    @FXML
    private Label titleLabel;
    @FXML
    private Label yearLabel;
    @FXML
    private Label genreLabel;
    @FXML
    private Label plotLabel;
    // Ajoutez d'autres éléments FXML si nécessaire

    private Film film;

    // Méthode pour initialiser le contrôleur avec un film spécifique
    public void setFilm(Film film) {
        this.film = film;
        updateFilmDetails();
    }

    // Mettre à jour l'interface utilisateur avec les détails du film
    private void updateFilmDetails() {
        if (film != null) {
            titleLabel.setText(film.getTitle());
            yearLabel.setText(film.getYear());
            genreLabel.setText(film.getGenre());
            plotLabel.setText(film.getPlot());

            if (film.getPoster() != null && !film.getPoster().isEmpty()) {
                Image image = new Image(film.getPoster(), true);
                posterImageView.setImage(image);
            }
        }
    }

    public void onBack(ActionEvent actionEvent) {
    }

    public void onAddToWatchlist(ActionEvent actionEvent) {
    }
}
