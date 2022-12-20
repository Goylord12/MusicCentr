package com.example.playlist;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class PathInput extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PathInput.class.getResource("Path-Input.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        stage.getIcons().add(new Image("file:assets/Icon.png"));
        stage.setTitle("Playlist");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}