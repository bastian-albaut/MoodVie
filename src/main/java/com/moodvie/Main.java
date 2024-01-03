package com.moodvie;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    private static final Logger logger = LogManager.getLogger(Main.class);
    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/main-view.fxml"));
            Parent view = loader.load();

            Scene scene = new Scene(view);
            String css = Objects.requireNonNull(getClass().getResource("/app/style.css")).toExternalForm();
            scene.getStylesheets().add(css);

            stage.setScene(scene);
            stage.setMinHeight(600);
            stage.setMinWidth(800);
            stage.show();
        } catch (IOException e) {
            logger.error("Error while loading the main view : " + e.getMessage());
            throw e;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}