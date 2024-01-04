package com.moodvie.controller.component.navBar;


import com.moodvie.controller.NavigationController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;

public class TabController {

    @FXML
    private Button tabButton;

    // Propriétés pour les paramètres dynamiques
    private StringProperty buttonText = new SimpleStringProperty();
    private StringProperty actionCommand = new SimpleStringProperty();

    // Initialise le contrôleur
    public void initialize() {
        // Liez le texte du bouton à la propriété buttonText
        tabButton.textProperty().bind(buttonText);

        // Liez l'action du bouton à une méthode qui gère l'action
        tabButton.setOnAction(this::handleButtonAction);
    }

    // Méthode pour gérer l'action du bouton
    private void handleButtonAction(ActionEvent event) {
        // Votre logique ici, en utilisant actionCommand pour déterminer l'action
        if (actionCommand.get().equals("loadLoginView")) {
            NavigationController.getInstance().loadLoginView();
        } else if (actionCommand.get().equals("loadProfilView")) {
            NavigationController.getInstance().loadProfilView();
        } else if (actionCommand.get().equals("loadRegisterView")) {
            NavigationController.getInstance().loadRegisterView();
        }else if (actionCommand.get().equals("logout")) {
            NavigationController.getInstance().handleDisconnect();
        }else if (actionCommand.get().equals("loadHomeView")) {
            NavigationController.getInstance().loadFilmView();
        }else if (actionCommand.get().equals("loadFilmSearchView")) {
            NavigationController.getInstance().loadFilmView();
        }else if (actionCommand.get().equals("loadWatchLaterView")) {
            NavigationController.getInstance().loadWatchLaterView();
        } else if (actionCommand.get().equals("loadLogView")) {
            NavigationController.getInstance().loadLogPageView();
        }
    }

    // Méthodes pour définir les propriétés
    public void setButtonText(String text) {
        buttonText.set(text);
    }

    public void setActionCommand(String command) {
        actionCommand.set(command);
    }

    // Méthodes pour obtenir les propriétés
    public String getButtonText() {
        return buttonText.get();
    }

    public String getActionCommand() {
        return actionCommand.get();
    }

    // Méthodes pour obtenir les propriétés en tant que propriétés
    public StringProperty buttonTextProperty() {
        return buttonText;
    }

    public StringProperty actionCommandProperty() {
        return actionCommand;
    }
}
