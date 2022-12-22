package com.example.playlist;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class MusicListController {
    private MusicFile nowPlaying;
   private MediaPlayer mediaPlayer;
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

        //визуальная отдача кнопок

        playButton.setOnMouseEntered(mouseEvent -> {
            playButton.setScaleX(1.1);
            playButton.setScaleY(1.1);
        });
        playButton.setOnMouseExited(mouseEvent -> {
            playButton.setScaleX(1);
            playButton.setScaleY(1);
        });

        pauseButton.setOnMouseEntered(mouseEvent -> {
            pauseButton.setScaleX(1.1);
            pauseButton.setScaleY(1.1);
        });
        pauseButton.setOnMouseExited(mouseEvent -> {
            pauseButton.setScaleX(1);
            pauseButton.setScaleY(1);
        });
        playButton.setOnAction(actionEvent -> play());

        pauseButton.setOnAction(actionEvent -> pause());
    }
    //создание списка с музыкальными файлами и установка этого списка в таблицу
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
    //проигрывание музыки
    public void play(){
        if(musicTable.getSelectionModel().getSelectedItems().size()==1) {
            if(nowPlaying==null||!nowPlaying.equals(musicTable.getSelectionModel().getSelectedItems().get(0))) {
                playButton.setScaleX(0.90);
                playButton.setScaleY(0.90);
                if(mediaPlayer!=null) {
                    mediaPlayer.dispose();
                }
                nowPlaying = musicTable.getSelectionModel().getSelectedItems().get(0);
                mediaPlayer = new MediaPlayer(nowPlaying.mediaFile);
                mediaPlayer.setOnReady(new Runnable() {
                    @Override
                    public void run() {
                        mediaPlayer.play();
                        Timeline timeLine = new Timeline(new KeyFrame(Duration.seconds(0.1), e -> {
                            playButton.setScaleX(1.1);
                            playButton.setScaleY(1.1);
                        }));
                        timeLine.playFromStart();
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
    //пауза
    public void pause() {
        if (mediaPlayer != null) {
            if (mediaPlayer.getStatus() != MediaPlayer.Status.PAUSED || mediaPlayer.getStatus() != MediaPlayer.Status.STOPPED) {
                pauseButton.setScaleX(0.90);
                pauseButton.setScaleY(0.90);
                mediaPlayer.pause();
                Timeline timeLine = new Timeline(new KeyFrame(Duration.seconds(0.1), e -> {
                    pauseButton.setScaleX(1.1);
                    pauseButton.setScaleY(1.1);
                }));
                timeLine.playFromStart();
            }
        }
    }
}

