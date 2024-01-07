package com.moodvie.controller.ratingController;

import com.moodvie.persistance.model.Rating;

import java.util.List;

import com.moodvie.business.facade.RatingFacade;
import com.moodvie.persistance.model.Film;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class RatingController { 

    @FXML
    private Button addRatingButton;

    @FXML
    private Button addCommentButton;

    @FXML
    private TextArea commentTextArea;

    @FXML   
    private Label ratingLabel;

    @FXML
    private HBox ratingHBox;


    private RatingFacade ratingFacade = RatingFacade.getInstance();
    private Rating rating;
    private Film film;

      // Method to show an alert with the given title and content
      private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /* 

    // Ajout de la méthode pour gérer le clic sur le bouton d'ajout de note
    @FXML
    public void handleAddRatingButton() {
        int newRating = Integer.parseInt(ratingLabel.getText());
        rating.setValue(newRating);
        ratingFacade.update(rating);
        updateAverageRating();  // Ajout de la mise à jour de la moyenne après l'ajout de la note
        showAlert("Information", "Votre note a bien été enregistrée");
    }

    */


    // Ajout de la méthode pour gérer le clic sur le bouton d'ajout de commentaire
    @FXML
    public void handleAddCommentButton() {
        String newComment = commentTextArea.getText();
        rating.setComment(newComment);
        ratingFacade.update(rating);
        showAlert("Information", "Votre commentaire a bien été enregistré");
    }

    /* 

    // Ajout de la méthode pour mettre à jour la moyenne des notes
    private void updateAverageRating() {
        double average = ratingFacade.getAverageRating(film.getId());
        displayAverageRating(average);
    }

    */

    // Méthode pour afficher la moyenne des notes
    private void displayAverageRating(double average) {
        // À implémenter selon votre logique d'affichage (peut-être mettre à jour une étiquette ou un autre élément visuel)
        System.out.println("Moyenne des notes : " + average);
    }



}
