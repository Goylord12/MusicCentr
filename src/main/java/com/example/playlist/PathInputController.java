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
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PathInputController{

    ArrayList<File> fileList = new ArrayList<>();

    @FXML
    private ResourceBundle resources;

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
        assert PathInputPane != null : "fx:id=\"PathInputPane\" was not injected: check your FXML file 'Path-Input.fxml'.";
        assert approveButton != null : "fx:id=\"approveButton\" was not injected: check your FXML file 'Path-Input.fxml'.";
        assert pathInputField != null : "fx:id=\"pathInputField\" was not injected: check your FXML file 'Path-Input.fxml'.";
        assert errorText != null : "fx:id=\"errorText\" was not injected: check your FXML file 'Path-Input.fxml'.";
        approveButton.setOnAction(actionEvent -> {
            try {
                File directory = new File(pathInputField.getText());
                for ( File file : directory.listFiles() ){
                    if ( file.isFile() && isMusicFile(file.getPath())) {
                        fileList.add(file);
                        System.out.print(file);
                    }
                }

            } catch (Exception ex) {
                errorText.setVisible(true);
            }
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("MusicList.fxml"));
            try{
                Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
                Stage stage = new Stage();
                stage.setTitle("Playlist");
                stage.setScene(scene);
                stage.show();
                MusicListController musicListController = fxmlLoader.getController();
                musicListController.setFileList(fileList);
            }
            catch (IOException ex){
                System.out.print(ex.getMessage());
            }
        });

    }
    private boolean isMusicFile(String path){
        String[] musicExtensions = new String[]{"mp3","wav"};
        String extension = path.split("\\.")[1];
        for (String item:musicExtensions
             ) {
            if(item.equalsIgnoreCase(extension)){
                return true;
            }
        }
        return false;
    }

}
