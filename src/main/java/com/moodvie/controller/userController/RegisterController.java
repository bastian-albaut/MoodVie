package com.moodvie.controller.userController;

import com.moodvie.business.facade.LogFacade;
import com.moodvie.business.facade.UserFacade;
import com.moodvie.controller.NavigationController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.Date;

public class RegisterController {

    private final UserFacade userFacade = UserFacade.getInstance();

    private final LogFacade logFacade = LogFacade.getInstance();

    private final NavigationController navigationController = NavigationController.getInstance();

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
    
    public PasswordField confirmPasswordField;
    public TextField lastNameField;
    public DatePicker birthdatePicker;
    public TextField firstNameField;
    public TextField confirmEmailField;


    @FXML
    private void handleRegister() {

        // Logique d'inscription
        String pseudo = surnameField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();

        String birthdate = null;
        if (birthdatePicker.getValue() != null) {
            birthdate = birthdatePicker.getValue().toString();
        }

        String email = emailField.getText();
        String confirmEmail = confirmEmailField.getText();

        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        //On vérifie si les champs sont remplis
        if (pseudo.isEmpty() || email.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || birthdate.isEmpty() || confirmEmail.isEmpty() || confirmPassword.isEmpty()) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Échec de l'inscription");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Veuillez remplir tous les champs !");
            errorAlert.showAndWait();
            return;
        }
        if (!email.equals(confirmEmail)) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Échec de l'inscription");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Les emails ne correspondent pas !");
            errorAlert.showAndWait();
            return;
        }
        if (!password.equals(confirmPassword)) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Échec de l'inscription");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Les mots de passe ne correspondent pas !");
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

        Boolean success = userFacade.register(pseudo, firstName, lastName, birthdate, email, password);

        if (success) {
            // Afficher un message de succès
            logFacade.add(new com.moodvie.persistance.model.Log("Inscription", "Inscription réussie", pseudo));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Inscription Réussie");
            alert.setHeaderText(null);
            alert.setContentText("Vous êtes bien inscrit !");
            alert.showAndWait();
            navigationController.loadFilmView();

            // Rediriger vers une autre page si nécessaire
        } else {
            // Afficher un message d'erreur
            logFacade.add(new com.moodvie.persistance.model.Log("Inscription", "Inscription échouée", email));
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Échec de l'inscription");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Email déjà utilisé !");
            errorAlert.showAndWait();
        }
    }

    @FXML
    private void handleLogin() {
        NavigationController.getInstance().loadLoginView();
    }
}
