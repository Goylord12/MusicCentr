package com.example.playlist;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PathInputController {

    ArrayList<File> fileList = new ArrayList<>();

    @FXML
    private ResourceBundle resources;


    @FXML
    private ImageView iconView;

    @FXML
    private URL location;

    @FXML
    private Button approveButton;
    @FXML
    private Text errorText;

    @FXML
    private AnchorPane PathInputPane;

    @FXML
    private TextField pathInputField;

    @FXML
    void initialize() {
        //установка изображения
        iconView.setImage(new Image("file:assets/Icon.png"));
        approveButton.setOnAction(actionEvent -> {
            //проверка и перебор файлов в папке
            getFiles();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("MusicList.fxml"));
            if(!errorText.isVisible()) {
                try {
                    Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
                    Stage stage = new Stage();
                    stage.getIcons().add(new Image("file:assets/Icon.png"));
                    stage.setTitle("Playlist");
                    stage.setScene(scene);
                    stage.show();
                    MusicListController musicListController = fxmlLoader.getController();
                    musicListController.setFileList(fileList);
                } catch (IOException ex) {
                    System.out.print(ex.getMessage());
                }
            }
        });

    }

    //проверка расширения файла
    private boolean isMusicFile(String path) {
        String[] musicExtensions = new String[]{"mp3", "wav"};
        String extension = path.split("\\.")[1];
        for (String item : musicExtensions
        ) {
            if (item.equalsIgnoreCase(extension)) {
                return true;
            }
        }
        return false;
    }

    public void getFiles() {
        try {
            File directory = new File(pathInputField.getText());
            for (File file : directory.listFiles()) {
                if (file.isFile() && isMusicFile(file.getPath())) {
                    fileList.add(file);
                }
            }
            errorText.setVisible(false);
        } catch (Exception ex) {
            errorText.setVisible(true);
        }
    }

}
