package com.moodvie.controller.userController;


import com.moodvie.business.facade.LogFacade;
import com.moodvie.business.facade.UserFacade;
import com.moodvie.controller.NavigationController;
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

    private final LogFacade logFacade = LogFacade.getInstance();

    @FXML
    private void handleLoginAction() {
        String email = usernameField.getText();
        String password = passwordField.getText();

        User user = userFacade.login(email, password);
        if (user != null) {
            // Afficher un message de succès
            logFacade.add(new com.moodvie.persistance.model.Log("Connexion", "Connexion réussie", user.getPseudo()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Connexion Réussie");
            alert.setHeaderText(null);
            alert.setContentText("Vous êtes bien connecté !");
            alert.showAndWait();

            // Rediriger vers une autre page si nécessaire
        } else {
            // Afficher un message d'erreur
            logFacade.add(new com.moodvie.persistance.model.Log("Connexion", "Connexion échouée", email));
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Échec de la Connexion");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Email ou mot de passe incorrect !");
            errorAlert.showAndWait();
        }
    }

    public void handleShowRegisterView() {
        NavigationController.getInstance().loadRegisterView();
    }

    public void handleCancelAction() {
        usernameField.setText("");
        passwordField.setText("");
    }

    public void handleShowForgotPasswordView() {
       //Afficher une alerte
        logFacade.add(new com.moodvie.persistance.model.Log("Mot de passe oublié", "Mot de passe oublié", "Mot de passe oublié"));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mot de passe oublié");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez contacter l'administrateur !");
        alert.showAndWait();
    }
}
