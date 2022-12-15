package com.example.playlist;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;

public class MusicListController {

    ArrayList<File> fileList;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() {

    }
    public void setFileList(ArrayList<File> fileList){
        this.fileList = new ArrayList<>(fileList);
    }

}

