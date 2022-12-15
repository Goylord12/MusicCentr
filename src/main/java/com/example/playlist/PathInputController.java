package com.example.playlist;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

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
                File directory = new File(pathInputField.getText().replaceAll("\s", ""));
                for ( File file : directory.listFiles() ){
                    if ( file.isFile() && file.toString().split("\\.")[1].equalsIgnoreCase("mp3")) {
                        fileList.add(file);
                        System.out.print(file);
                    }
                }
            } catch (Exception ex) {
                errorText.setVisible(true);
            }
        });
    }

}
