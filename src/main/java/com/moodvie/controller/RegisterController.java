package com.moodvie.controller;

import com.moodvie.business.facade.UserFacade;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class RegisterController {

    private final UserFacade userFacade = UserFacade.getInstance();

    @FXML

    public VBox registrationForm;
    @FXML
    public TextField emailField;
    @FXML
    public CheckBox termsCheckBox;
    @FXML
    public Button lgoinButton;
    @FXML
    public Button registerButton;
    @FXML
    public TextField surnameField;
    public PasswordField passwordField;


    @FXML
    private void handleRegister() {
        // Logique d'inscription
        String name = surnameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        Boolean success = userFacade.register(name, email, password);
        //On vérifie si les champs sont remplis
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Échec de l'inscription");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Veuillez remplir tous les champs !");
            errorAlert.showAndWait();
            return;
        }
        //On vérifie si les conditions d'utilisation sont acceptées
        else if (!termsCheckBox.isSelected()) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Échec de l'inscription");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Veuillez accepter les conditions d'utilisation !");
            errorAlert.showAndWait();
            return;
        }
        if (success) {
            // Afficher un message de succès
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Inscription Réussie");
            alert.setHeaderText(null);
            alert.setContentText("Vous êtes bien inscrit !");
            alert.showAndWait();

            // Rediriger vers une autre page si nécessaire
        } else {
            // Afficher un message d'erreur
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Échec de l'inscription");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Email déjà utilisé !");
            errorAlert.showAndWait();
        }
    }

    @FXML
    private void handleLogin() {

    }
}
