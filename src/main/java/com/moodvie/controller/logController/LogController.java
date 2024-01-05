package com.moodvie.controller.logController;

import com.moodvie.business.facade.LogFacade;
import com.moodvie.persistance.model.Log;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


import java.util.Arrays;
import java.util.List;

public class LogController {
    @FXML
    private TableView<Log> logTableView;
    @FXML
    private TableColumn<Log, String> dateColumn;
    @FXML
    private TableColumn<Log, String> actionColumn;
    @FXML
    private TableColumn<Log, String> userIdColumn;
    @FXML
    private TableColumn<Log, String> userNameColumn;

    LogFacade logFacade = LogFacade.getInstance();

    public void initialize() {
        // Récupérer les logs de la base de données
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        actionColumn.setCellValueFactory(new PropertyValueFactory<>("action"));
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("nameUser"));

        List<Log> logs = Arrays.asList(logFacade.getAll());

        ObservableList<Log> logList = FXCollections.observableArrayList(logs);

        // Configurer les colonnes et lier les données
        logTableView.setItems(logList);
        // Ici, configurez chaque colonne pour afficher le champ approprié de Log
        // Exemple : columnDate.setCellValueFactory (new PropertyValueFactory<>("date"));
    }


}
