package com.moodvie.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;

public class RegisterController {

    public VBox registrationForm;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleRegister() {
        // Logique d'inscription
    }

    @FXML
    private void handleBack() {
        // Logique pour retourner à l'écran précédent
    }
}
