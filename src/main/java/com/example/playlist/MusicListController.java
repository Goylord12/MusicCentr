package com.example.playlist;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;

public class MusicListController {

    ArrayList<File> fileList;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<MusicFile, String>artistColumn;

    @FXML
    private TableColumn<MusicFile, String> fileNameColumn;

    @FXML
    private TableColumn<MusicFile, String> lengthColumn;

    @FXML
    private TableColumn<MusicFile, String> musicNameColumn;

    @FXML
    private TableColumn<MusicFile, String> sizeColumn;
    @FXML
    private TableView<MusicFile> musicTable;

    @FXML
    private AnchorPane musicTablePane;

    @FXML
    void initialize() {
        assert artistColumn != null : "fx:id=\"artistColumn\" was not injected: check your FXML file 'MusicList.fxml'.";
        assert fileNameColumn != null : "fx:id=\"fileNameColumn\" was not injected: check your FXML file 'MusicList.fxml'.";
        assert lengthColumn != null : "fx:id=\"lengthColumn\" was not injected: check your FXML file 'MusicList.fxml'.";
        assert musicNameColumn != null : "fx:id=\"musicNameColumn\" was not injected: check your FXML file 'MusicList.fxml'.";
        assert musicTable != null : "fx:id=\"musicTable\" was not injected: check your FXML file 'MusicList.fxml'.";
        assert musicTablePane != null : "fx:id=\"musicTablePane\" was not injected: check your FXML file 'MusicList.fxml'.";
        assert sizeColumn != null : "fx:id=\"sizeColumn\" was not injected: check your FXML file 'MusicList.fxml'.";
        musicNameColumn.setCellValueFactory(new PropertyValueFactory<>("Title"));
        fileNameColumn.setCellValueFactory(new PropertyValueFactory<>("FileName"));
        lengthColumn.setCellValueFactory(new PropertyValueFactory<>("Length"));
        artistColumn.setCellValueFactory(new PropertyValueFactory<>("Artist"));
    }
    public void setFileList(ArrayList<File> fileList){
        this.fileList = new ArrayList<>(fileList);
        ObservableList<MusicFile> data = FXCollections.observableArrayList();
        for (File item:fileList
             ) {
            data.add(new MusicFile(item));
        }
        musicTable.setItems(data);
    }

}

