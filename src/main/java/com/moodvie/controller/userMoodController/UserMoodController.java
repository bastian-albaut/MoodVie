package com.moodvie.controller.userMoodController;

import com.moodvie.business.facade.LogFacade;
import com.moodvie.business.facade.UserMoodFacade;
import com.moodvie.business.facade.UserFacade;
import com.moodvie.persistance.model.UserMood;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import com.moodvie.persistance.dao.OMDbApiFilmDao;
import com.moodvie.persistance.model.Film;
import java.util.Collections;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;



import java.util.List;

public class UserMoodController {

    private final UserMoodFacade userMoodFacade = UserMoodFacade.getInstance();

    private final UserFacade userFacade = UserFacade.getInstance();

    private final LogFacade logFacade = LogFacade.getInstance();

    @FXML
    private TextField moodDescriptionField;

    @FXML
    private Button addMoodButton;

    @FXML
    private VBox moodsVBox;

    private int userId; // ID de l'utilisateur connecté

    public UserMoodController() {
        userId = userFacade.getUser().getId();
    }

    @FXML
    private void initialize() {
        displayUserMoods();
    }

    @FXML
    private void handleAddMoodAction() {
        String moodDescription = moodDescriptionField.getText();
        UserMood userMood = new UserMood();
        userMood.setUserId(userId);
        userMood.setMoodDescription(moodDescription);
    
        // Exemple d'ID de film par défaut
        String associatedFilmID = "idDuFilmValide"; // Remplacez ceci par un ID valide
        userMood.addAssociatedFilmID(associatedFilmID);
    
        userMoodFacade.addUserMood(userMood);
        logFacade.add(new com.moodvie.persistance.model.Log("Ajout d'une humeur", "Ajout d'une humeur réussie", userFacade.getUser().getPseudo()));
        displayUserMoods();
    }

    private void displayUserMoods() {
        List<UserMood> userMoods = userMoodFacade.getAllUserMoods();
        moodsVBox.getChildren().clear();
        for (UserMood mood : userMoods) {
            VBox moodCard = createMoodCard(mood);
            moodsVBox.getChildren().add(moodCard);
        }
    }

    private VBox createMoodCard(UserMood mood) {
        VBox card = new VBox(10);
        Label moodDescription = new Label(mood.getMoodDescription());
        moodDescription.getStyleClass().add("mood-description");
    
        Button deleteButton = new Button("Supprimer");
        deleteButton.setOnAction(event -> handleDeleteMoodAction(mood));
    
        // Création de la ListView pour afficher les films
        ListView<Film> filmListView = new ListView<>();
        mood.getAssociatedFilmIDs().forEach(filmId -> {
            Film filmDetails = filmDao.get(filmId);
            if (filmDetails != null) {
                filmListView.getItems().add(filmDetails);
            }
        });
    
        card.getChildren().addAll(moodDescription, filmListView, deleteButton);
        return card;
    }
    private void handleDeleteMoodAction(UserMood mood) {
        userMoodFacade.deleteUserMood(mood.getMoodID());
        logFacade.add(new com.moodvie.persistance.model.Log("Suppression d'une humeur", "Suppression d'une humeur réussie", userFacade.getUser().getPseudo()));
        displayUserMoods();
    }
    
    private OMDbApiFilmDao filmDao = new OMDbApiFilmDao();


@FXML
private void handleSearchFilmAction() {
    String filmTitle = filmSearchField.getText();
    searchFilms(filmTitle);
}
@FXML
private TextField filmSearchField;

@FXML
private ComboBox<Film> filmComboBox; // Assurez-vous que ceci est correctement lié à votre ComboBox dans FXML

public void searchFilms(String title) {
    List<Film> foundFilms = filmDao.searchFilm(title);
    filmComboBox.getItems().clear();
    filmComboBox.getItems().addAll(foundFilms);
}


@FXML
private Button associateFilmButton; // Bouton pour associer le film sélectionné à l'humeur

// Méthode appelée lorsque l'utilisateur appuie sur le bouton "Associer Film"
@FXML
private void handleAssociateFilmAction() {
    Film selectedFilm = filmComboBox.getSelectionModel().getSelectedItem();
    if (selectedFilm != null && moodDescriptionField.getText() != null && !moodDescriptionField.getText().isEmpty()) {
        String moodDescription = moodDescriptionField.getText();

        UserMood userMood = new UserMood();
        userMood.setMoodDescription(moodDescription);
        userMood.addAssociatedFilmID(selectedFilm.getImdbID());

        userMoodFacade.addOrUpdateUserMood(userMood);

        // Mise à jour de l'affichage
        displayUserMoods();

        // Console log des films associés à l'humeur
        System.out.println("Films associés à l'humeur '" + moodDescription + "': ");
        userMood.getAssociatedFilmIDs().forEach(filmId -> {
            Film filmDetails = filmDao.get(filmId); // Utilisez la méthode get de OMDbApiFilmDao
            logFacade.add(new com.moodvie.persistance.model.Log("Ajout d'une humeur", "Ajout d'une humeur réussie", userFacade.getUser().getPseudo()));
            if (filmDetails != null) {
                System.out.println(filmDetails.getTitle());
            } else {
                System.out.println("Film ID: " + filmId + " n'a pas pu être récupéré.");
            }
        });

        // Afficher la notification de confirmation
        Alert confirmationAlert = new Alert(AlertType.INFORMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Le film a été associé à l'émotion avec succès!");
        confirmationAlert.showAndWait();
    } else {
        // Afficher un message d'erreur
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Impossible d'associer le film");
        logFacade.add(new com.moodvie.persistance.model.Log("Ajout d'une humeur", "Ajout d'une humeur échouée", userFacade.getUser().getPseudo()));

        if (selectedFilm == null) {
            alert.setContentText("Veuillez sélectionner un film.");
        } else if (moodDescriptionField.getText() == null || moodDescriptionField.getText().isEmpty()) {
            alert.setContentText("Veuillez entrer une description pour l'humeur.");
        }

        alert.showAndWait();
    }
}



// Ajoutez ou mettez à jour la méthode dans UserMoodFacade pour gérer l'ajout ou la mise à jour d'un mood
public void addOrUpdateUserMood(UserMood userMood) {
    // Votre logique pour ajouter ou mettre à jour un UserMood dans la base de données
}


}
