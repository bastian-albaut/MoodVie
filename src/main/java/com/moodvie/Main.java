package com.moodvie;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/main-view.fxml"));
            Parent view = loader.load();
            stage.setScene(new Scene(view));
            stage.setMinHeight(600);
            stage.setMinWidth(800);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public static void main(String[] args) {
        launch();
    }
}