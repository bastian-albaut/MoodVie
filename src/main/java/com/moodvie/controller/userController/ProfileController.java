package com.moodvie.controller.userController;

import com.moodvie.business.facade.LogFacade;
import com.moodvie.business.facade.UserFacade;
import com.moodvie.persistance.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

import java.io.IOException;
public class ProfileController {

    private final UserFacade userFacade = UserFacade.getInstance();

    private final LogFacade logFacade = LogFacade.getInstance();
    public TextField emailField;

    public TextField firstNameField;

    public TextField lastNameField;

    public DatePicker birthDateField;
    public TextField pseudoField;

    public TextField passwordField;

    public void initialize() {
        User user = userFacade.getUser();
        if (user == null) {
            return;
        }
        emailField.setText(user.getEmail());
        firstNameField.setText(user.getFirstname());
        lastNameField.setText(user.getLastname());
        //On transorme la date en LocalDate

        pseudoField.setText(user.getPseudo());
        passwordField.setText(user.getPassword());
    }


    public void updateUser(ActionEvent actionEvent) {
        String email = emailField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String birthDate = birthDateField.getValue().toString();
        String pseudo = pseudoField.getText();
        String password = passwordField.getText();

        User user = userFacade.updateUser(email, firstName, lastName, birthDate, pseudo, password);
        if (user != null) {
            // Afficher un message de succès
            // Rediriger vers une autre page si nécessaire
            logFacade.add(new com.moodvie.persistance.model.Log("Modification", "Modification réussie", user.getPseudo()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Modification Réussie");
            alert.setHeaderText(null);
            alert.setContentText("Vous avez bien modifié votre profil !");
            alert.showAndWait();


        } else {
            // Afficher un message d'erreur
            logFacade.add(new com.moodvie.persistance.model.Log("Modification", "Modification échouée", email));
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Échec de la Modification");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Veuillez remplir tous les champs !");
            errorAlert.showAndWait();
        }
    }
}
