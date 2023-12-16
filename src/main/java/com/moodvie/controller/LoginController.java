package com.moodvie.controller;


import com.moodvie.business.facade.UserFacade;
import com.moodvie.persistance.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

public class LoginController {

    public Button loginButton;
    public Button registerButton;
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private final UserFacade userFacade = UserFacade.getInstance();

    @FXML
    private void handleLoginAction() {
        String email = usernameField.getText();
        String password = passwordField.getText();

        User user = userFacade.login(email, password);
        if (user != null) {
            // Afficher un message de succès
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Connexion Réussie");
            alert.setHeaderText(null);
            alert.setContentText("Vous êtes bien connecté !");
            alert.showAndWait();

            // Rediriger vers une autre page si nécessaire
        } else {
            // Afficher un message d'erreur
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Échec de la Connexion");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Email ou mot de passe incorrect !");
            errorAlert.showAndWait();
        }
    }

    public void handleShowRegisterView(ActionEvent actionEvent) {
    }
}
