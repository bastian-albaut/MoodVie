package com.moodvie.controller.filmController;

import com.moodvie.business.facade.RatingFacade;
import com.moodvie.persistance.model.Film;
import com.moodvie.persistance.model.Rating;
import com.moodvie.business.facade.UserFacade;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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
     @FXML
    private Button addRatingButton;

    @FXML
    private Button addCommentButton;

    @FXML
    private TextArea ratingTextArea; //note du film

    @FXML
    private TextArea commentTextArea; //commentaire du film

    @FXML   
    private Label ratingLabel; //afficher note moyenne du film

    private Film film;
    private RatingFacade ratingFacade = RatingFacade.getInstance();
    private UserFacade userFacade = UserFacade.getInstance();
    private Rating rating;

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



    //INTERACTION AVEC LES FILMS

     // Ajout de la méthode pour gérer le clic sur le bouton d'ajout de note
     @FXML
     public void handleAddRatingButton() {
         // Initialisez l'objet Rating

         Rating rating = ratingFacade.get(userFacade.getUser().getId(), film.getId());

         if (rating != null) {
             showAlert("Erreur", "Vous avez déjà noté ce film.");
             return;
         }

         rating = new Rating();
 
         try {
             int newRatingValue = Integer.parseInt(ratingTextArea.getText());
             if (newRatingValue < 1 || newRatingValue > 5) {
                 showAlert("Erreur", "Veuillez entrer une note entre 1 et 5.");
                 return;
             }
 
             rating.setValue(newRatingValue);
             rating.setIdUser(userFacade.getUser().getId());
             rating.setIdFilm(film.getId());  // Assurez-vous d'avoir un moyen d'associer la note au film
             ratingFacade.add(rating);
             updateAverageRating();
             showAlert("Information", "Votre note a bien été enregistrée");
         } catch (NumberFormatException e) {
             showAlert("Erreur", "Veuillez entrer une note valide (nombre entre 1 et 5).");
         }
     }

     
    // Mettre à jour l'interface utilisateur avec la moyenne des notes et les commentaires

 
 
 
    // Ajout de la méthode pour gérer le clic sur le bouton d'ajout de commentaire
    @FXML
    public void handleAddCommentButton() {
         String newComment = commentTextArea.getText();
         rating.setComment(newComment);
         ratingFacade.update(rating);
         showAlert("Information", "Votre commentaire a bien été enregistré");
     }


    // Ajout de la méthode pour mettre à jour la moyenne des notes
    private void updateAverageRating() {
        double average = ratingFacade.getAverageRating(film.getId());
        displayAverageRating(average);
    }

    // Méthode pour afficher la moyenne des notes
    private void displayAverageRating(double average) {
        // À implémenter selon votre logique d'affichage (peut-être mettre à jour une étiquette ou un autre élément visuel)
        System.out.println("Moyenne des notes : " + average);
    }

    // Méthode pour afficher les commentaires
    private void displayComments(String comments) {
        System.out.println("Commentaires : " + comments);
    }


      // Method to show an alert with the given title and content
      private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
