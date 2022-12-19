package com.example.playlist;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;

public class MusicListController {
    MusicFile nowPlaying;
    MediaPlayer mediaPlayer;
    ArrayList<File> fileList;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button pauseButton;

    @FXML
    private Button playButton;

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
    public TableView<MusicFile> musicTable;

    @FXML
    private AnchorPane musicTablePane;

    @FXML
    void initialize() {
        musicNameColumn.setCellValueFactory(new PropertyValueFactory<>("Title"));
        fileNameColumn.setCellValueFactory(new PropertyValueFactory<>("FileName"));
        lengthColumn.setCellValueFactory(new PropertyValueFactory<>("Length"));
        artistColumn.setCellValueFactory(new PropertyValueFactory<>("Artist"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("Size"));
        playButton.setOnAction(actionEvent -> {
           play();
        });

        pauseButton.setOnAction(actionEvent -> {
            pause();
        });
    }
    public void setFileList(ArrayList<File> fileList){
        MusicFile.setController(this);
        this.fileList = new ArrayList<>(fileList);
        ObservableList<MusicFile> data = FXCollections.observableArrayList();
        for (File item:fileList
             ){
            data.add(new MusicFile(item));
        }
        musicTable.setItems(data);

    }
    public void play(){
        if(musicTable.getSelectionModel().getSelectedItems().get(0)!=null) {
            if(nowPlaying==null||!nowPlaying.equals(musicTable.getSelectionModel().getSelectedItems().get(0))) {
                if(mediaPlayer!=null) {
                    mediaPlayer.dispose();
                }
                nowPlaying = musicTable.getSelectionModel().getSelectedItems().get(0);
                mediaPlayer = new MediaPlayer(nowPlaying.mediaFile);
                mediaPlayer.setOnReady(new Runnable() {
                    @Override
                    public void run() {
                        mediaPlayer.play();
                    }
                });
            }
            else{
                if(mediaPlayer.getStatus()== MediaPlayer.Status.PAUSED||mediaPlayer.getStatus()== MediaPlayer.Status.STOPPED){
                    mediaPlayer.play();
                }
            }

        }

    }
    public void pause() {
        if (mediaPlayer != null) {
            if (mediaPlayer.getStatus() != MediaPlayer.Status.PAUSED || mediaPlayer.getStatus() != MediaPlayer.Status.STOPPED) {
                mediaPlayer.pause();
            }
        }
    }
}

