package com.moodvie.controller.filmController;

import com.moodvie.business.facade.RatingFacade;
import com.moodvie.persistance.model.Film;
import com.moodvie.persistance.model.Rating;
import com.moodvie.business.facade.UserFacade;
import com.moodvie.business.facade.WatchLaterFacade;
import com.moodvie.persistance.model.User;
import com.moodvie.persistance.model.WatchLater;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
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
    private Button addToWatchLaterButton;

    @FXML
    private TextArea commentTextArea; //commentaire du film

    @FXML   
    private Label averageRatingLabel; //afficher note moyenne du film

    @FXML
    private Spinner<Integer> noteSpinner;

    @FXML
    private Label commentsLabel; //afficher les commentaires du film

    private Film film;
    private RatingFacade ratingFacade = RatingFacade.getInstance();
    private UserFacade userFacade = UserFacade.getInstance();
    private WatchLaterFacade watchLaterFacade = WatchLaterFacade.getInstance();
    private Rating rating;

    // Méthode pour initialiser le contrôleur avec un film spécifique
    public void setFilm(Film film) {
        this.film = film;
        updateFilmDetails();
        updateWatchLaterButton();
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
        addFilmToWatchLater(film);
    }

    public void onRemoveFromWatchlist(ActionEvent actionEvent){
        removeFilmFromWatchLater(film);
    }



    //INTERACTION AVEC LES FILMS

     // Ajout de la méthode pour gérer le clic sur le bouton d'ajout de note
     @FXML
     public void handleAddRatingButton() {
         // Initialisez l'objet Rating

         Rating existingRating = ratingFacade.get(userFacade.getUser().getId(), film.getId());

         System.out.println("User ID: " + userFacade.getUser().getId());
         System.out.println("Film ID: " + film.getId());
         System.out.println("Existing Rating: " + existingRating);

         if (existingRating != null) {
             showAlert("Erreur", "Vous avez déjà noté ce film.");
             return;
         }

         rating = new Rating();

         int selectedNote = noteSpinner.getValue();  // Obtenez la note sélectionnée du Spinner
         
         rating.setValue(selectedNote);
         rating.setIdUser(userFacade.getUser().getId());
         rating.setIdFilm(film.getId());  // Assurez-vous d'avoir un moyen d'associer la note au film
         ratingFacade.add(rating);
         updateAverageRating();
         showAlert("Information", "Votre note a bien été enregistrée");

     }


    // Ajout de la méthode pour gérer le clic sur le bouton d'ajout de commentaire
    @FXML
    public void handleAddCommentButton() {
        String newComment = commentTextArea.getText();

        if (rating == null) {
            showAlert("Erreur", "Veuillez d'abord ajouter une note avant d'ajouter un commentaire.");
            return;
        }

        rating.setComment(newComment);
        ratingFacade.updateComment(rating);
        displayComments();
        showAlert("Information", "Votre commentaire a bien été enregistré");
        
        // Vous pouvez également réinitialiser le TextArea après l'ajout
        commentTextArea.clear();
    }


    // Ajout de la méthode pour mettre à jour la moyenne des notes
    private void updateAverageRating() {
        double average = ratingFacade.getAverageRating(film.getId());
        displayAverageRating(average);
    }

    // Méthode pour afficher la moyenne des notes
    private void displayAverageRating(double average) {
        averageRatingLabel.setText(String.valueOf(average));
    }

    // Méthode pour afficher les commentaires
    @FXML
    private void displayComments() {
        String comments = ratingFacade.getComments(film.getId());
        commentsLabel.setText(comments);
    }   


      // Method to show an alert with the given title and content
      private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
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

   private void updateWatchLaterButton() {
    WatchLater watchLater = watchLaterFacade.get(film.getImdbID(), userFacade.getUser().getId());
    if (watchLater != null) {
        addToWatchLaterButton.setText("Retirer de la liste");
        addToWatchLaterButton.setOnAction(this::onRemoveFromWatchlist);
    } else {
        addToWatchLaterButton.setText("Ajouter à la liste");
        addToWatchLaterButton.setOnAction(this::onAddToWatchlist);
    }
}

}
